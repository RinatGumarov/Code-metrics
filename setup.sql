CREATE DATABASE IF NOT EXISTS code_metrics;

DROP TABLE IF EXISTS clojure_metrics;
create table clojure_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  forms int not null,
  literals int not null,
  keywords int not null,
  symbols int not null,
  lists int not null,
  vectors int not null,
  maps int not null,
  sets int not null,
  nestiness int not null,
  mxnestiness int not null,
  functions int not null,
  pfunctions int not null,
  macros int not null,
  multis int not null,
  methods int not null
)
;

DROP TABLE IF EXISTS csharp_metrics;
create table csharp_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  className varchar(255) not null,
  wmc int not null,
  dit int not null,
  noc int not null,
  cbo int not null,
  rfc int not null,
  lcom int not null
)
;

DROP TABLE IF EXISTS erlang_metrics;
create table erlang_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  linesOfCode int not null,
  numberOfComments int not null,
  numberOfImports int not null,
  numberOfExports int not null,
  numberOfModules int not null
)
;

DROP TABLE IF EXISTS js_metrics;
create table js_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  linesOfCode int not null,
  numberOfComments int not null,
  numberOfGlobalVars int not null,
  numberOfVars int not null,
  numberOfFunctions int not null,
  averageFunctionSize int not null,
  numberOfLoops int not null,
  numberOfConditions int not null
)
;

DROP TABLE IF EXISTS perl_metrics;
create table perl_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  linesOfCode int not null,
  numStringOperator int not null,
  numNumericOperator int not null,
  numScalars int not null,
  arrayGets int not null,
  hashGets int not null,
  numArrays int not null,
  numHashes int not null,
  numBranches int not null,
  numCycles int not null,
  regexes int not null,
  functions int not null,
  cyclomaticComplexity int not null
)
;

DROP TABLE IF EXISTS php_metrics;
create table php_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  linesOfCode int not null,
  singleLineComments int not null,
  multiLineComments int not null,
  numberOfMethods int not null,
  numberOfPublicMethods int not null,
  numberOfClassAttributes int not null,
  numberOfPublicAttributes int not null,
  classSize int not null,
  classInterfaceSize int not null,
  cyclomaticComplexity int not null,
  weightedMethodCount int not null
)
;


DROP TABLE IF EXISTS python_metrics;
create table python_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  numberLines int not null,
  numberAllTokens int not null,
  numberTokensInLine int not null,
  numberClasses int not null,
  numberImports int not null,
  numberMethods int not null,
  numberReturns int not null,
  numberCycles int not null,
  complexityLevel varchar(10) not null
)
;

DROP TABLE IF EXISTS ruby_metrics;
create table ruby_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  nom int not null,
  statements int not null,
  classes int not null,
  interfaces int not null,
  noa int not null,
  loops int not null,
  conditions int not null,
  rr double not null,
  noc int not null,
  sr double not null,
  dit int not null,
  mif double not null,
  aif double not null,
  nmo int not null,
  lcom int not null,
  tcc double not null
)
;

DROP TABLE IF EXISTS scala_metrics;
create table scala_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  valDeclCount int not null,
  varDeclCount int not null,
  funcDeclCount int not null,
  funcDefCount int not null,
  classDefCount int not null,
  importCount int not null,
  whileCount int not null,
  ifCount int not null,
  assignCount int not null,
  throwCount int not null,
  returnCount int not null,
  tryCount int not null,
  forCount int not null,
  annotationCount int not null,
  objectDefCount int not null,
  traitDefCount int not null,
  packageDefCount int not null,
  WMC double null,
  DIT int null,
  NOC int null,
  CBO int null,
  RFC int null,
  NPM int null
)
;

DROP TABLE IF EXISTS swift_metrics;
create table swift_metrics
(
  id int auto_increment
    primary key,
  full_name varchar(255) not null,
  functionCount int not null,
  classCount int not null,
  variablesCount int not null,
  constantCount int not null,
  loopsCount int not null,
  conditionsCount int not null
)
;