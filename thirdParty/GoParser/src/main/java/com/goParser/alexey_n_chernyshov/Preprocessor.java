/**
 * @author Alexey Chernyshov
 */

package com.goParser.alexey_n_chernyshov;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Preprocessor {

    /**
     * Adds semicolons. Not all semicolons are added, the Parser sets
     * them in a proper way with LOOKAHEAD. This class only speeds up
     * the parsing.
     *
     * @param stream
     * @return
     */
    InputStream addSemicolons(InputStream stream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        reader.lines().forEach(
                s -> {
                    s = s.trim();
                    if (s.endsWith("++") || s.endsWith("--") ||
                            s.endsWith(")") || s.endsWith("]") || s.endsWith("}"))
                        sb.append(s + ";\n");
                    else {
                        String[] wordList = s.split("\\s+");
                        String lastWord = wordList[wordList.length - 1];
                        if (!lastWord.endsWith(":") &&
                                !lastWord.endsWith(",") &&
                                !lastWord.endsWith("{") &&
                                lastWord.matches("[a-z_A-Z].*") ||

                                lastWord.matches("-?\\d+i?") ||

                                lastWord.matches("(0x|0X)([0-9a-fA-F])+") ||

                                lastWord.matches("\\d+\\.\\d*([eE][+-]?\\d+)?i?") ||
                                lastWord.matches("\\d+([eE][+-]?\\d+)?i?") ||
                                lastWord.matches("\\.\\d+([eE][+-]?\\d+)?i?") ||

                                lastWord.matches("\".*\"") ||
                                lastWord.matches("`.*`") ||

                                lastWord.equals("break") || lastWord.equals("continue") ||
                                lastWord.equals("fallthrough") || lastWord.equals("return")
                                )
                            sb.append(s + ";\n");
                        else
                            sb.append(s + "\n");
                    }
                }
        );

        //TODO
//        System.out.println(sb.toString());
        return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
