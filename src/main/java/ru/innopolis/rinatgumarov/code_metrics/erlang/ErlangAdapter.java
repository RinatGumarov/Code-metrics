package ru.innopolis.rinatgumarov.code_metrics.erlang;

import erlang.parser.Erlang;
import org.apache.log4j.Logger;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rinat on 16.06.17.
 */
public class ErlangAdapter {

    private static Logger logger = Logger.getLogger(ErlangAdapter.class.getName());

    public static void run(String path) {
        File d = new File(path);
        try {
            List<File> files = Arrays.stream(d.listFiles())
                    .filter(file -> file.isDirectory() || file.getName().endsWith(".erl"))
                    .collect(Collectors.toList());
            if (!files.isEmpty())
                for (File file : files) {
                    try {
                        if (file.isDirectory())
                            run(path + file.getName() + "/");
                        else {
                            String code = Erlang.getStringFromFile(file.getAbsoluteFile());
                            int linesOfCode = Erlang.getLinesOfCode(code);
                            int numberOfComments = Erlang.getNumberOfComments(code);
                            int numberOfImports = Erlang.getNumberOfImports(code);
                            int numberOfExports = Erlang.getNumberOfExports(code);
                            int numberOfModules = Erlang.getNumberOfModules(code);
                            String query = "INSERT INTO erlang_metrics (full_name, linesOfCode, numberOfComments," +
                                    " numberOfImports, numberOfExports, numberOfModules) " +
                                    "VALUES (" + "'" +
                                    (path + file.getName()) + "'" + ", " +
                                    linesOfCode + ", " +
                                    numberOfComments + ", " +
                                    numberOfImports + ", " +
                                    numberOfExports + ", " +
                                    numberOfModules +
                                    ");";
                            Database db = Database.getINSTANCE();
                            db.write(query);
                            logger.info(file.getAbsolutePath() + " - successful");
                        }
                    } catch (IOException e) {
                        logger.info("Reading file " + file.getAbsolutePath() + " failed:" + e.getMessage());
                    }
                }
        }
        catch (NullPointerException e){
            logger.info("Directory " + path + " is empty.");
        }
    }
}
