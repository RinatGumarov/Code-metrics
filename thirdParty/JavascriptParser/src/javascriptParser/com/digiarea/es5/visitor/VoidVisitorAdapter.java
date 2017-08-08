package javascriptParser.com.digiarea.es5.visitor;

import javascriptParser.com.digiarea.es5.AllocationExpression;
import javascriptParser.com.digiarea.es5.ArrayAccessExpression;
import javascriptParser.com.digiarea.es5.ArrayLiteral;
import javascriptParser.com.digiarea.es5.AssignmentExpression;
import javascriptParser.com.digiarea.es5.AssignmentExpression.AssignOperator;
import javascriptParser.com.digiarea.es5.BinaryExpression;
import javascriptParser.com.digiarea.es5.BinaryExpression.BinaryOperator;
import javascriptParser.com.digiarea.es5.Block;
import javascriptParser.com.digiarea.es5.BlockComment;
import javascriptParser.com.digiarea.es5.BooleanLiteral;
import javascriptParser.com.digiarea.es5.BreakStatement;
import javascriptParser.com.digiarea.es5.CallExpression;
import javascriptParser.com.digiarea.es5.CaseBlock;
import javascriptParser.com.digiarea.es5.CaseClause;
import javascriptParser.com.digiarea.es5.CatchClause;
import javascriptParser.com.digiarea.es5.CompilationUnit;
import javascriptParser.com.digiarea.es5.ConditionalExpression;
import javascriptParser.com.digiarea.es5.ConstantStatement;
import javascriptParser.com.digiarea.es5.ContinueStatement;
import javascriptParser.com.digiarea.es5.DebuggerStatement;
import javascriptParser.com.digiarea.es5.DecimalLiteral;
import javascriptParser.com.digiarea.es5.DefaultClause;
import javascriptParser.com.digiarea.es5.DoWhileStatement;
import javascriptParser.com.digiarea.es5.EmptyLiteral;
import javascriptParser.com.digiarea.es5.EmptyStatement;
import javascriptParser.com.digiarea.es5.EnclosedExpression;
import javascriptParser.com.digiarea.es5.ExpressionStatement;
import javascriptParser.com.digiarea.es5.FieldAccessExpression;
import javascriptParser.com.digiarea.es5.FloatLiteral;
import javascriptParser.com.digiarea.es5.ForeachStatement;
import javascriptParser.com.digiarea.es5.ForStatement;
import javascriptParser.com.digiarea.es5.FunctionDeclaration;
import javascriptParser.com.digiarea.es5.FunctionExpression;
import javascriptParser.com.digiarea.es5.GetAssignment;
import javascriptParser.com.digiarea.es5.HexIntegerLiteral;
import javascriptParser.com.digiarea.es5.IdentifierName;
import javascriptParser.com.digiarea.es5.IfStatement;
import javascriptParser.com.digiarea.es5.ImportStatement;
import javascriptParser.com.digiarea.es5.JSDocComment;
import javascriptParser.com.digiarea.es5.LabelledStatement;
import javascriptParser.com.digiarea.es5.LetDefinition;
import javascriptParser.com.digiarea.es5.LetExpression;
import javascriptParser.com.digiarea.es5.LetStatement;
import javascriptParser.com.digiarea.es5.LineComment;
import javascriptParser.com.digiarea.es5.NewExpression;
import javascriptParser.com.digiarea.es5.Node;
import javascriptParser.com.digiarea.es5.NodeList;
import javascriptParser.com.digiarea.es5.NullLiteral;
import javascriptParser.com.digiarea.es5.ObjectLiteral;
import javascriptParser.com.digiarea.es5.OctalLiteral;
import javascriptParser.com.digiarea.es5.Parameter;
import javascriptParser.com.digiarea.es5.Project;
import javascriptParser.com.digiarea.es5.PutAssignment;
import javascriptParser.com.digiarea.es5.RegexpLiteral;
import javascriptParser.com.digiarea.es5.ReturnStatement;
import javascriptParser.com.digiarea.es5.SequenceExpression;
import javascriptParser.com.digiarea.es5.SetAssignment;
import javascriptParser.com.digiarea.es5.StringLiteralDouble;
import javascriptParser.com.digiarea.es5.StringLiteralSingle;
import javascriptParser.com.digiarea.es5.SuperExpression;
import javascriptParser.com.digiarea.es5.SwitchStatement;
import javascriptParser.com.digiarea.es5.ThisExpression;
import javascriptParser.com.digiarea.es5.ThrowStatement;
import javascriptParser.com.digiarea.es5.TryStatement;
import javascriptParser.com.digiarea.es5.UnaryExpression;
import javascriptParser.com.digiarea.es5.UnaryExpression.UnaryOperator;
import javascriptParser.com.digiarea.es5.VariableDeclaration;
import javascriptParser.com.digiarea.es5.VariableExpression;
import javascriptParser.com.digiarea.es5.VariableStatement;
import javascriptParser.com.digiarea.es5.WhileStatement;
import javascriptParser.com.digiarea.es5.WithStatement;

