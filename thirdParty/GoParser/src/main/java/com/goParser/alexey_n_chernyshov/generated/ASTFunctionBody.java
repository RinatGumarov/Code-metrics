/* Generated By:JJTree: Do not edit this line. ASTFunctionBody.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.goParser.alexey_n_chernyshov.generated;

public
class ASTFunctionBody extends SimpleNode {
  public ASTFunctionBody(int id) {
    super(id);
  }

  public ASTFunctionBody(GoParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GoParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8f631b7f49a7882d4c4d9ff1632e7287 (do not edit this line) */
