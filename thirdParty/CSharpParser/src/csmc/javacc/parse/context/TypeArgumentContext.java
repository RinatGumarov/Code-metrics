package csmc.javacc.parse.context;

import java.util.Set;

public class TypeArgumentContext extends ParseContext<String, Set<String>> {
    public TypeArgumentContext(ParseContext parent, String className, Set<String> typeArguments) {
        this.parent = parent;
        this.key = className;
        this.value = typeArguments;
    }
}
