//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> AttributesOpt()
 * f1 -> DelegateModifierList()
 * f2 -> <DELEGATE>
 * f3 -> ReturnType()
 * f4 -> Identifier()
 * f5 -> VariantTypeParameterListOpt()
 * f6 -> <LPAREN>
 * f7 -> FormalParameterListOpt()
 * f8 -> <RPAREN>
 * f9 -> TypeParameterConstraintsClauseList()
 * f10 -> SemicolonOpt()
 */
public class DelegateDeclaration implements Node {
   public AttributesOpt f0;
   public DelegateModifierList f1;
   public NodeToken f2;
   public ReturnType f3;
   public Identifier f4;
   public VariantTypeParameterListOpt f5;
   public NodeToken f6;
   public FormalParameterListOpt f7;
   public NodeToken f8;
   public TypeParameterConstraintsClauseList f9;
   public SemicolonOpt f10;

   public DelegateDeclaration(AttributesOpt n0, DelegateModifierList n1, NodeToken n2, ReturnType n3, Identifier n4, VariantTypeParameterListOpt n5, NodeToken n6, FormalParameterListOpt n7, NodeToken n8, TypeParameterConstraintsClauseList n9, SemicolonOpt n10) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
      f6 = n6;
      f7 = n7;
      f8 = n8;
      f9 = n9;
      f10 = n10;
   }

   public DelegateDeclaration(AttributesOpt n0, DelegateModifierList n1, ReturnType n2, Identifier n3, VariantTypeParameterListOpt n4, FormalParameterListOpt n5, TypeParameterConstraintsClauseList n6, SemicolonOpt n7) {
      f0 = n0;
      f1 = n1;
      f2 = new NodeToken("delegate");
      f3 = n2;
      f4 = n3;
      f5 = n4;
      f6 = new NodeToken("(");
      f7 = n5;
      f8 = new NodeToken(")");
      f9 = n6;
      f10 = n7;
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

