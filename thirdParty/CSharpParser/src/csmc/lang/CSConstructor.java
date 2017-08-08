package csmc.lang;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CSConstructor extends CSMethod {
    private String constructorInitializer;

    public CSConstructor(CSClass csClass, Set<CSModifier> modifiers, String type, String name, Set<CSParameter> formalParameters, Set<String> typeParameters, String body, String constructorInitializer) {
        super(csClass, modifiers, type, name, formalParameters, typeParameters, body);
        this.constructorInitializer = constructorInitializer;
    }

    public String getConstructorInitializer() {
        return constructorInitializer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CSConstructor that = (CSConstructor) o;
        return Objects.equals(constructorInitializer, that.constructorInitializer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), constructorInitializer);
    }
}
