package xyz.tincat.host.feast.plugin.jsexecutor;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @ Date       ：Created in 13:47 2020/1/3
 * @ Modified By：
 * @ Version:     0.1
 */
public class TestJsService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JsInvokeService service = new NashronJsInvokeService();
        UUID id = service.declare("aaa", "return {s:123};", "round", "mem").get();
        Object s = service.invokeFunction(id, "{}", "{}").get();
        System.out.println("result = " + s);
        String[] argNames = {"round", "mem"};
        Object s2 = service.eval("aaa", "return {s:123};", argNames, "{}", "{}").get();
        System.out.println("result2 = " + s2);
    }
}