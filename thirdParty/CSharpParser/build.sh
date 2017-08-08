#!/bin/sh

echo Generating .class files
javac -sourcepath ./src src/csmc/Main.java

echo Building jar file
jar -cfe CSharpMetricsCollector.jar csmc.Main -C ./src ./

echo Finished

