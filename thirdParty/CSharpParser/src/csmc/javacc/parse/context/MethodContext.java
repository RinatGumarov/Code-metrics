package csmc.javacc.parse.context;

import csmc.lang.CSMethod;

public class MethodContext extends ParseContext<String, CSMethod> {
    public MethodContext(ParseContext parent, String methodName, CSMethod method) {
        this.parent = parent;
        this.key = methodName;
        this.value = method;
    }
}
