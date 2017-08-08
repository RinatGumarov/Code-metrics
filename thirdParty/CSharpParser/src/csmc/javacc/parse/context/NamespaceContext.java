package csmc.javacc.parse.context;

import csmc.lang.CSNamespace;

/**
 * NamespaceContext indicates current namespace name.
 * It uses String as key parameter, and instance of CSNamespace as value.
 */
public class NamespaceContext extends ParseContext<String, CSNamespace> {
    public NamespaceContext(ParseContext parent, String namespaceName, CSNamespace namespace) {
        this.parent = parent;
        this.key = namespaceName;
        this.value = namespace;
    }
}
