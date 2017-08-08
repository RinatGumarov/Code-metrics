package main;

import main.metrics.ComplexMetrics;
import main.metrics.Metrics;
import main.parser.ParseException;
import main.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Main {

    public static void main(String[] args) {

        try {
            File folder = new File(args[0]);

            FilenameFilter scalaFileFilter = (dir, name) -> name.endsWith(".scala");
            File[] files = folder.listFiles(scalaFileFilter);

            FileWriter fw = new FileWriter("metrics.csv");
            fw.write("File name; " +
                    "Val decl; Var decl; Total variables; " +
                    "Func decl; Func def; Total functionc; " +
                    "Class def; Object def; Trait def; " +
                    "Annotations; Imports; Packages; " +
                    "While; For; If; " +
                    "Try; Throw; Return; " +
                    "Assignment; Weighted methods; Number of Public Meth&Vars" +
                    "\n"
            );

            for (File file : files) {
                try {
                    Metrics metrics = Parser.parse(args[0] + file.getName());
//                    ComplexMetrics cmetrics = Parser.parseComplex(args[0] + file.getName());
                    fw.write(file.getName() + ';' +
                            metrics.getValDeclCount() + ";" + metrics.getVarDeclCount() + ";" + metrics.getTotalVariables() + ";" +
                            metrics.getFuncDeclCount() + ";" + metrics.getFuncDefCount() + ";" + metrics.getTotalFunctions() + ";" +
                            metrics.getClassDefCount() + ";" + metrics.getObjectDefCount() + ";" + metrics.getTraitDefCount()+ ";" +
                            metrics.getAnnotationCount() + ";" + metrics.getImportCount() + ";" + metrics.getPackageDefCount() + ";" +
                            metrics.getWhileCount() + ";" + metrics.getForCount() + ";" + metrics.getIfCount() + ";" +
                            metrics.getTryCount() + ";" + metrics.getThrowCount() + ";" + metrics.getReturnCount() + ";" +
//                            metrics.getAssignCount() + ";" + cmetrics.getWMC() +";" + cmetrics.getNPM() +
                            "\n"
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            fw.close();
            System.out.println("Report had been generated successfully!");
        } catch (NoSuchFileException e) {
            System.out.println("Such folder does not exist");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Specify the name of directory with sources as an argument");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
