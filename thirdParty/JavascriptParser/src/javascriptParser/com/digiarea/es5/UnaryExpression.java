package javascriptParser.com.digiarea.es5;

import javascriptParser.com.digiarea.es5.visitor.VoidVisitor;
import javascriptParser.com.digiarea.es5.visitor.GenericVisitor;

/** 
 * The Class UnaryExpression.
 */
public final class UnaryExpression extends Expression {

    /** 
     * The Enum UnaryOperator.
     */
    public static enum UnaryOperator {

        /** 
         * The positive.
         */
        positive, 
        /** 
         * The negative.
         */
        negative, 
        /** 
         * The pre increment.
         */
        preIncrement, 
        /** 
         * The pre decrement.
         */
        preDecrement, 
        /** 
         * The not.
         */
        not, 
        /** 
         * The rem.
         */
        rem, 
        /** 
         * The inverse.
         */
        inverse, 
        /** 
         * The pos increment.
         */
        posIncrement, 
        /** 
         * The pos decrement.
         */
        posDecrement, 
        /** 
         * The delete.
         */
        delete, 
        /** 
         * The op void.
         */
        opVoid, 
        /** 
         * The typeof.
         */
        typeof;

        public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
            v.visit(this, ctx);
        }

        public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
            return v.visit(this, ctx);
        }

    }

    /** 
     * The expression.
     */
    private Expression expression;

    /** 
     * The unary operator.
     */
    private UnaryOperator unaryOperator;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    public void setUnaryOperator(UnaryOperator unaryOperator) {
        this.unaryOperator = unaryOperator;
    }

    UnaryExpression() {
        super();
    }

    UnaryExpression(Expression expression, UnaryOperator unaryOperator, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
        this.expression = expression;
        this.unaryOperator = unaryOperator;
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
