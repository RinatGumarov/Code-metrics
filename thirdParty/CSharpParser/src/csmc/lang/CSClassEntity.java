package csmc.lang;

import java.util.Set;
import java.util.Objects;

public abstract class CSClassEntity {
    private Set<CSModifier> modifiers;
    private String type;
    private String name;
    private CSClass csClass;

    public CSClassEntity(CSClass csClass, Set<CSModifier> modifiers, String type, String name) {
        this.csClass = csClass;
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
    }

    public CSClass getCsClass() {
        return csClass;
    }

    public Set<CSModifier> getModifiers() {
        return modifiers;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSClassEntity that = (CSClassEntity) o;
        return Objects.equals(modifiers, that.modifiers) &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifiers, type, name);
    }
}
