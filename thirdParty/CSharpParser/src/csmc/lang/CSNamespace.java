package csmc.lang;

import java.util.*;

public class CSNamespace {
    private String name;
    private CSNamespace parent;

    private Set<String> imports;
    private Set<String> staticImports;
    private Map<String, String> aliases;

    private Set<CSNamespace> namespaces;
    private Set<CSClass> classes;

    public CSNamespace(String name, CSNamespace parent) {
        this.name = name;
        this.parent = parent;
        this.imports = new HashSet<>();
        this.staticImports = new HashSet<>();
        this.aliases = new HashMap<>();
        this.namespaces = new HashSet<>();
        this.classes = new HashSet<>();
        this.aliases.put("global", "global");
    }

    public Set<String> getImports() {
        return imports;
    }

    public Set<String> getStaticImports() {
        return staticImports;
    }

    public void addStaticImport(String importName) {
        this.staticImports.add(importName);
    }

    public void addImport(String importName) {
        this.imports.add(importName);
    }

    public Map<String, String> getAliases() {
        return aliases;
    }

    public String searchAlias(String aliasName) {
        return aliases.containsKey(aliasName) ? aliases.get(aliasName) : parent == null ? null : parent.searchAlias(aliasName);
    }

    public void addAlias(String aliasName, String importName) {
        if (this.aliases.containsKey(aliasName)) {
            throw new IllegalArgumentException("Namespace "
                    + this.toString()
                    + "already contains alias "
                    + aliasName + " = " + this.aliases.get(aliasName));
        }
        this.aliases.put(aliasName, importName);
    }

    public String getName() {
        return name;
    }

    public CSNamespace getParent() {
        return parent;
    }

    public Set<CSNamespace> getNamespaces() {
        return namespaces;
    }

    public Set<CSClass> getClasses() {
        return classes;
    }

    public void addNamespace(CSNamespace namespace) {
        namespaces.add(namespace);
    }

    public void addClass(CSClass csClass) {
        classes.add(csClass);
    }

    @Override
    public String toString() {
        return parent == null || parent.getName().equals("global") ? name : parent.toString() + "." + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSNamespace that = (CSNamespace) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}
