package com.goParser.alexey_n_chernyshov;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class CyclomaticComplexityVisitorTest {

    private int countCCofMethod(String src, String methodName) throws FileNotFoundException, ParseException{
        Preprocessor p = new Preprocessor();
        GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(src)));
        SimpleNode root = parser.getRoot();
        CyclomaticComplexityVisitor visitor = new CyclomaticComplexityVisitor();
        root.jjtAccept(visitor, null);

        return visitor.getComplexity().get(methodName);
    }

    @Test
    public void test1(){
        try{
            String src = "src/test/resources/HelloWorld.go";
            assertEquals(1,countCCofMethod(src, "main"));
        } catch (Exception e){
            fail("Caught an exception " + e.toString());
        }
    }
}