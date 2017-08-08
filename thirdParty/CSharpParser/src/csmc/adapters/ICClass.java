package csmc.adapters;

import java.util.List;
import java.util.Map;

public interface ICClass {
    String getClassName();

    List<String> getChildrenClasses();

    List<String> getClassesUsed();

    List<IAttribute> getClassAttributes();

    List<IAttribute> getPublicClassAttributes();

    List<IMethod> getPublicClassMethods();

    List<IMethod> getMethods();

    String getParentClassName();

    void setClassName(String className);

    void setChildrenClasses(List<String> childrenClasses);

    void setClassesUsed(List<String> classesUsed);

    void setClassAttributes(List<IAttribute> classAttributes);

    void setClassMethods(Map<String, String> classMethods);

    void setClassMethods(List<IMethod> classMethods);

    void setParentClassName(String parentClassName);
}
