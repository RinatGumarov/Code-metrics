package pythonParser;/* Generated By:JavaCC: Do not edit this line. pythonParser.PythonGrammarConstants.java */

/**
 * pythonParser.Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface PythonGrammarConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SPACE = 1;
  /** RegularExpression Id. */
  int EOL = 2;
  /** RegularExpression Id. */
  int INDENT = 3;
  /** RegularExpression Id. */
  int OPERATOR = 4;
  /** RegularExpression Id. */
  int PLUS = 5;
  /** RegularExpression Id. */
  int MINUS = 6;
  /** RegularExpression Id. */
  int MULTIPLY = 7;
  /** RegularExpression Id. */
  int DIVIDE = 8;
  /** RegularExpression Id. */
  int FLOORDIVIDE = 9;
  /** RegularExpression Id. */
  int POWER = 10;
  /** RegularExpression Id. */
  int LSHIFT = 11;
  /** RegularExpression Id. */
  int RSHIFT = 12;
  /** RegularExpression Id. */
  int MODULUS = 13;
  /** RegularExpression Id. */
  int NOT = 14;
  /** RegularExpression Id. */
  int XOR = 15;
  /** RegularExpression Id. */
  int OR = 16;
  /** RegularExpression Id. */
  int AND = 17;
  /** RegularExpression Id. */
  int EQUAL = 18;
  /** RegularExpression Id. */
  int GREATER = 19;
  /** RegularExpression Id. */
  int LESS = 20;
  /** RegularExpression Id. */
  int EQEQUAL = 21;
  /** RegularExpression Id. */
  int EQLESS = 22;
  /** RegularExpression Id. */
  int EQGREATER = 23;
  /** RegularExpression Id. */
  int LESSGREATER = 24;
  /** RegularExpression Id. */
  int NOTEQUAL = 25;
  /** RegularExpression Id. */
  int PLUSEQ = 26;
  /** RegularExpression Id. */
  int MINUSEQ = 27;
  /** RegularExpression Id. */
  int MULTIPLYEQ = 28;
  /** RegularExpression Id. */
  int DIVIDEEQ = 29;
  /** RegularExpression Id. */
  int FLOORDIVIDEEQ = 30;
  /** RegularExpression Id. */
  int MODULOEQ = 31;
  /** RegularExpression Id. */
  int ANDEQ = 32;
  /** RegularExpression Id. */
  int OREQ = 33;
  /** RegularExpression Id. */
  int XOREQ = 34;
  /** RegularExpression Id. */
  int LSHIFTEQ = 35;
  /** RegularExpression Id. */
  int RSHIFTEQ = 36;
  /** RegularExpression Id. */
  int POWEREQ = 37;
  /** RegularExpression Id. */
  int CONDITION = 38;
  /** RegularExpression Id. */
  int TRUE = 39;
  /** RegularExpression Id. */
  int FALSE = 40;
  /** RegularExpression Id. */
  int LPAREN = 41;
  /** RegularExpression Id. */
  int RPAREN = 42;
  /** RegularExpression Id. */
  int LBRACE = 43;
  /** RegularExpression Id. */
  int RBRACE = 44;
  /** RegularExpression Id. */
  int LBRACKET = 45;
  /** RegularExpression Id. */
  int RBRACKET = 46;
  /** RegularExpression Id. */
  int SEMICOLON = 47;
  /** RegularExpression Id. */
  int COMMA = 48;
  /** RegularExpression Id. */
  int DOT = 49;
  /** RegularExpression Id. */
  int COLON = 50;
  /** RegularExpression Id. */
  int OR_BOOL = 51;
  /** RegularExpression Id. */
  int AND_BOOL = 52;
  /** RegularExpression Id. */
  int NOT_BOOL = 53;
  /** RegularExpression Id. */
  int IS = 54;
  /** RegularExpression Id. */
  int IN = 55;
  /** RegularExpression Id. */
  int LAMBDA = 56;
  /** RegularExpression Id. */
  int IF = 57;
  /** RegularExpression Id. */
  int ELSE = 58;
  /** RegularExpression Id. */
  int ELIF = 59;
  /** RegularExpression Id. */
  int WHILE = 60;
  /** RegularExpression Id. */
  int FOR = 61;
  /** RegularExpression Id. */
  int TRY = 62;
  /** RegularExpression Id. */
  int EXCEPT = 63;
  /** RegularExpression Id. */
  int DEF = 64;
  /** RegularExpression Id. */
  int CLASS = 65;
  /** RegularExpression Id. */
  int FINALLY = 66;
  /** RegularExpression Id. */
  int PRINT = 67;
  /** RegularExpression Id. */
  int PASS = 68;
  /** RegularExpression Id. */
  int BREAK = 69;
  /** RegularExpression Id. */
  int CONTINUE = 70;
  /** RegularExpression Id. */
  int RETURN = 71;
  /** RegularExpression Id. */
  int YIELD = 72;
  /** RegularExpression Id. */
  int IMPORT = 73;
  /** RegularExpression Id. */
  int FROM = 74;
  /** RegularExpression Id. */
  int DEL = 75;
  /** RegularExpression Id. */
  int RAISE = 76;
  /** RegularExpression Id. */
  int GLOBAL = 77;
  /** RegularExpression Id. */
  int EXEC = 78;
  /** RegularExpression Id. */
  int ASSERT = 79;
  /** RegularExpression Id. */
  int AS = 80;
  /** RegularExpression Id. */
  int WITH = 81;
  /** RegularExpression Id. */
  int AT = 82;
  /** RegularExpression Id. */
  int RANGE = 83;
  /** RegularExpression Id. */
  int METHOD = 84;
  /** RegularExpression Id. */
  int CLASSNAME = 85;
  /** RegularExpression Id. */
  int RETURNVALUE = 86;
  /** RegularExpression Id. */
  int PRINT_COMMAND = 87;
  /** RegularExpression Id. */
  int CYCLE_FOR = 88;
  /** RegularExpression Id. */
  int CYCLE_WHILE = 89;
  /** RegularExpression Id. */
  int IMPORT_LINE = 90;
  /** RegularExpression Id. */
  int IMPORT_LINE_AS = 91;
  /** RegularExpression Id. */
  int STRING = 92;
  /** RegularExpression Id. */
  int NAME = 93;
  /** RegularExpression Id. */
  int LETTER = 94;
  /** RegularExpression Id. */
  int DECNUMBER = 95;
  /** RegularExpression Id. */
  int HEXNUMBER = 96;
  /** RegularExpression Id. */
  int OCTNUMBER = 97;
  /** RegularExpression Id. */
  int FLOAT = 98;
  /** RegularExpression Id. */
  int COMPLEX = 99;
  /** RegularExpression Id. */
  int EXPONENT = 100;
  /** RegularExpression Id. */
  int DIGIT = 101;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "<SPACE>",
    "<EOL>",
    "<INDENT>",
    "<OPERATOR>",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"//\"",
    "\"**\"",
    "\"<<\"",
    "\">>\"",
    "\"%\"",
    "\"~\"",
    "\"^\"",
    "\"|\"",
    "\"&\"",
    "\"=\"",
    "\">\"",
    "\"<\"",
    "\"==\"",
    "\"<=\"",
    "\">=\"",
    "\"<>\"",
    "\"!=\"",
    "\"+=\"",
    "\"-=\"",
    "\"*=\"",
    "\"/=\"",
    "\"//=\"",
    "\"%=\"",
    "\"&=\"",
    "\"|=\"",
    "\"^=\"",
    "\"<<=\"",
    "\">>=\"",
    "\"**=\"",
    "<CONDITION>",
    "\"True\"",
    "\"False\"",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\",\"",
    "\".\"",
    "\":\"",
    "\"or\"",
    "\"and\"",
    "\"not\"",
    "\"is\"",
    "\"in\"",
    "\"lambda\"",
    "\"if\"",
    "\"else\"",
    "\"elif\"",
    "\"while\"",
    "\"for\"",
    "\"try\"",
    "\"except\"",
    "\"def\"",
    "\"class\"",
    "\"finally\"",
    "\"print\"",
    "\"pass\"",
    "\"break\"",
    "\"continue\"",
    "\"return\"",
    "\"yield\"",
    "\"import\"",
    "\"from\"",
    "\"del\"",
    "\"raise\"",
    "\"global\"",
    "\"exec\"",
    "\"assert\"",
    "\"as\"",
    "\"with\"",
    "\"@\"",
    "\"range\"",
    "<METHOD>",
    "<CLASSNAME>",
    "<RETURNVALUE>",
    "<PRINT_COMMAND>",
    "<CYCLE_FOR>",
    "<CYCLE_WHILE>",
    "<IMPORT_LINE>",
    "<IMPORT_LINE_AS>",
    "<STRING>",
    "<NAME>",
    "<LETTER>",
    "<DECNUMBER>",
    "<HEXNUMBER>",
    "<OCTNUMBER>",
    "<FLOAT>",
    "<COMPLEX>",
    "<EXPONENT>",
    "<DIGIT>",
  };

}
