package ru.innopolis.rinatgumarov.code_metrics.swift;

import ru.innopolis.rinatgumarov.code_metrics.db.Database;
import swiftParser.JavaSwift;
import swiftParser.Metrics;
import swiftParser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rinat on 08.06.17.
 */
public class SwiftAdapter {
    public static void run(String path) {
        File d = new File(path);
        List<File> files = Arrays.stream(d.listFiles())
                .filter(file -> file.isDirectory() || file.getName().endsWith(".swift"))
                .collect(Collectors.toList());
        if (!files.isEmpty())
            for (File file : files) {
                try {
                    if (file.isDirectory())
                        run(path + file.getName() + "/");
                    else {
                        JavaSwift.parse(path + file.getName());
                        String query = "INSERT INTO swift_metrics (full_name, functionCount, classCount, variablesCount," +
                                " constantCount, loopsCount, conditionsCount) " +
                                "VALUES (" + "'" +
                                (path + file.getName()) + "'" + ", " +
                                Metrics.INSTANCE.getFunctionCount() + ", " +
                                Metrics.INSTANCE.getClassCount() + ", " +
                                Metrics.INSTANCE.getVariablesCount() + ", " +
                                Metrics.INSTANCE.getConstantCount() + ", " +
                                Metrics.INSTANCE.getLoopsCount() + ", " +
                                Metrics.INSTANCE.getConditionsCount() +
                                ");";
                        Database db = Database.getINSTANCE();
                        System.out.println(query);
                        db.write(query);
                        Metrics.INSTANCE.clear();
                    }
                } catch (FileNotFoundException | ParseException e) {
                    e.printStackTrace();
                }

            }
    }
}

