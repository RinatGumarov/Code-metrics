//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> AttributesOpt()
 * f1 -> AccessorModifierOpt()
 * f2 -> <SET>
 * f3 -> AccessorBody()
 */
public class SetAccessorDeclaration implements Node {
   public AttributesOpt f0;
   public AccessorModifierOpt f1;
   public NodeToken f2;
   public AccessorBody f3;

   public SetAccessorDeclaration(AttributesOpt n0, AccessorModifierOpt n1, NodeToken n2, AccessorBody n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public SetAccessorDeclaration(AttributesOpt n0, AccessorModifierOpt n1, AccessorBody n2) {
      f0 = n0;
      f1 = n1;
      f2 = new NodeToken("set");
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

