/**
 * Copyright © 2016-2019 The Thingsboard Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.tincat.host.feast.plugin.jsexecutor;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import lombok.extern.slf4j.Slf4j;

import javax.script.Invocable;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ashvayka on 26.09.18.
 */
@Slf4j
public class NashronJsInvokeService implements JsInvokeService {
    private NashornSandbox sandbox;
    private ExecutorService monitorExecutorService;
    private Map<UUID, String> scriptIdToNameMap;

    public NashronJsInvokeService() {
        scriptIdToNameMap = new HashMap<>();
        sandbox = NashornSandboxes.create();
        monitorExecutorService = Executors.newWorkStealingPool(4);
        sandbox.setExecutor(monitorExecutorService);
        sandbox.setMaxCPUTime(100);
        sandbox.allowNoBraces(false);
        sandbox.allowLoadFunctions(true);
        sandbox.setMaxPreparedStatements(30);
    }

    @Override
    public ListenableFuture<Object> eval(String functionName, String scriptBody, String[] argNames, Object... args) {
        try {
            Object a = sandbox.eval("print('Hello World!');return 'aaa';");
            System.out.println("a="+a);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        String jsScript = generateJsScript(functionName, scriptBody, argNames);
        SimpleBindings bindings = new SimpleBindings();
        for (int i = 0; i < argNames.length; i++) {
            bindings.put(argNames[i], args[i]);
        }
        Object result;
        try {
            result = sandbox.eval(jsScript, bindings);
        } catch (ScriptException e) {
            e.printStackTrace();
            return Futures.immediateFailedFuture(e);
        }
        return Futures.immediateFuture(result);
    }

    @Override
    public ListenableFuture<UUID> declare(String functionName, String scriptBody, String... argNames) {
        UUID scriptId = UUID.randomUUID();
        String jsScript = generateJsScript(functionName, scriptBody, argNames);
        return doSave(scriptId, functionName, jsScript);
    }

    @Override
    public ListenableFuture<Object> invokeFunction(UUID scriptId, Object... args) {
        try {
            Object result;
            String functionName = scriptIdToNameMap.get(scriptId);
            result = sandbox.getSandboxedInvocable().invokeFunction(functionName, args);
            return Futures.immediateFuture(result);
        } catch (Exception e) {
            onScriptExecutionError(scriptId);
            return Futures.immediateFailedFuture(e);
        }
    }


    @Override
    public ListenableFuture<Void> release(UUID scriptId) {
        String functionName = scriptIdToNameMap.get(scriptId);
        if (functionName != null) {
            scriptIdToNameMap.remove(scriptId);
            try {
                sandbox.eval(functionName + " = undefined;");
            } catch (ScriptException e) {
                e.printStackTrace();
                return Futures.immediateFailedFuture(e);
            }
        }
        return Futures.immediateFuture(null);
    }

    private String generateJsScript(String functionName, String scriptBody, String[] argNames) {
        return JsScriptFactory.generateRuleNodeScript(functionName, scriptBody, argNames);
    }

    private ListenableFuture<UUID> doSave(UUID scriptId, String functionName, String jsScript) {
        try {
            sandbox.eval(jsScript);
            scriptIdToNameMap.put(scriptId, functionName);
        } catch (Exception e) {
            log.warn("Failed to compile JS script: {}", e.getMessage(), e);
            return Futures.immediateFailedFuture(e);
        }
        return Futures.immediateFuture(scriptId);
    }

    private void onScriptExecutionError(UUID scriptId) {
    }

}
