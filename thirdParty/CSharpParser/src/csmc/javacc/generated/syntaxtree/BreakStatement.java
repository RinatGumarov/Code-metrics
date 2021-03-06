//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <BREAK>
 * f1 -> <SEMICOLON>
 */
public class BreakStatement implements Node {
   public NodeToken f0;
   public NodeToken f1;

   public BreakStatement(NodeToken n0, NodeToken n1) {
      f0 = n0;
      f1 = n1;
   }

   public BreakStatement() {
      f0 = new NodeToken("break");
      f1 = new NodeToken(";");
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

