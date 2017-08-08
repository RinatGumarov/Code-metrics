package javascriptParser.com.digiarea.es5;

import javascriptParser.com.digiarea.es5.visitor.VoidVisitor;
import javascriptParser.com.digiarea.es5.visitor.GenericVisitor;

/** 
 * The Class AssignmentExpression.
 */
public class AssignmentExpression extends Expression {

    /** 
     * The Enum AssignOperator.
     */
    public static enum AssignOperator {

        /** 
         * The assign.
         */
        assign, 
        /** 
         * The plus.
         */
        plus, 
        /** 
         * The minus.
         */
        minus, 
        /** 
         * The star.
         */
        star, 
        /** 
         * The slash.
         */
        slash, 
        /** 
         * The and.
         */
        and, 
        /** 
         * The or.
         */
        or, 
        /** 
         * The xor.
         */
        xor, 
        /** 
         * The rem.
         */
        rem, 
        /** 
         * The l shift.
         */
        lShift, 
        /** 
         * The r signed shift.
         */
        rSignedShift, 
        /** 
         * The r unsigned shift.
         */
        rUnsignedShift;

        public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
            v.visit(this, ctx);
        }

        public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
            return v.visit(this, ctx);
        }

    }

    /** 
     * The target.
     */
    private Expression target;

    /** 
     * The value.
     */
    private Expression value;

    /** 
     * The assign operator.
     */
    private AssignOperator assignOperator;

    public Expression getTarget() {
        return target;
    }

    public void setTarget(Expression target) {
        this.target = target;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    public AssignOperator getAssignOperator() {
        return assignOperator;
    }

    public void setAssignOperator(AssignOperator assignOperator) {
        this.assignOperator = assignOperator;
    }

    AssignmentExpression() {
        super();
    }

    AssignmentExpression(Expression target, Expression value, AssignOperator assignOperator, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
        this.target = target;
        this.value = value;
        this.assignOperator = assignOperator;
    }

    @Override
    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    @Override
    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
