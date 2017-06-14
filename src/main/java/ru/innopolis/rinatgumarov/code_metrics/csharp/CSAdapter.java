package ru.innopolis.rinatgumarov.code_metrics.csharp;

import csmc.javacc.parse.ParseDriver;
import csmc.metrics.CKMetric;
import csmc.metrics.CKMetricsExtractor;
import csmc.outformat.CSVBuilder;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Created by Rinat on 13.06.17.
 */
public class CSAdapter {
    public static void run(String path) {
        File d = new File(path);
        FilenameFilter scalaFileFilter = (dir, name) -> name.endsWith(".cs");
        File[] files = d.listFiles(scalaFileFilter);

        for (File file : files) {
            ParseDriver parseDriver = new ParseDriver();
            parseDriver.parse(path + file.getName());
            List<CKMetric> metrics = CKMetricsExtractor.fromNamespace(parseDriver.getGlobalNamespace());
            for (CKMetric metric : metrics) {
                String query = "INSERT INTO csharp_metrics (full_name, className," +
                        " wmc, dit," +
                        " noc, cbo, rfc, lcom) " +
                        "VALUES (" + "'" +
                        (path + file.getName()) + "'" + ", " +
                        "'" + metric.getClassName() + "'" + ", " +
                        metric.getWmc() + ", " +
                        metric.getDit() + ", " +
                        metric.getNoc() + ", " +
                        metric.getCbo() + ", " +
                        metric.getRfc() + ", " +
                        metric.getLcom()+
                        ");";
                System.out.println(query);
                Database db = Database.getINSTANCE();
                db.write(query);
            }
        }
    }
}
