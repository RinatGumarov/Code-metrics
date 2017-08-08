package swiftParser;

public class Metrics {

    public static final Metrics INSTANCE = new Metrics();

    int functionCount = 0;
    int classCount = 0;
    int variablesCount = 0;
    int constantCount = 0;
    int loopsCount = 0;
    int conditionsCount = 0;


    public void clear() {
        this.functionCount = 0;
        this.classCount = 0;
        this.variablesCount = 0;
        this.constantCount = 0;
        this.loopsCount = 0;
        this.conditionsCount = 0;
    }

    public int getFunctionCount() {
        return functionCount;
    }

    public void setFunctionCount(int functionCount) {
        this.functionCount = functionCount;
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    public int getVariablesCount() {
        return variablesCount;
    }

    public void setVariablesCount(int variablesCount) {
        this.variablesCount = variablesCount;
    }

    public int getConstantCount() {
        return constantCount;
    }

    public void setConstantCount(int constantCount) {
        this.constantCount = constantCount;
    }

    public int getLoopsCount() {
        return loopsCount;
    }

    public void setLoopsCount(int loopsCount) {
        this.loopsCount = loopsCount;
    }

    public int getConditionsCount() {
        return conditionsCount;
    }

    public void setConditionsCount(int conditionsCount) {
        this.conditionsCount = conditionsCount;
    }
}
