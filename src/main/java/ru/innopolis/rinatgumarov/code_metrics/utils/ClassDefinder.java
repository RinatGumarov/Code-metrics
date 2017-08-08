package ru.innopolis.rinatgumarov.code_metrics.utils;

/**
 * Created by Rinat on 03.07.17.
 */
public class ClassDefinder {

    public static String getTableName(String className){
        if (className.contains("Clojure"))
            return "clojure_metrics";
        else if (className.contains("CS"))
            return "csharp_metrics";
        else if (className.contains("Erlang"))
            return "erlang_metrics";
        else if (className.contains("JS"))
            return "js_metrics";
        else if (className.contains("Perl"))
            return "perl_metrics";
        else if (className.contains("PHP"))
            return "php_metrics";
        else if (className.contains("Python"))
            return "python_metrics";
        else if (className.contains("Ruby"))
            return "ruby_metrics";
        else if (className.contains("Scala"))
            return "scala_metrics";
        else if (className.contains("Swift"))
            return "swift_metrics";
        else throw new IllegalArgumentException("No matching with exist table names");
    }
}
