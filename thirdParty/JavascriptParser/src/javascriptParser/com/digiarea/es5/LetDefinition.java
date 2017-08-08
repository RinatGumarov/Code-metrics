package javascriptParser.com.digiarea.es5;

import javascriptParser.com.digiarea.es5.visitor.VoidVisitor;
import javascriptParser.com.digiarea.es5.visitor.GenericVisitor;

/** 
 * The Class LetDefinition.
 */
public class LetDefinition extends Statement {

    /** 
     * The variable declarations.
     */
    private NodeList<VariableDeclaration> variableDeclarations = null;

    public NodeList<VariableDeclaration> getVariableDeclarations() {
        return variableDeclarations;
    }

    public void setVariableDeclarations(NodeList<VariableDeclaration> variableDeclarations) {
        this.variableDeclarations = variableDeclarations;
    }

    LetDefinition() {
        super();
    }

    LetDefinition(NodeList<VariableDeclaration> variableDeclarations, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
        this.variableDeclarations = variableDeclarations;
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
