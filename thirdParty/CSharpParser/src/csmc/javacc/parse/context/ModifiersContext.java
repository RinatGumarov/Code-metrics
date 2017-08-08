package csmc.javacc.parse.context;

import csmc.lang.CSModifier;
import java.util.Set;

public class ModifiersContext extends ParseContext<String, Set<CSModifier>> {
    public ModifiersContext(ParseContext parent, String entityName, Set<CSModifier> modifiers) {
        this.parent = parent;
        this.key = entityName;
        this.value = modifiers;
    }
}
