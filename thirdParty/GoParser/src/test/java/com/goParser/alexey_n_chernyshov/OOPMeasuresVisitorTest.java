package com.goParser.alexey_n_chernyshov;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;

import static org.junit.Assert.*;

public class OOPMeasuresVisitorTest {


    private int countStructs(String src) throws FileNotFoundException, ParseException {
        Preprocessor p = new Preprocessor();
        GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(src)));
        SimpleNode root = parser.getRoot();
        OOPMeasuresVisitor visitor = new OOPMeasuresVisitor();
        root.jjtAccept(visitor, null);
        visitor.getStructures().forEach( (s) -> System.out.println(s.getStructName() + " " + s.getAttributes()) );
        return visitor.getStructures().size();
    }

    private int countMethodsOfStructs(String src) throws FileNotFoundException, ParseException {
        Preprocessor p = new Preprocessor();
        GoParser parser = new GoParser(p.addSemicolons(new FileInputStream(src)));
        SimpleNode root = parser.getRoot();
        OOPMeasuresVisitor visitor = new OOPMeasuresVisitor();
        root.jjtAccept(visitor, null);
        HashSet<OOPMeasuresVisitor.Structure> l = visitor.getStructures();
        int count = 0;
        for(OOPMeasuresVisitor.Structure s: l){
            count += s.getMethods().size();
            System.out.println(s.getStructName() + ": " + s.getMethods().size() + "methods");
        }

        return count;
    }

    @Test
    public void test1(){
        try{
            String src = "src/test/resources/HelloWorld.go";
            assertEquals(1, countStructs(src));
        } catch (Exception e){
            System.out.println(e.toString());
            //fail("Caught an exception " + e.toString());
        }
    }

    @Test
    public void test2(){
        try{
            String src = "src/test/resources/HelloWorld.go";
            assertEquals(1, countMethodsOfStructs(src));
        } catch (Exception e){
            System.out.println(e.toString());
            //fail("Caught an exception " + e.toString());
        }
    }
}