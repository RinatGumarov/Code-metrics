/* Generated By:JJTree: Do not edit this line. ASTReciever.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.goParser.alexey_n_chernyshov.generated;

public
class ASTReciever extends SimpleNode {
  public ASTReciever(int id) {
    super(id);
  }

  public ASTReciever(GoParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GoParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=503f80f54603a24647f72199dd713bbd (do not edit this line) */
