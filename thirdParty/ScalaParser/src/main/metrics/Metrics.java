package main.metrics;

public class Metrics {

    private int valDeclCount;
    private int varDeclCount;
    private int funcDeclCount;
    private int funcDefCount;
    private int classDefCount;
    private int importCount;
    private int whileCount;
    private int ifCount;
    private int assignCount;
    private int throwCount;
    private int returnCount;
    private int tryCount;
    private int forCount;
    private int annotationCount;
    private int objectDefCount;
    private int traitDefCount;
    private int packageDefCount;

    public int getValDeclCount() {
        return valDeclCount;
    }

    public void setValDeclCount(int valDeclCount) {
        this.valDeclCount = valDeclCount;
    }

    public int getVarDeclCount() {
        return varDeclCount;
    }

    public void setVarDeclCount(int varDeclCount) {
        this.varDeclCount = varDeclCount;
    }

    public int getFuncDeclCount() {
        return funcDeclCount;
    }

    public void setFuncDeclCount(int funcDeclCount) {
        this.funcDeclCount = funcDeclCount;
    }

    public int getFuncDefCount() {
        return funcDefCount;
    }

    public void setFuncDefCount(int funcDefCount) {
        this.funcDefCount = funcDefCount;
    }

    public int getClassDefCount() {
        return classDefCount;
    }

    public void setClassDefCount(int classDefCount) {
        this.classDefCount = classDefCount;
    }

    public int getTotalVariables(){
        return valDeclCount + varDeclCount;
    }

    public int getTotalFunctions(){
        return funcDeclCount + funcDefCount;
    }

    public int getImportCount() {
        return importCount;
    }

    public void setImportCount(int importCount) {
        this.importCount = importCount;
    }

    public int getWhileCount() {
        return whileCount;
    }

    public void setWhileCount(int whileCount) {
        this.whileCount = whileCount;
    }

    public void setIfCount(int ifCount) {
        this.ifCount = ifCount;
    }

    public int getIfCount() {
        return ifCount;
    }

    public int getAssignCount() {
        return assignCount;
    }

    public void setAssignCount(int assignCount) {
        this.assignCount = assignCount;
    }

    public int getThrowCount() {
        return throwCount;
    }

    public void setThrowCount(int throwCount) {
        this.throwCount = throwCount;
    }

    public int getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(int returnCount) {
        this.returnCount = returnCount;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public int getForCount() {
        return forCount;
    }

    public void setForCount(int forCount) {
        this.forCount = forCount;
    }

    public void setAnnotationCount(int annotationCount) {
        this.annotationCount = annotationCount;
    }

    public int getAnnotationCount() {
        return annotationCount;
    }

    public int getObjectDefCount() {
        return objectDefCount;
    }

    public void setObjectDefCount(int objectDefCount) {
        this.objectDefCount = objectDefCount;
    }

    public int getTraitDefCount() {
        return traitDefCount;
    }

    public void setTraitDefCount(int traitDefCount) {
        this.traitDefCount = traitDefCount;
    }

    public void setPackageDefCount(int packageDefCount) {
        this.packageDefCount = packageDefCount;
    }

    public int getPackageDefCount() {
        return packageDefCount;
    }
}
