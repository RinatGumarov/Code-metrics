//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> AttributesOpt()
 * f1 -> PropertyModifierList()
 * f2 -> Type()
 * f3 -> MemberName()
 * f4 -> PropertyBody()
 */
public class PropertyDeclaration implements Node {
   public AttributesOpt f0;
   public PropertyModifierList f1;
   public Type f2;
   public MemberName f3;
   public PropertyBody f4;

   public PropertyDeclaration(AttributesOpt n0, PropertyModifierList n1, Type n2, MemberName n3, PropertyBody n4) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
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

