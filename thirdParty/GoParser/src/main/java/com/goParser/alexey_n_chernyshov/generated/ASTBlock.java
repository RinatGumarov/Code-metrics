/* Generated By:JJTree: Do not edit this line. ASTBlock.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.goParser.alexey_n_chernyshov.generated;

public
class ASTBlock extends SimpleNode {
  public ASTBlock(int id) {
    super(id);
  }

  public ASTBlock(GoParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GoParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=2ec296fa31adf761e208e2df5a40c64d (do not edit this line) */
