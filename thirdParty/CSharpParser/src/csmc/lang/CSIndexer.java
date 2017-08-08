package csmc.lang;

import java.util.Objects;
import java.util.Set;

public class CSIndexer extends CSProperty {
    private Set<CSParameter> formalParameters;

    public CSIndexer(CSClass csClass, Set<CSModifier> modifiers, String type, String name, CSMethod getter, CSMethod setter, Set<CSParameter> formalParameters) {
        super(csClass, modifiers, type, name, getter, setter);
        this.formalParameters = formalParameters;
    }

    public Set<CSParameter> getFormalParameters() {
        return formalParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CSIndexer csIndexer = (CSIndexer) o;
        return Objects.equals(formalParameters, csIndexer.formalParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formalParameters);
    }
}
