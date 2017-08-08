package csmc.javacc.parse.context;

import csmc.lang.CSClass;

/**
 * ClassContext contains current class name, associated CK metric
 * and reference to related namespace context.
 * It uses String as key parameter, and instance of CSClass as value.
 */
public class ClassContext extends ParseContext<String, CSClass> {
    public ClassContext(ParseContext parent, String className, CSClass csClass) {
        this.parent = parent;
        this.key = className;
        this.value = csClass;
    }
}
