package metrics;

import javascriptParser.com.digiarea.es5.parser.ParseException;
import javascriptParser.metrics.MetricsParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Anastasia on 18.02.2017.
 */
class MetricsParserTests {

    private static MetricsParser parser;

    @BeforeAll
    static void setUp() {

        Path filePath = Paths.get("MetricsParserTests.js");

        InputStream stream = null;

        try {
            stream = new ByteArrayInputStream(Files.readAllBytes(filePath));
            parser = new MetricsParser(stream);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void linesOfCode() {
        assertEquals(29, parser.linesOfCode(), "Wrong number of lines of code.");
    }

    @Test
    void numberOfGlobalVars() {
        assertEquals(3, parser.numberOfGlobalVars(), "Wrong number of lines of code.");
    }

    @Test
    void numberOfVars() throws ParseException {
        assertEquals(3, parser.numberOfVars(), "Wrong number of objects.");
    }

    @Test
    void numberOfComments() {
        assertEquals(2, parser.numberOfComments(), "Wrong number of lines of comments.");
    }

    @Test
    void numberOfFunctions() throws ParseException {
        assertEquals(3, parser.numberOfFunctions(), "Wrong number of functions.");
    }

    @Test
    void averageFunctionSize() {
        assertEquals(2, parser.averageFunctionSize(), "Wrong function size.");
    }

    @Test
    void numberOfLoops() throws ParseException {
        assertEquals(1, parser.numberOfLoops(), "Wrong number of loops.");
    }

    @Test
    void numberOfConditions() throws ParseException {
        assertEquals(1, parser.numberOfConditions(), "Wrong number of conditions");
    }
}