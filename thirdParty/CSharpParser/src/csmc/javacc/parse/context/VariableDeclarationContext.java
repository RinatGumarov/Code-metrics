package csmc.javacc.parse.context;

import java.util.Set;

public class VariableDeclarationContext extends ParseContext<String, Set<String>> {
    public VariableDeclarationContext(ParseContext parent, String typeName, Set<String> variableNames) {
        this.parent = parent;
        this.key = typeName;
        this.value = variableNames;
    }
}
