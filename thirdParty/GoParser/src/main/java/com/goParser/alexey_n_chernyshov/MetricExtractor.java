/**
 * @author Alexey Chernyshov
 */

package com.goParser.alexey_n_chernyshov;

import com.goParser.alexey_n_chernyshov.generated.SimpleNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Extract metrics from projects in the Go lang.
 */
public class MetricExtractor {

    private final String SEPARATOR = "\t";
    /**
     * Map of parsed sources
     */
    HashMap<String, SimpleNode> astSources = new HashMap<>();
    /**
     * Contains all the metrics.
     */
    List<SourceFile> sources = new ArrayList<>();
    /**
     * List of files not parsed.
     */
    List<ProblemFile> problemFiles = new ArrayList<>();
    /**
     * Total number of files parsed.
     */
    int totalParsed = 0;

    /**
     * @return number of files not parsed
     */
    int getProblemFilesCount() {
        return problemFiles.size();
    }

    public void parseFile(String filename) {
        try {
            totalParsed++;
            Preprocessor p = new Preprocessor();
            GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(filename)));
            SimpleNode root = parser.getRoot();
            astSources.put(filename, root);

            StatementCounterVisitor statementCounterVisitor = new StatementCounterVisitor();
            root.jjtAccept(statementCounterVisitor, null);

            CyclomaticComplexityVisitor cyclomaticComplexityVisitor = new CyclomaticComplexityVisitor();
            root.jjtAccept(cyclomaticComplexityVisitor, null);

            OOPMeasuresVisitor oopMeasuresVisitor = new OOPMeasuresVisitor();
            root.jjtAccept(oopMeasuresVisitor, null);

            SourceFile sourceFile = new SourceFile(filename, statementCounterVisitor.getStatements());
            for (Map.Entry<String, Integer> entry : cyclomaticComplexityVisitor.getComplexity().entrySet()) {
                sourceFile.addFunction(new Method(entry.getKey(), entry.getValue()));
            }
            for (OOPMeasuresVisitor.Structure structure : oopMeasuresVisitor.getStructures()) {
                sourceFile.addStruct(new Struct(structure.getStructName(),
                        structure.getMethods().size(),
                        structure.getAttributes().size()));
            }
            sources.add(sourceFile);
        } catch (Exception e) {
            problemFiles.add(new ProblemFile(filename, e.toString()));
        }
    }

    public void parseDir(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().endsWith(".go"))
                .forEach(p -> parseFile(p.toString()));
    }

    /**
     * Outputs encountered problems in files.
     */
    public void printProblemFiles(PrintStream out) {
        if (!problemFiles.isEmpty()) {
            out.println("Problem files (total: " + problemFiles.size() + "):");
            problemFiles.forEach((s) -> out.println(s));
        }
    }

    /**
     * Prints metrics for files:
     * - statements count
     */
    public void printFilesMetrics(PrintStream out) {
        out.println("filename" + SEPARATOR +
                "statementCount" + SEPARATOR +
                "structuresCount" + SEPARATOR +
                "functionsCount" + SEPARATOR +
                "AvgCyclomaticComplexity" + SEPARATOR +
                "MaxCyclomaticComlexity"
        );
        sources.forEach((s) -> {
            out.println(s.filename + SEPARATOR +
                    s.statementCount + SEPARATOR +
                    s.structs.size() + SEPARATOR +
                    s.methods.size() + SEPARATOR +
                    s.methods.stream()
                            .mapToInt(Method::getCyclomaticComplexity)
                            .average()
                            .getAsDouble() + SEPARATOR +
                    s.methods.stream().mapToInt(Method::getCyclomaticComplexity).max().getAsInt()
            );
        });
    }

    /**
     * Prints metrics for structures:
     * - methods attached to structure
     */
    public void printStructMetrics(PrintStream out) {
        out.println("filename: structName" + SEPARATOR + "methodCount" + SEPARATOR + "attributesCount");

        for (SourceFile s : sources) {
            for (Struct struct : s.structs) {
                out.println(s.filename + ": " + struct.name + SEPARATOR +
                        struct.methodCount + SEPARATOR +
                        struct.atributesCount
                );
            }
        }
    }

    /**
     * Prints metrics for methods:
     * - cyclomatic complexity
     */
    public void printMethodMetrics(PrintStream out) {
        out.println("filename: methodName" + SEPARATOR + "cyclomaticComplexity");

        for (SourceFile s : sources) {
            for (Method method : s.methods) {
                out.println(s.filename + ": " + method.name + SEPARATOR + method.cyclomaticComplexity);
            }
        }
    }

    /**
     * Output report to the out stream.
     */
    public void printReport() {
        printProblemFiles(System.out);
        printFilesMetrics(System.out);
        printStructMetrics(System.out);
        printMethodMetrics(System.out);
    }

    /**
     * Prints reports with metrics to files.
     */
    public void printReportFiles() throws FileNotFoundException {
        printProblemFiles(new PrintStream(new FileOutputStream("problemFiles.txt")));
        printFilesMetrics(new PrintStream(new FileOutputStream("sourcesMetrics.txt")));
        printStructMetrics(new PrintStream(new FileOutputStream("structMetrics.txt")));
        printMethodMetrics(new PrintStream(new FileOutputStream("methodMetrics.txt")));
    }

    /**
     * Represents the file with problem encountered.
     */
    class ProblemFile {
        String filename;
        String problem;

        ProblemFile(String filename, String problem) {
            this.filename = filename;
            this.problem = problem;
        }

        @Override
        public String toString() {
            return filename + ": " + problem;
        }
    }

    /**
     * Metrics for function of the source file.
     */
    class Method {
        String name;
        int cyclomaticComplexity;

        Method(String name, int cyclomaticComplexity) {
            this.name = name;
            this.cyclomaticComplexity = cyclomaticComplexity;
        }

        int getCyclomaticComplexity() {
            return cyclomaticComplexity;
        }
    }

    /**
     * Metrics for struct of the source file.
     */
    class Struct {
        String name;
        int methodCount;
        int atributesCount;

        Struct(String name, int methodCount, int atributesCount) {
            this.name = name;
            this.methodCount = methodCount;
            this.atributesCount = atributesCount;
        }
    }

    /**
     * Stores all metrics parsed.
     */
    class SourceFile {
        String filename;
        int statementCount;
        HashSet<Method> methods = new HashSet<>();
        HashSet<Struct> structs = new HashSet<>();

        SourceFile(String filename, int statementCount) {
            this.filename = filename;
            this.statementCount = statementCount;
        }

        void addFunction(Method method) {
            methods.add(method);
        }

        void addStruct(Struct struct) {
            structs.add(struct);
        }
    }

}
