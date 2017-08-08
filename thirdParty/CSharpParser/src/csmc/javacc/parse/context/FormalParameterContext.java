package csmc.javacc.parse.context;

import csmc.lang.CSParameter;
import java.util.Set;

public class FormalParameterContext extends ParseContext<String, Set<CSParameter>> {
    public FormalParameterContext(ParseContext parent, String methodName, Set<CSParameter> formalArguments) {
        this.parent = parent;
        this.key = methodName;
        this.value = formalArguments;
    }
}
