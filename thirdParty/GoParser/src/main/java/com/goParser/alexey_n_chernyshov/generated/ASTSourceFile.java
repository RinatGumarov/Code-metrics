/* Generated By:JJTree: Do not edit this line. ASTSourceFile.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.goParser.alexey_n_chernyshov.generated;

public
class ASTSourceFile extends SimpleNode {
  public ASTSourceFile(int id) {
    super(id);
  }

  public ASTSourceFile(GoParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GoParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=4a3c64a2dc3fe2ab67c2bb06f24a74e1 (do not edit this line) */
