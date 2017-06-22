package ru.innopolis.rinatgumarov.code_metrics.ruby;

//import ru.innopolis.rinatgumarov.code_metrics.db.Database;
//import rubyMetrics.MetricsCounter;

//import

import org.apache.log4j.Logger;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;
import rubyMetrics.MetricsCounter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rinat on 13.06.17.
 */
public class RubyAdapter {

    private static Logger logger = Logger.getLogger(RubyAdapter.class.getName());

    public static void run(String path) {
        File d = new File(path);
        try {
            List<File> files = Arrays.stream(d.listFiles())
                    .filter(file -> file.isDirectory() || file.getName().endsWith(".rb"))
                    .collect(Collectors.toList());
            if (!files.isEmpty())
                for (File file : files) {
                    if (file.isDirectory())
                        run(path + file.getName() + "/");
                    else {
                        MetricsCounter e = new MetricsCounter(path + file.getName());
                        String[] metrics = e.getMetricsStrings();
                        String query = "INSERT INTO ruby_metrics (full_name, nom," +
                                " statements, classes," +
                                " interfaces, noa, loops, conditions," +
                                " rr, noc, sr, dit, mif, aif, nmo, lcom, tcc) " +
                                "VALUES (" + "'" +
                                (path + file.getName()) + "'" + ", " +
                                metrics[0] + ", " +
                                metrics[1] + ", " +
                                metrics[2] + ", " +
                                metrics[3] + ", " +
                                metrics[4] + ", " +
                                metrics[5] + ", " +
                                metrics[6] + ", " +
                                metrics[7] + ", " +
                                metrics[8] + ", " +
                                metrics[9] + ", " +
                                metrics[10] + ", " +
                                metrics[11] + ", " +
                                metrics[12] + ", " +
                                metrics[13] + ", " +
                                metrics[14] + ", " +
                                metrics[15] + ");";
                        Database db = Database.getINSTANCE();
                        db.write(query);
                        logger.info(file.getAbsolutePath() + " - successful");
                    }
                }
        }
        catch (NullPointerException e){
            logger.info("Directory " + path + " is empty.");
        }
    }
}