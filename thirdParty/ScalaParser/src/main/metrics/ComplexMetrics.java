package main.metrics;

public class ComplexMetrics {

    /**
     * WMC - Weighted methods per class
     */
    private double WMC;
    /**
     * Depth of Inheritance Tree
     */
    private int DIT;
    /**
     * Number of Children
     */
    private int NOC;
    /**
     * Coupling between object classes
     */
    private int CBO;
    /**
     * Response for a Class
     */
    private int RFC;
    /**
     * NPM -  Number of Public Methods and Variables
     */
    private int NPM;
    private int funcDeclCount;
    private int funcDefCount;

    private void calculateWMC() {
        this.setWMC(this.getFuncDeclCount() * 0.5 + this.getFuncDefCount());
    }

    public double getWMC() {
        return WMC;
    }

    public void setWMC(double WMC) {
        this.WMC = WMC;
    }

    public int getDIT() {
        return DIT;
    }

    public void setDIT(int DIT) {
        this.DIT = DIT;
    }

    public int getNOC() {
        return NOC;
    }

    public void setNOC(int NOC) {
        this.NOC = NOC;
    }

    public int getCBO() {
        return CBO;
    }

    public void setCBO(int CBO) {
        this.CBO = CBO;
    }

    public int getRFC() {
        return RFC;
    }

    public void setRFC(int RFC) {
        this.RFC = RFC;
    }

    public int getFuncDeclCount() {
        return funcDeclCount;
    }

    public void setFuncDeclCount(int funcDeclCount) {
        this.funcDeclCount = funcDeclCount;
        this.calculateWMC();
    }

    public int getFuncDefCount() {
        return funcDefCount;
    }

    public void setFuncDefCount(int funcDefCount) {
        this.funcDefCount = funcDefCount;
        this.calculateWMC();
    }

    public int getNPM() {
        return NPM;
    }

    public void setNPM(int NPM) {
        this.NPM = NPM;
    }
}
