/* Generated By:JJTree: Do not edit this line. ASTFunctionDecl.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.goParser.alexey_n_chernyshov.generated;

public
class ASTFunctionDecl extends SimpleNode {
  public ASTFunctionDecl(int id) {
    super(id);
  }

  public ASTFunctionDecl(GoParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GoParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b82561deb5b3cc3b12146f0040c7443d (do not edit this line) */
