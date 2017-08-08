package com.goParser.alexey_n_chernyshov;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Yex
 */
public class PreprocessorTest {

    @Test
    public void testDecimalIntegerLiterals() {
        String src =
                "package main\n" +
                        "\n" +
                        "import \"fmt\"\n" +
                        "\n" +
                        "func main() {\n" +
                        "    _\n" +
                        "    _123\n" +
                        "    0xaf333\n" +
                        "    072.40\n" +
                        "    .12345E+5\n" +
                        "    1.e+0i\n" +
                        "    \"\\\"string\"\n" +
                        "    fmt.Println(\"go\" + \"lang\")\n" +
                        "    fmt.Println(\"1+1 =\", 1+1)\n" +
                        "    2 + 2\n" +
                        "    fmt.Println(\"7.0/3.0 =\", 7.0/3.0)\n" +
                        "    fmt.Println(true && false)\n" +
                        "    fmt.Println(true || false)\n" +
                        "    fmt.Println(!true)\n" +
                        "    return  \n" +
                        "}";
        InputStream inStream = new ByteArrayInputStream(src.getBytes(StandardCharsets.UTF_8));
        Preprocessor p = new Preprocessor();
        p.addSemicolons(inStream);
    }

}