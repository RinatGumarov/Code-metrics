package ru.innopolis.rinatgumarov.code_metrics.clojure;

import clojureParser.ClojureParser;
import clojureParser.ParseException;
import org.apache.log4j.Logger;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Rinat on 17.06.17.
 */
public class ClojureAdapter {

    private static ClojureParser INSTANCE;
    private static Logger logger = Logger.getLogger(ClojureAdapter.class.getName());

    public static void run(String path) {
        File d = new File(path);
        try {
            List<File> files = Arrays.stream(d.listFiles())
                    .filter(file -> file.isDirectory() || file.getName().endsWith(".clj"))
                    .collect(Collectors.toList());

            if (!files.isEmpty())
                for (File file : files) {
                    try {
                        if (file.isDirectory())
                            run(file.getAbsolutePath() + "/");
                        else {
                            initClojureParser(file.getAbsolutePath());
                            ClojureParser.S();
                            String query = "INSERT INTO clojure_metrics (full_name, forms, literals," +
                                    " keywords, symbols, lists, vectors, maps, sets, nestiness, mxnestiness," +
                                    " functions, pfunctions, macros, multis, methods) " +
                                    "VALUES (" + "'" +
                                    (path + file.getName()) + "'" + ", " +
                                    ClojureParser.forms + ", " +
                                    ClojureParser.literals + ", " +
                                    ClojureParser.keywords + ", " +
                                    ClojureParser.symbols + ", " +
                                    ClojureParser.lists + ", " +
                                    ClojureParser.vectors + ", " +
                                    ClojureParser.maps + ", " +
                                    ClojureParser.sets + ", " +
                                    ClojureParser.nestiness + ", " +
                                    ClojureParser.mxnestiness + ", " +
                                    ClojureParser.functions + ", " +
                                    ClojureParser.pfunctions + ", " +
                                    ClojureParser.macros + ", " +
                                    ClojureParser.multis + ", " +
                                    ClojureParser.methods +
                                    ");";
                            Database db = Database.getINSTANCE();
                            db.write(query);
                            logger.info(file.getAbsolutePath() + " - successful");
                        }
                    } catch (ParseException e) {
                        logger.info("Parse exception in " + file.getAbsolutePath() + " with message: " + e.getMessage());
                    }
                }
        } catch (NullPointerException e) {
            logger.info("Directory " + path + " is empty.");
        }
    }

    private static ClojureParser initClojureParser(String path) {
        try {
            if (INSTANCE == null) {
                INSTANCE = new ClojureParser(new FileReader(path));
                return INSTANCE;
            } else {
                ClojureParser.ReInit(new FileReader(path));
                ClojureParser.forms = 0;
                ClojureParser.literals = 0;
                ClojureParser.keywords = 0;
                ClojureParser.symbols = 0;
                ClojureParser.lists = 0;
                ClojureParser.vectors = 0;
                ClojureParser.maps = 0;
                ClojureParser.sets = 0;
                ClojureParser.nestiness = 0;
                ClojureParser.mxnestiness = 0;
                ClojureParser.functions = 0;
                ClojureParser.pfunctions = 0;
                ClojureParser.macros = 0;
                ClojureParser.multis = 0;
                ClojureParser.methods = 0;
                return INSTANCE;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
