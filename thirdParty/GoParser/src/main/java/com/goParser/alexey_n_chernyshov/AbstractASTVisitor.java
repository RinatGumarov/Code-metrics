package com.goParser.alexey_n_chernyshov;

import com.goParser.alexey_n_chernyshov.generated.ASTImaginaryLiteral;
import com.goParser.alexey_n_chernyshov.generated.GoParserVisitor;
import com.goParser.alexey_n_chernyshov.generated.SimpleNode;

/**
 * @author Yex
 */
public abstract class AbstractASTVisitor implements GoParserVisitor {

    protected void visitAllChildren(SimpleNode node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
    }

    public abstract Object visit(ASTImaginaryLiteral node, Object data);
}
