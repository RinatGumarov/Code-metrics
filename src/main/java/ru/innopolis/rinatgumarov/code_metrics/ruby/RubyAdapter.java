package ru.innopolis.rinatgumarov.code_metrics.ruby;

//import ru.innopolis.rinatgumarov.code_metrics.db.Database;
//import rubyMetrics.MetricsCounter;

//import
import ru.innopolis.rinatgumarov.code_metrics.db.Database;
import rubyMetrics.MetricsCounter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Rinat on 13.06.17.
 */
public class RubyAdapter {
    public static void run(String path) {
        File d = new File(path);
        FilenameFilter pythonFileFilter = (dir, name) -> name.endsWith(".rb");
        File[] files = d.listFiles(pythonFileFilter);

        for (File file : files) {
            MetricsCounter e = new MetricsCounter(path + file.getName());
            String[] metrics = e.getMetricsStrings();
            String query = "INSERT INTO ruby_metrics (full_name, nom," +
                    " statements, classes," +
                    " interfaces, noa, loops, conditions," +
                    " rr, noc, sr, dit, mif, aif, nmo, lcom, tcc) " +
                    "VALUES (" + "'" +
                    (path + file.getName()) + "'" + ", " +
//                    PythonGrammar.numberLines + ", " +
//                    PythonGrammar.numberAllTokens + ", " +
//                    PythonGrammar.numberTokensInLine + ", " +
//                    PythonGrammar.numberClasses + ", " +
//                    PythonGrammar.numberImports + ", " +
//                    PythonGrammar.numberMethods + ", " +
//                    PythonGrammar.numberReturns + ", " +
//                    PythonGrammar.numberCycles + ", " +
//                    "'" + complexityLevel + "'" +
                    ");";
            Database db = Database.getINSTANCE();
            db.write(query);
//        }
        }
    }
}
