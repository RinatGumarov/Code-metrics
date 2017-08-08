package csmc.adapters;

import java.util.List;

public interface IMethod {
    String getName();

    String getAccessSpec();

    List<String> getGlobalFields();

    String getClassName();

    List<String> getFields();

    String getSource();

    List<String> getMethodsUsed();

    void setName(String name);

    void setAccessSpec(String accessSpec);

    void setGlobalFields(List<String> globalFields);

    void setClassName(String className);

    void setFields(List<String> fields);

    void setSource(String source);

    void setMethodsUsed(List<String> methodsUsed);
}
