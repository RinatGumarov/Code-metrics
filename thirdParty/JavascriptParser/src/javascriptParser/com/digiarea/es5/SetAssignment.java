package javascriptParser.com.digiarea.es5;

import javascriptParser.com.digiarea.es5.visitor.VoidVisitor;
import javascriptParser.com.digiarea.es5.visitor.GenericVisitor;

/** 
 * The Class SetAssignment.
 */
public class SetAssignment extends GetAssignment {

    /** 
     * The identifier.
     */
    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    SetAssignment() {
        super();
    }

    SetAssignment(String identifier, Block block, PropertyName propertyName, Comment comment, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(block, propertyName, comment, jsDocComment, posBegin, posEnd);
        this.identifier = identifier;
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
