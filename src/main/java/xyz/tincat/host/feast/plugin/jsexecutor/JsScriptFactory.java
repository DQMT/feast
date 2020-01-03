package xyz.tincat.host.feast.plugin.jsexecutor;

/**
 * @ Date       ：Created in 16:38 2020/1/2
 * @ Modified By：
 * @ Version:     0.1
 */
public class JsScriptFactory {
    public static final String ROUND = "round";
    public static final String MEM = "mem";
    public static final String NODE_FUNCTION_NAME = "nodeFunc";

    private static final String JS_WRAPPER_PREFIX_TEMPLATE = "function %s(roundStr, memStr){\n" +
            "    var round = JSON.parse(roundStr);\n" +
            "    var mem = JSON.parse(memStr);\n" +
            "    return JSON.stringify(%s(round, mem));\n" +
            "    function %s(%s,%s){";
    private static final String JS_WRAPPER_SUFFIX = "\n}" +
            "\n}";


    public static String generateRuleNodeScript(String functionName, String scriptBody, String... argNames) {
        if (argNames != null && argNames.length == 2) {
            String jsWrapperPrefix = String.format(JS_WRAPPER_PREFIX_TEMPLATE, functionName,
                    NODE_FUNCTION_NAME, NODE_FUNCTION_NAME, argNames[0], argNames[1]);
            return jsWrapperPrefix + scriptBody + JS_WRAPPER_SUFFIX;
        } else {
            throw new RuntimeException("argNames size must be 2");
        }
    }


}