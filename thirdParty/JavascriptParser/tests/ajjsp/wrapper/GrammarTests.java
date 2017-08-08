package ajjsp.wrapper;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javascriptParser.com.digiarea.es5.*;
import javascriptParser.com.digiarea.es5.parser.ASTParser;
import javascriptParser.com.digiarea.es5.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Vyacheslav on 17-Feb-17.
 */
class GrammarTests {
    @Test
    public void functionDeclarationTest() throws ParseException {

        String code = "function myFirstFunction(param1, param2){" +
                "alert('Hello, world!');" +
                "}";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        FunctionDeclaration fun = parser.FunctionDeclaration();

        assertEquals("myFirstFunction", fun.getName(), "Name must be myFirstFunction");
        assertEquals("{\n" +
                "    alert('Hello, world!');\n" +
                "}", fun.getBody().toString(), "Body must be correct");
        assertEquals("param1", fun.getParameters().get(0).toString(), "1st parameter name should be param1");
        assertEquals("param2", fun.getParameters().get(1).toString(), "2nd parameter name should be param2");

    }

    @Test
    public void variableDeclarationTest() throws ParseException {

        String code = "a = 10+15";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        VariableDeclaration var = parser.VariableDeclaration();

        assertEquals("a", var.getName(), "Name must be a");
        assertEquals("10 + 15", var.getExpression().toString(), "Expression must be 10+15");

    }

    @Test
    public void variableStatementTest() throws ParseException {

        String code = "var a = 10+15";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        VariableStatement var = parser.VariableStatement();

        assertEquals("a = 10 + 15", var.getVariableDeclarations().get(0).toString(), "Name must be a");
    }


    @Test
    public void whileStatementTest() throws ParseException {
        String code = "while(42 == 42){" +
                "alert('We are doomed')" +
                "}";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        WhileStatement whiles = parser.WhileStatement();

        assertEquals("42 == 42", whiles.getCondition().toString());
        assertEquals("{\n" +
                "    alert('We are doomed');\n" +
                "}", whiles.getBody().toString(), "Body must be correct");
    }

    @Test
    public void doWhileStatementTest() throws ParseException {
        String code = "do{" +
                "alert('We are doomed')" +
                "} while (42 == 42);";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        DoWhileStatement dowhiles = parser.DoWhileStatement();

        assertEquals("42 == 42", dowhiles.getCondition().toString());
        assertEquals("{\n" +
                "    alert('We are doomed');\n" +
                "}", dowhiles.getBody().toString(), "Body must be correct");
    }

    @Test
    public void forStatementTest() throws ParseException {
        String code = "for (i = 0; i < 9; i++) {\n" +
                "   console.log(i);\n" +
                "}";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        ForStatement fors = parser.ForStatement();

        assertEquals("i = 0", fors.getVariable().toString());
        assertEquals("i++ ", fors.getExpr().toString());
        assertEquals("i < 9", fors.getCondition().toString());
        assertEquals("{\n" +
                "    console.log(i);\n" +
                "}", fors.getBody().toString(), "Body must be correct");
    }

    @Test
    public void forVarStatementTest() throws ParseException {
        String code = "for (var i = 0; i < 9; i++) {\n" +
                "   console.log(i);\n" +
                "}";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        ForStatement fors = parser.ForVarStatement();

        assertEquals("var i = 0", fors.getVariable().toString());
        assertEquals("i++ ", fors.getExpr().toString());
        assertEquals("i < 9", fors.getCondition().toString());
        assertEquals("{\n" +
                "    console.log(i);\n" +
                "}", fors.getBody().toString(), "Body must be correct");
    }

    @Test
    public void forVarInStatementTest() throws ParseException {
        String code = "for (var prop in obj) {\n" +
                "   console.log('obj.' + prop, '=', obj[prop]);\n" +
                "}";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        ForeachStatement forins = parser.ForInVarStatement();

        assertEquals("obj", forins.getExpression().toString());
        assertEquals("var prop", forins.getVariable().toString());
        assertEquals("{\n" +
                "    console.log('obj.' + prop, '=', obj[prop]);\n" +
                "}", forins.getBody().toString(), "Body must be correct");
    }

    @Test
    public void forInStatementTest() throws ParseException {
        String code = "for (prop in obj) {\n" +
                "   console.log('obj.' + prop, '=', obj[prop]);\n" +
                "}";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        ForeachStatement forins = parser.ForInStatement();

        assertEquals("obj", forins.getExpression().toString());
        assertEquals("prop", forins.getVariable().toString());
        assertEquals("{\n" +
                "    console.log('obj.' + prop, '=', obj[prop]);\n" +
                "}", forins.getBody().toString(), "Body must be correct");
    }

    @Test
    public void ifStatementTest() throws ParseException {
        String code = "if (hour < 18) {\n" +
                "    greeting = \"Good day\";\n" +
                "} else {\n" +
                "    greeting = \"Good evening\";\n" +
                "}";
        InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        ASTParser parser = new ASTParser(stream);
        IfStatement ifs = parser.IfStatement();

        assertEquals("hour < 18", ifs.getCondition().toString());
        assertEquals("{\n" +
                "    greeting = \"Good day\";\n" +
                "}", ifs.getThenStatement().toString(), "Body must be correct");
        assertEquals("{\n" +
                "    greeting = \"Good evening\";\n" +
                "}", ifs.getElseStatement().toString(), "Body must be correct");

    }
}