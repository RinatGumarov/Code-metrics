#!bin/bash
mvn install:install-file -Dfile=thirdParty/PythonParser.jar -DgroupId=python-parser -DartifactId=python-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/ClojureParser.jar -DgroupId=clojure-parser -DartifactId=clojure-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/CSharpParser.jar -DgroupId=csharp-parser -DartifactId=csharp-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/ErlangParser.jar -DgroupId=erlang-parser -DartifactId=erlang-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/JavascriptParser.jar -DgroupId=javascript-parser -DartifactId=javascript-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/PerlParser.jar -DgroupId=perl-parser -DartifactId=perl-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/PHPParser.jar -DgroupId=php-parser -DartifactId=php-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/RubyParser.jar -DgroupId=ruby-parser -DartifactId=ruby-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/ScalaParser.jar -DgroupId=scala-parser -DartifactId=scala-parser -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=thirdParty/SwiftParser.jar -DgroupId=swift-parser -DartifactId=swift-parser -Dversion=1.0 -Dpackaging=jar