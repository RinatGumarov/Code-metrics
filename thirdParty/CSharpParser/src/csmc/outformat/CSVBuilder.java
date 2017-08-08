package csmc.outformat;

import csmc.metrics.CKMetric;

import java.util.List;

/**
 * Builds csv representation of CK metrics
 */
public class CSVBuilder {

    public static String fromList(List<CKMetric> metrics) {
        StringBuilder sb = new StringBuilder();
        sb.append("ClassName,WMC,DIT,NOC,CBO,RFC,LCOM\n");
        metrics.forEach(metric -> {
            sb.append(metric.getClassName()); sb.append(",");
            sb.append(metric.getWmc()); sb.append(",");
            sb.append(metric.getDit()); sb.append(",");
            sb.append(metric.getNoc()); sb.append(",");
            sb.append(metric.getCbo()); sb.append(",");
            sb.append(metric.getRfc()); sb.append(",");
            sb.append(metric.getLcom()); sb.append("\n");
        });
        return sb.toString();
    }

}
