/**
 * @author Alexey Chernyshov
 */

package com.goParser.alexey_n_chernyshov;

import java.io.FileNotFoundException;

public class MainApp {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Please pass in the path.");
            System.exit(1);
        }

        try {
            MetricExtractor me = new MetricExtractor();
            me.parseDir(args[0]);
//            me.printReport(); // prints to the System.out
            me.printReportFiles();
            System.out.print("Finished! Total parsed: " + me.totalParsed + ", problem files: " + me.getProblemFilesCount());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
