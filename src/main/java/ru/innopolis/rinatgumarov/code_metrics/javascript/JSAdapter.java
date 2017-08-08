package ru.innopolis.rinatgumarov.code_metrics.javascript;

import javascriptParser.com.digiarea.es5.parser.ParseException;
import javascriptParser.metrics.MetricsParser;
import org.apache.log4j.Logger;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rinat on 20.06.17.
 */
public class JSAdapter {

    static Logger logger = Logger.getLogger(JSAdapter.class.getName());

    public static void run(String path) {
        File d = new File(path);
        try {
            List<File> files = Arrays.stream(d.listFiles())
                    .filter(file -> file.isDirectory() || file.getName().endsWith(".js"))
                    .collect(Collectors.toList());
            if (!files.isEmpty())
                for (File file : files) {
                    try {
                        if (file.isDirectory())
                            run(file.getAbsolutePath() + "/");
                        else {
                            MetricsParser parser = new MetricsParser(
                                    new ByteArrayInputStream(
                                            Files.readAllBytes(Paths.get(file.getPath(), new String[0]))));
                            String query = "INSERT INTO js_metrics (full_name, linesOfCode, numberOfComments," +
                                    " numberOfGlobalVars, numberOfVars, numberOfFunctions, averageFunctionSize," +
                                    " numberOfLoops, numberOfConditions) " +
                                    "VALUES (" + "'" +
                                    (path + file.getName()) + "'" + ", " +
                                    parser.linesOfCode() + ", " +
                                    parser.numberOfComments() + ", " +
                                    parser.numberOfGlobalVars() + ", " +
                                    parser.numberOfVars() + ", " +
                                    parser.numberOfFunctions() + ", " +
                                    parser.averageFunctionSize() + ", " +
                                    parser.numberOfLoops() + ", " +
                                    parser.numberOfConditions() +
                                    ");";
                            Database db = Database.getINSTANCE();
                            db.write(query);
                            logger.info(file.getAbsolutePath() + " - successful");
                        }
                    } catch (ParseException | IOException e) {
                        logger.info("Parse exception in " + file.getAbsolutePath() + " with message: " + e.getMessage());
                    }
                }
        } catch (NullPointerException e) {
            logger.info("Directory " + path + " is empty.");
        }
    }
}
