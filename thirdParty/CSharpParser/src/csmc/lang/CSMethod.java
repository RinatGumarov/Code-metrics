package csmc.lang;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CSMethod extends CSClassEntity {
    private Set<CSParameter> formalParameters;
    private Set<String> typeParameters;
    private String body;
    private Set<CSParameter> localVariables;
    private Set<CSMethod> invokedMethods;

    public CSMethod(CSClass csClass, Set<CSModifier> modifiers, String type, String name, Set<CSParameter> formalParameters, Set<String> typeParameters, String body) {
        super(csClass, modifiers, type, name);
        this.formalParameters = formalParameters;
        this.typeParameters = typeParameters;
        this.body = body;
        this.localVariables = new HashSet<>();
        this.invokedMethods = new HashSet<>();
    }

    public Set<CSParameter> getFormalParameters() {
        return formalParameters;
    }

    public Set<String> getTypeParameters() {
        return typeParameters;
    }

    public String getBody() {
        return body;
    }

    public Set<CSParameter> getLocalVariables() {
        return localVariables;
    }

    public Set<CSMethod> getInvokedMethods() {
        return invokedMethods;
    }

    public void addLocalVariable(CSParameter variable) {
        localVariables.add(variable);
    }

    public void addInvokedMethod(CSMethod invokedMethod) {
        invokedMethods.add(invokedMethod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CSMethod csMethod = (CSMethod) o;
        return Objects.equals(formalParameters, csMethod.formalParameters) &&
                Objects.equals(typeParameters, csMethod.typeParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formalParameters, typeParameters);
    }
}
