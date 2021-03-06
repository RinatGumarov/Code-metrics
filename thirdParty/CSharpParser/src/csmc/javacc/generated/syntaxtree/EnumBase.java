//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <COLON>
 * f1 -> IntegralType()
 */
public class EnumBase implements Node {
   public NodeToken f0;
   public IntegralType f1;

   public EnumBase(NodeToken n0, IntegralType n1) {
      f0 = n0;
      f1 = n1;
   }

   public EnumBase(IntegralType n0) {
      f0 = new NodeToken(":");
      f1 = n0;
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

