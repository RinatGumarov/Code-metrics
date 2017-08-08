package com.phpParser;

/**
 * Created by kenneth on 18.03.17.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private PHP parser;
    private List<Integer> metrics;
    private File file;
    public final static List<String> metricNames = new ArrayList<>(Arrays.asList(
            "Lines Of Code",
            "Single Line Comments",
            "Multi Line Comments",
            "Number Of Methods",
            "Number Of Public Methods",
            "Number Of Class Attributes",
            "Number Of Public Attributes",
            "Class Size",
            "Class Interface Size",
            "Cyclomatic Complexity",
            "Weighted Method Count"
    ));

    public Main(){
        metrics = new ArrayList<>();
    }

    public static void main(String [] args){
        Main main;
        BufferedWriter writer;

        try{
            File file = new File("metrics.csv");
            if(file.exists())file.delete();
            writer = new BufferedWriter(new FileWriter("metrics.csv", false));
            writer.write("filename, ");
            for(int i = 0; i < metricNames.size() - 1; i++)writer.write(metricNames.get(i) + ", ");
            writer.write(metricNames.get(metricNames.size() - 1));
            writer.newLine();
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }

        FilenameFilter phpFilter = (File dir, String name) -> name.toLowerCase().endsWith(".php");

        for(String path: args){
            try {
                File dir = new File(path);
                if (dir.isDirectory()) {
                    File[] dirListing = dir.listFiles(phpFilter);
                    for (File file : dirListing) {
                        main = new Main();
                        main.run(file);
                        main.saveMetrics(writer, dir.getName());
                    }
                    if(dirListing.length == 0){
                        System.out.println(path + "There are no php source files in the directory " + dir.getName());
                    }
                }
                else {
                    System.out.println(path + " is not a directory. Usage is");
                    System.out.println("java Main dir1 dir2 ...");
                    return ;
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }


    public void run(File file) {

        try {
            this.file = file;
            System.out.println("PHP Metrics Extractor: Started parsing file " + file.getName());
//            PHP.ReInit(new FileInputStream(file));
//            PHP.token_source.SwitchTo(PHP.HTML_STATE);
//            PHP.PhpPage();

            parser = new PHP(new FileInputStream(file));
            parser.token_source.SwitchTo(PHPConstants.HTML_STATE);
            parser.PhpPage();
            System.out.println("PHP Metrics Extractor:  " + file.getName() + " parsed successfully.");
            collectMetrics();
        }
        catch(ParseException | IOException e) {
            System.out.println("PHP Metrics Extractor:  Encountered errors while parsing " + file.getName());
            System.out.println(e.getMessage());
        }

    }

    public void saveMetrics(BufferedWriter writer, String dirname){

        try {
            writer.write(dirname + "/" + file.getName() + ", ");
            for (int i = 0; i < metrics.size() - 1; i++) {
                writer.write(metrics.get(i) + ", ");
            }
            writer.write(metrics.get(metrics.size() - 1).toString());
            writer.newLine();
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void collectMetrics(){

        metrics.add(parser.getLinesOfCode());
        metrics.add(parser.getComSingleLined());
        metrics.add(parser.getComMultiLined());
        metrics.add(parser.getNom());
        metrics.add(parser.getPubMethCount());
        metrics.add(parser.getNoaInClass());
        metrics.add(parser.getPubAttrCount());
        metrics.add(getClassSize());
        metrics.add(getClassInterfaceSize());
        metrics.add(getCyclomaticComplexity());
        metrics.add(parser.getLocInMet());
    }

    private int getCyclomaticComplexity(){
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
    private int getClassSize(){
        return (parser.getNomInClass() + parser.getNoaInClass());
    }

    private int getClassInterfaceSize(){
        return (parser.getPubMethCount() + parser.getPubAttrCount());
    }
}
