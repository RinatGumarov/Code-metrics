package csmc.javacc.parse.context;

public class IdentifierContext extends ParseContext<String, StringBuilder> {
    public IdentifierContext(ParseContext parent, String className) {
        this.parent = parent;
        this.key = className;
        this.value = new StringBuilder();
    }
}