public class VoidVisitorAdapter<C> implements VoidVisitor<C> {

    @Override
    public void visit(AllocationExpression n, C ctx) throws Exception {
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ArrayAccessExpression n, C ctx) throws Exception {
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getIndex() != null) {
            n.getIndex().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ArrayLiteral n, C ctx) throws Exception {
        if (n.getExpressions() != null) {
            n.getExpressions().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(AssignmentExpression n, C ctx) throws Exception {
        if (n.getTarget() != null) {
            n.getTarget().accept(this, ctx);
        }
        if (n.getValue() != null) {
            n.getValue().accept(this, ctx);
        }
        if (n.getAssignOperator() != null) {
            n.getAssignOperator().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(AssignOperator n, C ctx) throws Exception {
    }

    @Override
    public void visit(BinaryExpression n, C ctx) throws Exception {
        if (n.getLeft() != null) {
            n.getLeft().accept(this, ctx);
        }
        if (n.getRight() != null) {
            n.getRight().accept(this, ctx);
        }
        if (n.getBinaryOperator() != null) {
            n.getBinaryOperator().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(BinaryOperator n, C ctx) throws Exception {
    }

    @Override
    public void visit(Block n, C ctx) throws Exception {
        if (n.getStatements() != null) {
            n.getStatements().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(BlockComment n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(BooleanLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(BreakStatement n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(CallExpression n, C ctx) throws Exception {
        if (n.getScope() != null) {
            n.getScope().accept(this, ctx);
        }
        if (n.getArgs() != null) {
            n.getArgs().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(CaseBlock n, C ctx) throws Exception {
        if (n.getDefaultClause() != null) {
            n.getDefaultClause().accept(this, ctx);
        }
        if (n.getCaseClauses() != null) {
            n.getCaseClauses().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(CaseClause n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getStatements() != null) {
            n.getStatements().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(CatchClause n, C ctx) throws Exception {
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(CompilationUnit n, C ctx) throws Exception {
        if (n.getElements() != null) {
            n.getElements().accept(this, ctx);
        }
        if (n.getComments() != null) {
            n.getComments().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ConditionalExpression n, C ctx) throws Exception {
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getThenExpr() != null) {
            n.getThenExpr().accept(this, ctx);
        }
        if (n.getElseExpr() != null) {
            n.getElseExpr().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ConstantStatement n, C ctx) throws Exception {
        if (n.getVariableDeclarations() != null) {
            n.getVariableDeclarations().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ContinueStatement n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(DebuggerStatement n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(DecimalLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(DefaultClause n, C ctx) throws Exception {
        if (n.getStatements() != null) {
            n.getStatements().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(DoWhileStatement n, C ctx) throws Exception {
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(EmptyLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(EmptyStatement n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(EnclosedExpression n, C ctx) throws Exception {
        if (n.getInner() != null) {
            n.getInner().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ExpressionStatement n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(FieldAccessExpression n, C ctx) throws Exception {
        if (n.getScope() != null) {
            n.getScope().accept(this, ctx);
        }
        if (n.getField() != null) {
            n.getField().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(FloatLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ForeachStatement n, C ctx) throws Exception {
        if (n.getVariable() != null) {
            n.getVariable().accept(this, ctx);
        }
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ForStatement n, C ctx) throws Exception {
        if (n.getVariable() != null) {
            n.getVariable().accept(this, ctx);
        }
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getExpr() != null) {
            n.getExpr().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(FunctionDeclaration n, C ctx) throws Exception {
        if (n.getParameters() != null) {
            n.getParameters().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(FunctionExpression n, C ctx) throws Exception {
        if (n.getParameters() != null) {
            n.getParameters().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(GetAssignment n, C ctx) throws Exception {
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getPropertyName() != null) {
            n.getPropertyName().accept(this, ctx);
        }
        if (n.getComment() != null) {
            n.getComment().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(HexIntegerLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(IdentifierName n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(IfStatement n, C ctx) throws Exception {
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getThenStatement() != null) {
            n.getThenStatement().accept(this, ctx);
        }
        if (n.getElseStatement() != null) {
            n.getElseStatement().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ImportStatement n, C ctx) throws Exception {
        if (n.getName() != null) {
            n.getName().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(JSDocComment n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(LabelledStatement n, C ctx) throws Exception {
        if (n.getStatement() != null) {
            n.getStatement().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(LetDefinition n, C ctx) throws Exception {
        if (n.getVariableDeclarations() != null) {
            n.getVariableDeclarations().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(LetExpression n, C ctx) throws Exception {
        if (n.getVariableDeclarations() != null) {
            n.getVariableDeclarations().accept(this, ctx);
        }
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(LetStatement n, C ctx) throws Exception {
        if (n.getVariableDeclarations() != null) {
            n.getVariableDeclarations().accept(this, ctx);
        }
        if (n.getStatement() != null) {
            n.getStatement().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(LineComment n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(NewExpression n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getInitializer() != null) {
            n.getInitializer().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public <E extends Node> void visit(NodeList<E> n, C ctx) throws Exception {
        if (n.getNodes() != null) {
            for (E item : n.getNodes()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(NullLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ObjectLiteral n, C ctx) throws Exception {
        if (n.getPropertyAssignments() != null) {
            n.getPropertyAssignments().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(OctalLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(Parameter n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(Project n, C ctx) throws Exception {
        if (n.getCompilationUnits() != null) {
            n.getCompilationUnits().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(PutAssignment n, C ctx) throws Exception {
        if (n.getPropertyValue() != null) {
            n.getPropertyValue().accept(this, ctx);
        }
        if (n.getPropertyName() != null) {
            n.getPropertyName().accept(this, ctx);
        }
        if (n.getComment() != null) {
            n.getComment().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(RegexpLiteral n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ReturnStatement n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(SequenceExpression n, C ctx) throws Exception {
        if (n.getExpressions() != null) {
            n.getExpressions().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(SetAssignment n, C ctx) throws Exception {
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getPropertyName() != null) {
            n.getPropertyName().accept(this, ctx);
        }
        if (n.getComment() != null) {
            n.getComment().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(StringLiteralDouble n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(StringLiteralSingle n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(SuperExpression n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(SwitchStatement n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getBlock() != null) {
            n.getBlock().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ThisExpression n, C ctx) throws Exception {
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(ThrowStatement n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(TryStatement n, C ctx) throws Exception {
        if (n.getTryBlock() != null) {
            n.getTryBlock().accept(this, ctx);
        }
        if (n.getCatchClause() != null) {
            n.getCatchClause().accept(this, ctx);
        }
        if (n.getFinallyBlock() != null) {
            n.getFinallyBlock().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(UnaryExpression n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getUnaryOperator() != null) {
            n.getUnaryOperator().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(UnaryOperator n, C ctx) throws Exception {
    }

    @Override
    public void visit(VariableDeclaration n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(VariableExpression n, C ctx) throws Exception {
        if (n.getVariableDeclarations() != null) {
            n.getVariableDeclarations().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(VariableStatement n, C ctx) throws Exception {
        if (n.getVariableDeclarations() != null) {
            n.getVariableDeclarations().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(WhileStatement n, C ctx) throws Exception {
        if (n.getCondition() != null) {
            n.getCondition().accept(this, ctx);
        }
        if (n.getBody() != null) {
            n.getBody().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    @Override
    public void visit(WithStatement n, C ctx) throws Exception {
        if (n.getExpression() != null) {
            n.getExpression().accept(this, ctx);
        }
        if (n.getStatement() != null) {
            n.getStatement().accept(this, ctx);
        }
        if (n.getJsDocComment() != null) {
            n.getJsDocComment().accept(this, ctx);
        }
    }

    public VoidVisitorAdapter() {
        super();
    }

}
