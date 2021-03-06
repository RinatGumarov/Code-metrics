//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> Type()
 * f1 -> <THIS>
 * f2 -> <LBRACKET>
 * f3 -> FormalParameterList()
 * f4 -> <RBRACKET>
 * f5 -> <LBRACE>
 * f6 -> InterfaceAccessors()
 * f7 -> <RBRACE>
 */
public class InterfaceIndexerDeclaration implements Node {
   public Type f0;
   public NodeToken f1;
   public NodeToken f2;
   public FormalParameterList f3;
   public NodeToken f4;
   public NodeToken f5;
   public InterfaceAccessors f6;
   public NodeToken f7;

   public InterfaceIndexerDeclaration(Type n0, NodeToken n1, NodeToken n2, FormalParameterList n3, NodeToken n4, NodeToken n5, InterfaceAccessors n6, NodeToken n7) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
      f6 = n6;
      f7 = n7;
   }

   public InterfaceIndexerDeclaration(Type n0, FormalParameterList n1, InterfaceAccessors n2) {
      f0 = n0;
      f1 = new NodeToken("this");
      f2 = new NodeToken("[");
      f3 = n1;
      f4 = new NodeToken("]");
      f5 = new NodeToken("{");
      f6 = n2;
      f7 = new NodeToken("}");
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

