/* Generated By:JJTree: Do not edit this line. ASTUnaryMinus.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.goParser.alexey_n_chernyshov.generated;

public
class ASTUnaryMinus extends SimpleNode {
  public ASTUnaryMinus(int id) {
    super(id);
  }

  public ASTUnaryMinus(GoParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GoParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=1ee15d1b04de51b386171507e3ceaf52 (do not edit this line) */
