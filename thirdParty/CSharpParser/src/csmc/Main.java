package csmc;

import csmc.javacc.parse.ParseDriver;
import csmc.metrics.CKMetric;
import csmc.metrics.CKMetricsExtractor;
import csmc.outformat.CSVBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar CSharpMetricsCollectorJava.jar input_dir_or_file output_file.csv");
            return;
        }
        ParseDriver parseDriver = new ParseDriver();
        parseDriver.parse(args[0]);
//        MetricsAdapter adapter = new MetricsAdapter<>(parseDriver.getGlobalNamespace(), Attribute.class, Method.class, CClass.class);
//        Map<String, ICClass> allClasses = adapter.getAllClasses();
        List<CKMetric> metrics = CKMetricsExtractor.fromNamespace(parseDriver.getGlobalNamespace());
        String csv = CSVBuilder.fromList(metrics);
        try (FileWriter fileWriter = new FileWriter(args[1])) {
            fileWriter.write(csv);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
