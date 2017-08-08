package csmc.metrics;

import csmc.javacc.parse.util.Tuple2;
import csmc.lang.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple CKMetric representation
 */
public class CKMetric {
    private final String className;

    private int wmc; // Weighted Methods per Class
    private int dit; // Depth of inheritance tree
    private int noc; // Number of Children
    private int cbo; // Coupling Between Object Classes - # classes to which current is coupled
    private int rfc; // Response Set for a Class. Consider doing RFC'
    private int lcom; // Lack of Cohesion of Methods

    private CKMetric(String className) {
        this.className = className;
    }

    /**
     * Fully-qualified name of the class
     */
    public String getClassName() {
        return className;
    }

    /**
     * Weighted method per class (WMC).
     * Returns number of methods, operators, properties, indexers, constructors,
     * static constructors and destructors.
     */
    public int getWmc() {
        return wmc;
    }

    private static int calculateWmc(CSClass csClass) {
        return csClass.getMethods().size()
                + csClass.getOperators().size()
                + csClass.getProperties().size()
                + csClass.getIndexers().size()
                + csClass.getConstructors().size()
                + csClass.getStaticConstructors().size()
                + csClass.getDestructors().size();
    }

    /**
     * Depth of inheritance tree (DIT).
     */
    public int getDit() {
        return dit;
    }

    private static int calculateDit(CSClass csClass) {
        return csClass.getParent() == null ? 0 : 1 + calculateDit(csClass.getParent());
    }

    /**
     * Number of children (NOC).
     */
    public int getNoc() {
        return noc;
    }

    /**
     * Coupling between Object Classes (CBO) is a number of classes to which a given class is
     * coupled. Two classes are coupled when the methods declared in one class use the methods
     * or instance variables defined by the other class. The uses relationship can go either way:
     * both uses and used-by are considered, but only once.
     *
     * Simple version of CBO that calculates only classes and types used in a given type ('uses' relationship).
     * Considers also primitive types.
     * TODO: calculate classes number of cases where the given class is used ('used-by' relationship).
     */
    public int getCbo() {
        return cbo;
    }

    private static int calculateCbo(CSClass csClass) {
        Set<CSClass> distinctClassesUsed = new HashSet<>();
        Set<String> distinctTypesUsed = new HashSet<>();

        // Collect classes used by given
        distinctClassesUsed.addAll(csClass.getUsedClasses());

        // Collect types used by fields, constants, and events
        Stream.of(csClass.getFields(), csClass.getConstants(), csClass.getEvents())
                .flatMap(Collection::stream)
                .map(CSClassEntity::getType).forEach(distinctTypesUsed::add);

        // Collect types used by methods, operators, destructors, properties, indexers, constructors and static constructors
        Supplier<Stream<CSMethod>> allMethodStreamSupplier = () -> {
            Stream<CSMethod> allMethodStream = Stream.of(
                    csClass.getMethods(), csClass.getOperators(), csClass.getDestructors(), csClass.getConstructors(), csClass.getStaticConstructors())
                    .flatMap(Collection::stream);
            allMethodStream = Stream.concat(allMethodStream, csClass.getProperties().stream().map(CSProperty::getGetter));
            allMethodStream = Stream.concat(allMethodStream, csClass.getProperties().stream().map(CSProperty::getSetter));
            allMethodStream = Stream.concat(allMethodStream, csClass.getIndexers().stream().map(CSProperty::getGetter));
            allMethodStream = Stream.concat(allMethodStream, csClass.getIndexers().stream().map(CSProperty::getSetter));
            allMethodStream = allMethodStream.filter(Objects::nonNull);
            return allMethodStream;
        };

        // Handle method formal parameters
        allMethodStreamSupplier.get().map(CSMethod::getFormalParameters).flatMap(Collection::stream)
                .map(CSClassEntity::getType).forEach(distinctTypesUsed::add);

        // Handle method local variables
        allMethodStreamSupplier.get().map(CSMethod::getLocalVariables).flatMap(Collection::stream)
                .map(CSClassEntity::getType).forEach(distinctTypesUsed::add);

        // Handle return types
        allMethodStreamSupplier.get().map(CSMethod::getType).forEach(distinctTypesUsed::add);

        // Handle method classes of invoked methods
        allMethodStreamSupplier.get().map(CSMethod::getInvokedMethods).flatMap(Collection::stream)
                .map(CSMethod::getCsClass).forEach(distinctClassesUsed::add);

        // Collect types used by indexers
        csClass.getIndexers().stream()
                .map(CSIndexer::getFormalParameters).flatMap(Collection::stream)
                .map(CSParameter::getType).forEach(distinctTypesUsed::add);

        // Delete repetitions
        distinctClassesUsed.remove(csClass);
        distinctTypesUsed.remove(csClass.getName());
        distinctTypesUsed.remove(csClass.toString());
        distinctTypesUsed.removeAll(distinctClassesUsed.stream().map(CSClass::getName).collect(Collectors.toSet()));
        distinctTypesUsed.removeAll(distinctClassesUsed.stream().map(CSClass::toString).collect(Collectors.toSet()));


        return distinctClassesUsed.size() + distinctTypesUsed.size();
    }

