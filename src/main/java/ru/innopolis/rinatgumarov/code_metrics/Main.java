package ru.innopolis.rinatgumarov.code_metrics;

import ru.innopolis.rinatgumarov.code_metrics.csharp.CSAdapter;
import ru.innopolis.rinatgumarov.code_metrics.python.PythonAdapter;
import ru.innopolis.rinatgumarov.code_metrics.scala.ScalaAdapter;
import ru.innopolis.rinatgumarov.code_metrics.swift.SwiftAdapter;

/**
 * Created by Rinat on 08.06.17.
 */
public class Main {

    public static void main(String[] args){
        try {
            switch (args[0]){
                case "-csharp" :
                    CSAdapter.run(args[1]);
                    break;
                case "-swift" :
                    SwiftAdapter.run(args[1]);
                    break;
                case "-scala" :
                    ScalaAdapter.run(args[1]);
                    break;
                case "-python" :
                    PythonAdapter.run(args[1]);
                    break;
                default:
                    throw new ArrayIndexOutOfBoundsException();
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("usage ..");
        }
    }
}
