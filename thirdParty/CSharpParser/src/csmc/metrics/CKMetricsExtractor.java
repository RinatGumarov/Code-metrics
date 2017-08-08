package csmc.metrics;

import csmc.lang.CSNamespace;

import java.util.ArrayList;
import java.util.List;

public class CKMetricsExtractor {

    private static void traverse(CSNamespace namespace, List<CKMetric> metrics) {
        namespace.getClasses().stream().map(CKMetric::fromClass).forEach(metrics::add);
        namespace.getNamespaces().forEach(n -> traverse(n, metrics));
    }

    public static List<CKMetric> fromNamespace(CSNamespace global) {
        List<CKMetric> metrics = new ArrayList<>();
        traverse(global, metrics);
        return metrics;
    }

}
