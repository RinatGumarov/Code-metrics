//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> ( <ASYNC> )?
 * f1 -> <DELEGATE>
 * f2 -> ExplicitAnonymousFunctionSignatureOpt()
 * f3 -> Block()
 */
public class AnonymousMethodExpression implements Node {
   public NodeOptional f0;
   public NodeToken f1;
   public ExplicitAnonymousFunctionSignatureOpt f2;
   public Block f3;

   public AnonymousMethodExpression(NodeOptional n0, NodeToken n1, ExplicitAnonymousFunctionSignatureOpt n2, Block n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public AnonymousMethodExpression(NodeOptional n0, ExplicitAnonymousFunctionSignatureOpt n1, Block n2) {
      f0 = n0;
      f1 = new NodeToken("delegate");
      f2 = n1;
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

