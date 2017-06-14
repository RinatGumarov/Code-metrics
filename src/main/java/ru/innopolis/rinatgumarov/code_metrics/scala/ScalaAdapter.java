package ru.innopolis.rinatgumarov.code_metrics.scala;

import main.metrics.Metrics;
import main.parser.ParseException;
import main.parser.Parser;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Rinat on 08.06.17.
 */
public class ScalaAdapter {

    public static void run(String path) {
        File d = new File(path);
        FilenameFilter scalaFileFilter = (dir, name) -> name.endsWith(".scala");
        File[] files = d.listFiles(scalaFileFilter);

        for (File file : files) {
            try {
                Metrics metrics = Parser.parse(path + file.getName());

                String query = "INSERT INTO scala_metrics (full_name, valDeclCount, varDeclCount, funcDeclCount," +
                        " funcDefCount, classDefCount, importCount, whileCount, ifCount, " +
                        "assignCount, throwCount, returnCount, tryCount, forCount, annotationCount," +
                        " objectDefCount, traitDefCount, packageDefCount) " +
                        "VALUES (" + "'" +
                        (path + file.getName()) + "'" + ", " +
                        metrics.getValDeclCount() + ", " +
                        metrics.getVarDeclCount() + ", " +
                        metrics.getFuncDeclCount() + ", " +
                        metrics.getFuncDefCount() + ", " +
                        metrics.getClassDefCount() + ", " +
                        metrics.getImportCount() + ", " +
                        metrics.getWhileCount() + ", " +
                        metrics.getIfCount() + ", " +
                        metrics.getAssignCount() + ", " +
                        metrics.getThrowCount() + ", " +
                        metrics.getReturnCount() + ", " +
                        metrics.getTryCount() + ", " +
                        metrics.getForCount() + ", " +
                        metrics.getAnnotationCount() + ", " +
                        metrics.getObjectDefCount() + ", " +
                        metrics.getTraitDefCount() + ", " +
                        metrics.getPackageDefCount() +
                        ");";
                Database db = Database.getINSTANCE();
                db.write(query);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

