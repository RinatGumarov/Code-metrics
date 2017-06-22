package ru.innopolis.rinatgumarov.code_metrics;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import perl.PerlParser;
import ru.innopolis.rinatgumarov.code_metrics.clojure.ClojureAdapter;
import ru.innopolis.rinatgumarov.code_metrics.csharp.CSAdapter;
import ru.innopolis.rinatgumarov.code_metrics.erlang.ErlangAdapter;
import ru.innopolis.rinatgumarov.code_metrics.javascript.JSAdapter;
import ru.innopolis.rinatgumarov.code_metrics.perl.PerlAdapter;
import ru.innopolis.rinatgumarov.code_metrics.php.PHPAdapter;
import ru.innopolis.rinatgumarov.code_metrics.python.PythonAdapter;
import ru.innopolis.rinatgumarov.code_metrics.ruby.RubyAdapter;
import ru.innopolis.rinatgumarov.code_metrics.scala.ScalaAdapter;
import ru.innopolis.rinatgumarov.code_metrics.swift.SwiftAdapter;

import java.io.File;

/**
 * Created by Rinat on 08.06.17.
 */
public class Main {

    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "-javascript":
                    JSAdapter.run(args[1]);
                    break;
                case "-php":
                    PHPAdapter.run(args[1]);
                    break;
                case "-perl":
                    PerlAdapter.run(args[1]);
                    break;
                case "-clojure":
                    ClojureAdapter.run(args[1]);
                    break;
                case "-erlang":
                    ErlangAdapter.run(args[1]);
                    break;
                case "-ruby":
                    RubyAdapter.run(args[1]);
                    break;
                case "-csharp":
                    CSAdapter.run(args[1]);
                    break;
                case "-swift":
                    SwiftAdapter.run(args[1]);
                    break;
                case "-scala":
                    ScalaAdapter.run(args[1]);
                    break;
                case "-python":
                    PythonAdapter.run(args[1]);
                    break;
                default:
                    throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("usage: java -jar code_metrics.jar (-lang) path/to/sources");
        }
    }
}
