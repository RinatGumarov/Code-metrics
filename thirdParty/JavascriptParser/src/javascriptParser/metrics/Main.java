package javascriptParser.metrics;

import javascriptParser.com.digiarea.es5.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;


/**
 * Created by Anastasia on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        try {
            File folder = new File(args[0]);

            FilenameFilter txtFileFilter = (dir, name) -> name.endsWith(".js");
            File[] files = folder.listFiles(txtFileFilter);

            FileWriter fw = new FileWriter("metrics.csv");
            fw.write("file name, linesOfCode, numberOfComments, numberOfGlobalVars, numberOfVars, numberOfFunctions, averageFunctionSize, numberOfLoops, numberOfConditions\n");

            InputStream stream = null;

            for (File file : files) {
                stream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(file.getPath())));
                MetricsParser parser = new MetricsParser(stream);
                String delimiter = ";";

                String res = file.getName();
                res += delimiter;
                res += parser.linesOfCode();
                res += delimiter;
                res += parser.numberOfComments();
                res += delimiter;
                res += parser.numberOfGlobalVars();
                res += delimiter;
                res += parser.numberOfVars();
                res += delimiter;
                res += parser.numberOfFunctions();
                res += delimiter;
                res += parser.averageFunctionSize();
                res += delimiter;
                res += parser.numberOfLoops();
                res += delimiter;
                res += parser.numberOfConditions();
                res += '\n';

                fw.write(res);
            }

            fw.close();

            System.out.println("Report has been generated successfully! (check metrics.csv)");
        } catch (NoSuchFileException e) {
            System.out.println("Sorry, could not find such folder.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, don't forget to pass name of directory with sources as an argument.");
        } catch (FileNotFoundException e) {
            System.out.println("Close metrics.csv please, can't write there.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
