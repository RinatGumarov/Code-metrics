package javascriptParser.com.digiarea.es5;

import javascriptParser.com.digiarea.es5.visitor.VoidVisitor;
import javascriptParser.com.digiarea.es5.visitor.GenericVisitor;

/** 
 * The Class ImportStatement.
 */
public class ImportStatement extends Statement {

    /** 
     * The name.
     */
    private IdentifierName name;

    public IdentifierName getName() {
        return name;
    }

    public void setName(IdentifierName name) {
        this.name = name;
    }

    ImportStatement() {
        super();
    }

    ImportStatement(IdentifierName name, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
        this.name = name;
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
