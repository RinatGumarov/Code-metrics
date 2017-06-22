package ru.innopolis.rinatgumarov.code_metrics.php;

import com.phpParser.PHP;
import com.phpParser.PHPConstants;
import com.phpParser.ParseException;
import org.apache.log4j.Logger;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rinat on 20.06.17.
 */
public class PHPAdapter {

    private static Logger logger = Logger.getLogger(PHPAdapter.class.getName());
    private PHP parser;
    private int[] metrics;
    private File file;

    public static final String[] metricNames =
            new String[]{"Lines Of Code", "Single Line Comments", "Multi Line Comments",
                    "Number Of Methods", "Number Of Public Methods",
                    "Number Of Class Attributes", "Number Of Public Attributes",
                    "Class Size", "Class Interface Size",
                    "Cyclomatic Complexity", "Weighted Method Count"};

    public PHPAdapter() {
        this.metrics = new int[11];
    }

    public static void run(String path) {
        File d = new File(path);
        try {
            List<File> files = Arrays.stream(d.listFiles())
                    .filter(file -> file.isDirectory() || file.getName().endsWith(".php"))
                    .collect(Collectors.toList());
            if (!files.isEmpty())
                for (File file : files) {
                    if (file.isDirectory())
                        run(file.getAbsolutePath() + "/");
                    else {
                        PHPAdapter pa = new PHPAdapter();
                        pa.retrieve(file);
                        String query = "INSERT INTO php_metrics (full_name, linesOfCode, singleLineComments," +
                                " multiLineComments, numberOfMethods, numberOfPublicMethods," +
                                " numberOfClassAttributes, numberOfPublicAttributes, classSize," +
                                " classInterfaceSize, cyclomaticComplexity, weightedMethodCount) " +
                                "VALUES (" + "'" +
                                (path + file.getName()) + "'" + ", " +
                                pa.metrics[0] + ", " +
                                pa.metrics[1] + ", " +
                                pa.metrics[2] + ", " +
                                pa.metrics[3] + ", " +
                                pa.metrics[4] + ", " +
                                pa.metrics[5] + ", " +
                                pa.metrics[6] + ", " +
                                pa.metrics[7] + ", " +
                                pa.metrics[8] + ", " +
                                pa.metrics[9] + ", " +
                                pa.metrics[10] +
                                ");";
                        Database db = Database.getINSTANCE();
                        db.write(query);
                        logger.info(file.getAbsolutePath() + " - successful");
                    }
                }
        } catch (NullPointerException e) {
            logger.info("Directory " + path + " is empty.");
        }
    }

    private void collectMetrics() {

        metrics[0] = parser.getLinesOfCode();
        metrics[1] = parser.getComSingleLined();
        metrics[2] = parser.getComMultiLined();
        metrics[3] = parser.getNom();
        metrics[4] = parser.getPubMethCount();
        metrics[5] = parser.getNoaInClass();
        metrics[6] = parser.getPubAttrCount();
        metrics[7] = getClassSize();
        metrics[8] = getClassInterfaceSize();
        metrics[9] = getCyclomaticComplexity();
        metrics[10] = parser.getLocInMet();
    }

    private int getCyclomaticComplexity() {
        return parser.getNumQuestionMarks() +
                parser.getNumAnds() +
                parser.getNumOr() +
                parser.getNumXor() +
                parser.getNumCase() +
                parser.getNumFor() +
                parser.getNumCatch() +
                parser.getNumElseif() +
                parser.getNumForeach() +
                parser.getNumWhile();
    }

    private int getClassSize() {
        return (parser.getNomInClass() + parser.getNoaInClass());
    }

    private int getClassInterfaceSize() {
        return (parser.getPubMethCount() + parser.getPubAttrCount());
    }

    public void retrieve(File file) {

        try {
            this.file = file;
            parser = new PHP(new FileInputStream(file));
            parser.token_source.SwitchTo(PHPConstants.HTML_STATE);
            parser.PhpPage();
            collectMetrics();
        } catch (ParseException | IOException e) {
            System.out.println("PHP Metrics Extractor:  Encountered errors while parsing " + file.getName());
            System.out.println(e.getMessage());
        }

    }
}
