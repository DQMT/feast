package xyz.tincat.host.feast.plugin.jsexecutor;


import com.google.common.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * @ Date       ：Created in 14:20 2020/1/2
 * @ Modified By：
 * @ Version:     0.1
 */
public interface JsInvokeService {

    ListenableFuture<Object> eval(String functionName, String scriptBody, String[] argNames, Object... args);

    ListenableFuture<UUID> declare(String functionName, String scriptBody, String... argNames);

    ListenableFuture<Object> invokeFunction(UUID scriptId, Object... args);

    ListenableFuture<Void> release(UUID scriptId);

}