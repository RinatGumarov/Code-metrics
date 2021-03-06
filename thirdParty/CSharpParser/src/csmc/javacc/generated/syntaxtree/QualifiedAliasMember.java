//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> Identifier()
 * f1 -> <DBL_COLON>
 * f2 -> Identifier()
 * f3 -> TypeArgumentListOpt()
 */
public class QualifiedAliasMember implements Node {
   public Identifier f0;
   public NodeToken f1;
   public Identifier f2;
   public TypeArgumentListOpt f3;

   public QualifiedAliasMember(Identifier n0, NodeToken n1, Identifier n2, TypeArgumentListOpt n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public QualifiedAliasMember(Identifier n0, Identifier n1, TypeArgumentListOpt n2) {
      f0 = n0;
      f1 = new NodeToken("::");
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

