//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> PrimaryNoArrayCreationExpression()
 * f1 -> <LBRACKET>
 * f2 -> ExpressionList()
 * f3 -> <RBRACKET>
 */
public class ElementAccess implements Node {
   public PrimaryNoArrayCreationExpression f0;
   public NodeToken f1;
   public ExpressionList f2;
   public NodeToken f3;

   public ElementAccess(PrimaryNoArrayCreationExpression n0, NodeToken n1, ExpressionList n2, NodeToken n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public ElementAccess(PrimaryNoArrayCreationExpression n0, ExpressionList n1) {
      f0 = n0;
      f1 = new NodeToken("[");
      f2 = n1;
      f3 = new NodeToken("]");
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

