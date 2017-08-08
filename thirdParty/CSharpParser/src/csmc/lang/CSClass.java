package csmc.lang;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

public class CSClass {
    private Set<CSModifier> modifiers;
    private String name;
    private Set<String> typeParameters;
    private CSClass parent;
    private Set<CSClass> children;
    private Set<CSClass> usedClasses;
    private CSNamespace namespace;

    private Set<CSParameter> constants;
    private Set<CSParameter> fields;
    private Set<CSParameter> events;
    private Set<CSMethod> methods;
    private Set<CSMethod> operators;
    private Set<CSMethod> destructors;
    private Set<CSProperty> properties;
    private Set<CSIndexer> indexers;
    private Set<CSConstructor> constructors;
    private Set<CSConstructor> staticConstructors;

    public CSClass(String className, CSNamespace namespace) {
        this.modifiers = new HashSet<>();
        this.name = className;
        this.typeParameters = new HashSet<>();
        this.parent = null;
        this.children = new HashSet<>();
        this.usedClasses = new HashSet<>();
        this.namespace = namespace;

        this.constants = new HashSet<>();
        this.fields = new HashSet<>();
        this.events = new HashSet<>();
        this.methods = new HashSet<>();
        this.operators = new HashSet<>();
        this.destructors = new HashSet<>();
        this.properties = new HashSet<>();
        this.indexers = new HashSet<>();
        this.constructors = new HashSet<>();
        this.staticConstructors = new HashSet<>();
    }

    public CSNamespace getNamespace() {
        return namespace;
    }

    public Set<CSModifier> getModifiers() {
        return modifiers;
    }

    public Set<String> getTypeParameters() {
        return typeParameters;
    }

    public Set<CSParameter> getConstants() {
        return constants;
    }

    public Set<CSParameter> getFields() {
        return fields;
    }

    public Set<CSParameter> getEvents() {
        return events;
    }

    public Set<CSMethod> getMethods() {
        Set<CSMethod> result = new HashSet<>(methods);
        if (parent != null)
            result.addAll(parent.getMethods());
        return result;
    }

    public Set<CSMethod> getOperators() {
        return operators;
    }

    public Set<CSMethod> getDestructors() {
        return destructors;
    }

    public Set<CSProperty> getProperties() {
        return properties;
    }

    public Set<CSIndexer> getIndexers() {
        return indexers;
    }

    public Set<CSConstructor> getConstructors() {
        return constructors;
    }

    public Set<CSConstructor> getStaticConstructors() {
        return staticConstructors;
    }

    public String getName() {
        return name;
    }

    public CSClass getParent() {
        return parent;
    }

    public Set<CSClass> getUsedClasses() {
        return usedClasses;
    }

    public Set<CSClass> getChildren() {
        return children;
    }

    public void setParent(CSClass parent) {
        if (this.parent == null)
            this.parent = parent;
        else
            throw new IllegalArgumentException("A class " + name
                    + " already has parent " + this.parent.getName()
                    + ", trying to replace it with " + parent.getName());

    }

    public void addModifier(CSModifier modifier) {
        modifiers.add(modifier);
    }

    public void addTypeParameter(String typeParameter) {
        typeParameters.add(typeParameter);
    }

    public void addChild(CSClass child) {
        children.add(child);
        if (child.getParent() != this) {
            child.setParent(this);
        }
    }

    public void addConstant(CSParameter constant) {
        constants.add(constant);
    }

    public void addField(CSParameter field) {
        fields.add(field);
    }

    public void addEvent(CSParameter event) {
        events.add(event);
    }

    public void addMethod(CSMethod method) {
        methods.add(method);
    }

    public void addOperator(CSMethod operator) {
        operators.add(operator);
    }

    public void addDestructor(CSMethod destructor) {
        destructors.add(destructor);
    }

    public void addProperty(CSProperty property) {
        properties.add(property);
    }

    public void addIndexer(CSIndexer indexer) {
        indexers.add(indexer);
    }

    public void addConstructor(CSConstructor constructor) {
        constructors.add(constructor);
    }

    public void addStaticConstructor(CSConstructor staticConstructor) {
        staticConstructors.add(staticConstructor);
    }

    public void addUsedClass(CSClass csClass) {
        usedClasses.add(csClass);
    }

    @Override
    public String toString() {
        return namespace.toString().equals("global") ? name : namespace.toString() + "." + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSClass csClass = (CSClass) o;
        return Objects.equals(modifiers, csClass.modifiers) &&
                Objects.equals(name, csClass.name) &&
                Objects.equals(typeParameters, csClass.typeParameters) &&
                Objects.equals(parent, csClass.parent) &&
                Objects.equals(namespace, csClass.namespace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifiers, name, typeParameters, parent, namespace);
    }
}
