//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> LamdaExpressionModifierList()
 * f1 -> AnonymousFunctionSignature()
 * f2 -> <EQUAL_GREATER>
 * f3 -> AnonymousFunctionBody()
 */
public class LambdaExpression implements Node {
   public LamdaExpressionModifierList f0;
   public AnonymousFunctionSignature f1;
   public NodeToken f2;
   public AnonymousFunctionBody f3;

   public LambdaExpression(LamdaExpressionModifierList n0, AnonymousFunctionSignature n1, NodeToken n2, AnonymousFunctionBody n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public LambdaExpression(LamdaExpressionModifierList n0, AnonymousFunctionSignature n1, AnonymousFunctionBody n2) {
      f0 = n0;
      f1 = n1;
      f2 = new NodeToken("=>");
      f3 = n2;
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

