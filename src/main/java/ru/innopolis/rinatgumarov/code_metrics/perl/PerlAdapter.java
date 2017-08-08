package ru.innopolis.rinatgumarov.code_metrics.perl;

import org.apache.log4j.Logger;
import perl.ParseException;
import perl.PerlParser;
import ru.innopolis.rinatgumarov.code_metrics.db.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rinat on 19.06.17.
 */
public class PerlAdapter {

    private static Logger logger = Logger.getLogger(PerlAdapter.class.getName());

    public static void run(String path) {
        File d = new File(path);
        try {
            List<File> files = Arrays.stream(d.listFiles())
                    .filter(file -> file.isDirectory() || file.getName().endsWith(".pl"))
                    .collect(Collectors.toList());
            if (!files.isEmpty())
                for (File file : files) {
                    try {
                        if (file.isDirectory())
                            run(file.getAbsolutePath() + "/");
                        else {
                            PerlParser perlParser = new PerlParser(new FileReader(file));
                            clearMetrics();
                            perlParser.CompilationUnit();
                            String query = "INSERT INTO perl_metrics (full_name, linesOfCode, numStringOperator," +
                                    " numNumericOperator, numScalars, arrayGets, hashGets, numArrays, numHashes," +
                                    " numBranches, numCycles, regexes, functions, cyclomaticComplexity) " +
                                    "VALUES (" + "'" +
                                    (path + file.getName()) + "'" + ", " +
                                    PerlParser.getLinesOfCode() + ", " +
                                    PerlParser.getNumStringOperators() + ", " +
                                    PerlParser.getNumNumericOperators() + ", " +
                                    PerlParser.getNumScalars() + ", " +
                                    PerlParser.getArrayGets() + ", " +
                                    PerlParser.getHashGets() + ", " +
                                    PerlParser.getNumArrays() + ", " +
                                    PerlParser.getNumHashes() + ", " +
                                    PerlParser.getNumBranches() + ", " +
                                    PerlParser.getNumCycles() + ", " +
                                    PerlParser.getRegexes() + ", " +
                                    PerlParser.getFunctions() + ", " +
                                    (PerlParser.getNumBranches() + PerlParser.getNumCycles()) +
                                    ");";
                            Database db = Database.getINSTANCE();
                            db.write(query);
                            logger.info(file.getAbsolutePath() + " - successful");
                        }
                    } catch (FileNotFoundException | ParseException e) {
                        logger.info("Parse exception in " + file.getAbsolutePath() + " with message: " + e.getMessage());
                    }
                }
        } catch (NullPointerException e) {
            logger.info("Directory " + path + " is empty.");
        }
    }

    private static void clearMetrics() {
        PerlParser.setArrayGets(0);
        PerlParser.setFunctions(0);
        PerlParser.setHashGets(0);
        PerlParser.setLinesOfCode(0);
        PerlParser.setNumBranches(0);
        PerlParser.setNumCycles(0);
        PerlParser.setNumHashes(0);
        PerlParser.setNumNumericOperators(0);
        PerlParser.setNumScalars(0);
        PerlParser.setNumStringOperators(0);
        PerlParser.setRegexes(0);
        PerlParser.setNumArrays(0);
    }
}
