package swiftParser;

public interface MetricsProtocol {

    /* =================================
                   Numbers
       ================================= */

    int linesOfCodeCount();

    int methodsCount();

    int attributesCount();

    /* =================================
                   Complexity
       ================================= */

    /*
        The sum of complexity for all methods in a class
     */
    double weightedMethodComplexity();

    /*
        The sum of complexity for all methods in a class based on the information flow
     */
    double classComplexity();

    /* =================================
                   Coupling
       ================================= */

    /*
        The number of other classes to which a class object is coupled
      */
    int coupledToOtherClassesCount();

    /*
        The number of ADT(Abstract Data Type) instances defined in a class
    */
    int abstractDataTypesInClassCount();

}
