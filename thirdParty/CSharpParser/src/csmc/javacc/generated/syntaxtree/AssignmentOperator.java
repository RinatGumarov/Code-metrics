//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * Grammar production:
 * f0 -> <EQUAL>
 *       | <PLUS_EQUAL>
 *       | <MINUS_EQUAL>
 *       | <ASTERISK_EQUAL>
 *       | <SLASH_EQUAL>
 *       | <PERCENT_EQUAL>
 *       | <AMPERSAND_EQUAL>
 *       | <VERTICAL_EQUAL>
 *       | <CARET_EQUAL>
 *       | <DBL_LESS_EQUAL>
 *       | <DBL_GREATER_EQUAL>
 */
public class AssignmentOperator implements Node {
   public NodeChoice f0;

   public AssignmentOperator(NodeChoice n0) {
      f0 = n0;
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

