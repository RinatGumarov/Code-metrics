package ru.innopolis.rinatgumarov.code_metrics.python;

import pythonParser.ParseException;
import pythonParser.PythonGrammar;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rinat on 11.06.17.
 */
public class PythonAdapter {
    public static void run(String path) {
        File d = new File(path);
        List<File> files = Arrays.stream(d.listFiles())
                .filter(file -> file.isDirectory() || file.getName().endsWith(".py"))
                .collect(Collectors.toList());
        if (!files.isEmpty())
            for (File file : files) {
                try {
                    if (file.isDirectory())
                        run(path + file.getName() + "/");
                    else {
                        BufferedReader br = new BufferedReader(new FileReader(path + file.getName()));
                        PythonGrammar parser = new PythonGrammar(br);
                        parser.Start(System.out);
                        String complexityLevel = PythonGrammar.numberAllTokens < 100 ? "low" :
                                PythonGrammar.numberAllTokens < 150 ? "medium" :
                                        "high";
                        String query = "INSERT INTO python_metrics (full_name, numberLines," +
                                " numberAllTokens, numberTokensInLine," +
                                " numberClasses, numberImports, numberMethods, numberReturns," +
                                " numberCycles, complexityLevel) " +
                                "VALUES (" + "'" +
                                (path + file.getName()) + "'" + ", " +
                                PythonGrammar.numberLines + ", " +
                                PythonGrammar.numberAllTokens + ", " +
                                PythonGrammar.numberTokensInLine + ", " +
                                PythonGrammar.numberClasses + ", " +
                                PythonGrammar.numberImports + ", " +
                                PythonGrammar.numberMethods + ", " +
                                PythonGrammar.numberReturns + ", " +
                                PythonGrammar.numberCycles + ", " +
                                "'" + complexityLevel + "'" +
                                ");";
                        Database db = Database.getINSTANCE();
                        db.write(query);
                        PythonGrammar.clean();

                    }
                } catch (FileNotFoundException | ParseException e) {
                    e.printStackTrace();
                }

            }
    }
}
