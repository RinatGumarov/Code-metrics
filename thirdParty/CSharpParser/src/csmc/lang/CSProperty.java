package csmc.lang;

import java.util.Objects;
import java.util.Set;

public class CSProperty extends CSClassEntity {
    private CSMethod getter;
    private CSMethod setter;

    public CSProperty(CSClass csClass, Set<CSModifier> modifiers, String type, String name, CSMethod getter, CSMethod setter) {
        super(csClass, modifiers, type, name);
        this.getter = getter;
        this.setter = setter;
    }

    public CSMethod getGetter() {
        return getter;
    }

    public CSMethod getSetter() {
        return setter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CSProperty that = (CSProperty) o;
        return Objects.equals(getter, that.getter) &&
                Objects.equals(setter, that.setter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getter, setter);
    }
}