    /**
     * Response for a Class (RFC and RFC’). The response set of a class is a set of methods
     * that can potentially be executed in response to a message received by an object of that
     * class. RFC is the number of methods in this set; a measure of the potential communication
     * between the given class and other classes. RFC counts only the first level of calls
     * outside of the class.
     */
    public int getRfc() {
        return rfc;
    }

    private static int calculateRfc(CSClass csClass) {
        Set<CSMethod> rfc = new HashSet<>();

        Stream<CSMethod> allMethodStream = Stream.of(
                csClass.getMethods(), csClass.getOperators(), csClass.getDestructors(), csClass.getConstructors(), csClass.getStaticConstructors())
                .flatMap(Collection::stream);
        allMethodStream = Stream.concat(allMethodStream, csClass.getProperties().stream().map(CSProperty::getGetter));
        allMethodStream = Stream.concat(allMethodStream, csClass.getProperties().stream().map(CSProperty::getSetter));
        allMethodStream = Stream.concat(allMethodStream, csClass.getIndexers().stream().map(CSProperty::getGetter));
        allMethodStream = Stream.concat(allMethodStream, csClass.getIndexers().stream().map(CSProperty::getSetter));
        allMethodStream = allMethodStream.filter(Objects::nonNull);
        allMethodStream.peek(rfc::add).map(CSMethod::getInvokedMethods).flatMap(Collection::stream).forEach(rfc::add);

        return rfc.size();
    }

    /**
     * Lack of Cohesion of Methods (LCOM) is the number of pairs of methods in a class that
     * don't have at least one field in common minus the number of pairs of methods in the
     * class that do share at least one field. When this value is negative, the metric value
     * is set to 0.
     */
    public int getLcom() {
        return lcom;
    }

    private static int calculateLcom(CSClass csClass) {
        Set<Tuple2<CSMethod, CSMethod>> doNotShare = new HashSet<>();
        Set<Tuple2<CSMethod, CSMethod>> doShare = new HashSet<>();

        List<CSMethod> methodList = new ArrayList<>(csClass.getMethods());
        for (int i = 0; i < methodList.size() - 1; i++) {
            for (int j = i + 1; j < methodList.size(); j++) {
                boolean flag = false;
                for (CSParameter param1 : methodList.get(i).getLocalVariables()) {
                    boolean share = methodList.get(j).getLocalVariables().stream().anyMatch(p -> p.equals(param1));
                    if (share) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    doShare.add(new Tuple2<>(methodList.get(i), methodList.get(j)));
                } else {
                    doNotShare.add(new Tuple2<>(methodList.get(i), methodList.get(j)));
                }
            }
        }
        int difference = doNotShare.size() - doShare.size();
        return Integer.max(0, difference);
    }

    public static CKMetric fromClass(CSClass csClass) {
        CKMetric metric = new CKMetric(csClass.toString());

        metric.wmc = calculateWmc(csClass);
        metric.dit = calculateDit(csClass);
        metric.noc = csClass.getChildren().size();
        metric.cbo = calculateCbo(csClass);
        metric.rfc = calculateRfc(csClass);
        metric.lcom = calculateLcom(csClass);

        return metric;
    }
}
