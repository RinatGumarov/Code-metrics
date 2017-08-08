# metrics-extractor-scala

## Usage

Go to the folder with grammar
```
$ cd {PATH_TO_PROJECT}/src/main/parser
```

Build parser classes
```
$ javacc Scala.jj
```

Compile generated JAVA classes
```
$ javac *.java
```

Run Main.java with parameter {PATH_TO_FOLDER_WITH_SCALA_SOURCE_CODE}