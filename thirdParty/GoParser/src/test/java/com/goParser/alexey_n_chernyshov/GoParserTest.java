/**
 * @author Alexey Chernyshov
 */

package com.goParser.alexey_n_chernyshov;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.fail;

public class GoParserTest {
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Parses source as a string input.
     *
     * @param src
     * @return parser
     */
    private GoParser createParser(String src) {
        InputStream inStream = new ByteArrayInputStream(src.getBytes(StandardCharsets.UTF_8));
        return new GoParser(inStream);
    }

    /**
     * Traverse AST and builds result string.
     *
     * @return
     */
    private String getParseResult(SimpleNode root) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        PrintVisitor pv = new PrintVisitor(out);
        root.jjtAccept(pv, null);
        return outStream.toString();
    }

    private SimpleNode parseFile(String filename) throws FileNotFoundException, ParseException {
        Preprocessor p = new Preprocessor();
        GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(filename)));
        return parser.getRoot();
    }

    /**
     * Test "HelloWorld.go" program. Shouldn't throw ParseException.
     */
    @Test
    public void testHelloWorld() {
        try {
            parseFile("src/test/resources/HelloWorld.go");
        } catch (Exception e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Test "values.go" program with literals. Shouldn't throw ParseException.
     */
    @Test
    public void testValues() {
        try {
            parseFile("src/test/resources/grammar/values.go");
        } catch (Exception e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Test "typeswitch.go" program with type switch statement. Shouldn't throw ParseException.
     */
    @Test
    public void testTypeSwitchStatement() {
        try {
            parseFile("src/test/resources/grammar/typeswitch.go");
        } catch (Exception e) {
            fail("Caught an exception " + e.toString());
        }
    }

    /**
     * Test "application.go" program. Shouldn't throw ParseException.
     */
    @Test
    public void testCodeground() {
        try {
            parseFile("src/test/resources/codeground/application.go");
            parseFile("src/test/resources/codeground/main.go");
            parseFile("src/test/resources/codeground/network.go");
        } catch (Exception e) {
            fail("Caught an exception " + e.toString());
        }
    }

}