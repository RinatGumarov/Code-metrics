/* Generated By:JJTree: Do not edit this line. ASTPackageClause.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.goParser.alexey_n_chernyshov.generated;

public
class ASTPackageClause extends SimpleNode {
  public ASTPackageClause(int id) {
    super(id);
  }

  public ASTPackageClause(GoParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(GoParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=dce7b0b0b66c13b31efc6b9e53065fd0 (do not edit this line) */