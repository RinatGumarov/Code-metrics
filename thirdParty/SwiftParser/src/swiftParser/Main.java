package swiftParser;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Main {

    public static void main(String[] args) {

        try {
            File folder = new File(args[0]);

            FilenameFilter fileFilter = (dir, name) -> name.endsWith(".swift");
            File[] files = folder.listFiles(fileFilter);

            FileWriter fw = new FileWriter("results.csv");
            fw.write("File name; " +
                    "Total variables; Total constant; Total loops; " +
                    "Total classes; Total conditions; Total functions\n"
            );

            for (File file : files) {
                try {
                    JavaSwift.parse(args[0] + file.getName());
                    fw.write(file.getName() + ';' +
                            Metrics.INSTANCE.variablesCount + ";"
                            + Metrics.INSTANCE.constantCount + ";"
                            + Metrics.INSTANCE.loopsCount + ";"
                            + Metrics.INSTANCE.classCount + ";"
                            + Metrics.INSTANCE.conditionsCount + ";"
                            + Metrics.INSTANCE.functionCount+ "\n"
                    );
                    Metrics.INSTANCE.clear();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fw.write('\n');
            }

            fw.close();
            System.out.println("Report had been generated successfully!");
        } catch (NoSuchFileException e) {
            System.out.println("Sorry, could not find such folder.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, don't forget to pass name of directory with sources as an argument.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
