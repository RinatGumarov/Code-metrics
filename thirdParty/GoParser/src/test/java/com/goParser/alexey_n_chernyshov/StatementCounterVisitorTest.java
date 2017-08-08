package com.goParser.alexey_n_chernyshov;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class StatementCounterVisitorTest {

    private int countStatements(String src) throws FileNotFoundException, ParseException {
        Preprocessor p = new Preprocessor();
        GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(src)));
        SimpleNode root = parser.getRoot();
        StatementCounterVisitor visitor = new StatementCounterVisitor();
        root.jjtAccept(visitor, null);

        return visitor.getStatements();
    }

    @Test
    public void testHelloWorld(){
        try{
            String src = "src/test/resources/HelloWorld.go";
            assertEquals(1, countStatements(src));
        } catch (Exception e){
            fail("Caught an exception " + e.toString());
        }
    }

    @Test
    public void testValues(){
        try{
            String src = "src/test/resources/grammar/values.go";
            assertEquals(20, countStatements(src));
        } catch (Exception e){
            fail("Caught an exception " + e.toString());
        }
    }

}