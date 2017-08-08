//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <FIXED>
 * f1 -> <LPAREN>
 * f2 -> PointerType()
 * f3 -> FixedPointerDeclarators()
 * f4 -> <RPAREN>
 * f5 -> EmbeddedStatement()
 */
public class FixedStatement implements Node {
   public NodeToken f0;
   public NodeToken f1;
   public PointerType f2;
   public FixedPointerDeclarators f3;
   public NodeToken f4;
   public EmbeddedStatement f5;

   public FixedStatement(NodeToken n0, NodeToken n1, PointerType n2, FixedPointerDeclarators n3, NodeToken n4, EmbeddedStatement n5) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
   }

   public FixedStatement(PointerType n0, FixedPointerDeclarators n1, EmbeddedStatement n2) {
      f0 = new NodeToken("fixed");
      f1 = new NodeToken("(");
      f2 = n0;
      f3 = n1;
      f4 = new NodeToken(")");
      f5 = n2;
   }

   public void accept(csmc.javacc.generated.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(csmc.javacc.generated.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(csmc.javacc.generated.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(csmc.javacc.generated.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}
