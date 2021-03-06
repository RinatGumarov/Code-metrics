package javascriptParser.com.digiarea.es5.visitor;

import javascriptParser.com.digiarea.es5.Node;
import javascriptParser.com.digiarea.es5.AllocationExpression;
import javascriptParser.com.digiarea.es5.NodeFacade;
import javascriptParser.com.digiarea.es5.Expression;
import javascriptParser.com.digiarea.es5.JSDocComment;
import javascriptParser.com.digiarea.es5.ArrayAccessExpression;
import javascriptParser.com.digiarea.es5.ArrayLiteral;
import javascriptParser.com.digiarea.es5.NodeList;
import javascriptParser.com.digiarea.es5.AssignmentExpression;
import javascriptParser.com.digiarea.es5.AssignmentExpression.AssignOperator;
import javascriptParser.com.digiarea.es5.BinaryExpression;
import javascriptParser.com.digiarea.es5.BinaryExpression.BinaryOperator;
import javascriptParser.com.digiarea.es5.Block;
import javascriptParser.com.digiarea.es5.Statement;
import javascriptParser.com.digiarea.es5.BlockComment;
import javascriptParser.com.digiarea.es5.BooleanLiteral;
import javascriptParser.com.digiarea.es5.BreakStatement;
import javascriptParser.com.digiarea.es5.CallExpression;
import javascriptParser.com.digiarea.es5.CaseBlock;
import javascriptParser.com.digiarea.es5.DefaultClause;
import javascriptParser.com.digiarea.es5.CaseClause;
import javascriptParser.com.digiarea.es5.CatchClause;
import javascriptParser.com.digiarea.es5.CompilationUnit;
import javascriptParser.com.digiarea.es5.Comment;
import javascriptParser.com.digiarea.es5.ConditionalExpression;
import javascriptParser.com.digiarea.es5.ConstantStatement;
import javascriptParser.com.digiarea.es5.VariableDeclaration;
import javascriptParser.com.digiarea.es5.ContinueStatement;
import javascriptParser.com.digiarea.es5.DebuggerStatement;
import javascriptParser.com.digiarea.es5.DecimalLiteral;
import javascriptParser.com.digiarea.es5.DoWhileStatement;
import javascriptParser.com.digiarea.es5.EmptyLiteral;
import javascriptParser.com.digiarea.es5.EmptyStatement;
import javascriptParser.com.digiarea.es5.EnclosedExpression;
import javascriptParser.com.digiarea.es5.ExpressionStatement;
import javascriptParser.com.digiarea.es5.FieldAccessExpression;
import javascriptParser.com.digiarea.es5.IdentifierName;
import javascriptParser.com.digiarea.es5.FloatLiteral;
import javascriptParser.com.digiarea.es5.ForeachStatement;
import javascriptParser.com.digiarea.es5.ForStatement;
import javascriptParser.com.digiarea.es5.FunctionDeclaration;
import javascriptParser.com.digiarea.es5.Parameter;
import javascriptParser.com.digiarea.es5.FunctionExpression;
import javascriptParser.com.digiarea.es5.GetAssignment;
import javascriptParser.com.digiarea.es5.PropertyName;
import javascriptParser.com.digiarea.es5.HexIntegerLiteral;
import javascriptParser.com.digiarea.es5.IfStatement;
import javascriptParser.com.digiarea.es5.ImportStatement;
import javascriptParser.com.digiarea.es5.LabelledStatement;
import javascriptParser.com.digiarea.es5.LetDefinition;
import javascriptParser.com.digiarea.es5.LetExpression;
import javascriptParser.com.digiarea.es5.LetStatement;
import javascriptParser.com.digiarea.es5.LineComment;
import javascriptParser.com.digiarea.es5.NewExpression;
import javascriptParser.com.digiarea.es5.ObjectLiteral;
import java.util.List;
import java.util.ArrayList;
import javascriptParser.com.digiarea.es5.NullLiteral;
import javascriptParser.com.digiarea.es5.PropertyAssignment;
import javascriptParser.com.digiarea.es5.OctalLiteral;
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
import javascriptParser.com.digiarea.es5.VariableExpression;
import javascriptParser.com.digiarea.es5.VariableStatement;
import javascriptParser.com.digiarea.es5.WhileStatement;
import javascriptParser.com.digiarea.es5.WithStatement;

@SuppressWarnings("unchecked")
public class CloneVisitor<C> implements GenericVisitor<Node, C> {

    @Override
    public Node visit(AllocationExpression n, C ctx) throws Exception {
        AllocationExpression img = NodeFacade.AllocationExpression();
        if (n.getBody() != null) {
            img.setBody((Expression) n.getBody().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ArrayAccessExpression n, C ctx) throws Exception {
        ArrayAccessExpression img = NodeFacade.ArrayAccessExpression();
        if (n.getName() != null) {
            img.setName((Expression) n.getName().accept(this, ctx));
        }
        if (n.getIndex() != null) {
            img.setIndex((Expression) n.getIndex().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ArrayLiteral n, C ctx) throws Exception {
        ArrayLiteral img = NodeFacade.ArrayLiteral();
        if (n.getExpressions() != null) {
            img.setExpressions((NodeList<Expression>) n.getExpressions().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(AssignmentExpression n, C ctx) throws Exception {
        AssignmentExpression img = NodeFacade.AssignmentExpression();
        if (n.getTarget() != null) {
            img.setTarget((Expression) n.getTarget().accept(this, ctx));
        }
        if (n.getValue() != null) {
            img.setValue((Expression) n.getValue().accept(this, ctx));
        }
        img.setAssignOperator(n.getAssignOperator());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(AssignOperator n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(BinaryExpression n, C ctx) throws Exception {
        BinaryExpression img = NodeFacade.BinaryExpression();
        if (n.getLeft() != null) {
            img.setLeft((Expression) n.getLeft().accept(this, ctx));
        }
        if (n.getRight() != null) {
            img.setRight((Expression) n.getRight().accept(this, ctx));
        }
        img.setBinaryOperator(n.getBinaryOperator());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(BinaryOperator n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Block n, C ctx) throws Exception {
        Block img = NodeFacade.Block();
        if (n.getStatements() != null) {
            img.setStatements((NodeList<Statement>) n.getStatements().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(BlockComment n, C ctx) throws Exception {
        BlockComment img = NodeFacade.BlockComment();
        img.setContent(n.getContent());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(BooleanLiteral n, C ctx) throws Exception {
        BooleanLiteral img = NodeFacade.BooleanLiteral();
        img.setValue(n.isValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(BreakStatement n, C ctx) throws Exception {
        BreakStatement img = NodeFacade.BreakStatement();
        img.setIdentifier(n.getIdentifier());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(CallExpression n, C ctx) throws Exception {
        CallExpression img = NodeFacade.CallExpression();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getArgs() != null) {
            img.setArgs((NodeList<Expression>) n.getArgs().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(CaseBlock n, C ctx) throws Exception {
        CaseBlock img = NodeFacade.CaseBlock();
        if (n.getDefaultClause() != null) {
            img.setDefaultClause((DefaultClause) n.getDefaultClause().accept(this, ctx));
        }
        if (n.getCaseClauses() != null) {
            img.setCaseClauses((NodeList<CaseClause>) n.getCaseClauses().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(CaseClause n, C ctx) throws Exception {
        CaseClause img = NodeFacade.CaseClause();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getStatements() != null) {
            img.setStatements((NodeList<Statement>) n.getStatements().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(CatchClause n, C ctx) throws Exception {
        CatchClause img = NodeFacade.CatchClause();
        img.setString(n.getString());
        if (n.getBlock() != null) {
            img.setBlock((Block) n.getBlock().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(CompilationUnit n, C ctx) throws Exception {
        CompilationUnit img = NodeFacade.CompilationUnit();
        if (n.getElements() != null) {
            img.setElements((NodeList<Statement>) n.getElements().accept(this, ctx));
        }
        if (n.getComments() != null) {
            img.setComments((NodeList<Comment>) n.getComments().accept(this, ctx));
        }
        img.setName(n.getName());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ConditionalExpression n, C ctx) throws Exception {
        ConditionalExpression img = NodeFacade.ConditionalExpression();
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getThenExpr() != null) {
            img.setThenExpr((Expression) n.getThenExpr().accept(this, ctx));
        }
        if (n.getElseExpr() != null) {
            img.setElseExpr((Expression) n.getElseExpr().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ConstantStatement n, C ctx) throws Exception {
        ConstantStatement img = NodeFacade.ConstantStatement();
        if (n.getVariableDeclarations() != null) {
            img.setVariableDeclarations((NodeList<VariableDeclaration>) n.getVariableDeclarations().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ContinueStatement n, C ctx) throws Exception {
        ContinueStatement img = NodeFacade.ContinueStatement();
        img.setIdentifier(n.getIdentifier());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(DebuggerStatement n, C ctx) throws Exception {
        DebuggerStatement img = NodeFacade.DebuggerStatement();
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(DecimalLiteral n, C ctx) throws Exception {
        DecimalLiteral img = NodeFacade.DecimalLiteral();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(DefaultClause n, C ctx) throws Exception {
        DefaultClause img = NodeFacade.DefaultClause();
        if (n.getStatements() != null) {
            img.setStatements((NodeList<Statement>) n.getStatements().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(DoWhileStatement n, C ctx) throws Exception {
        DoWhileStatement img = NodeFacade.DoWhileStatement();
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(EmptyLiteral n, C ctx) throws Exception {
        EmptyLiteral img = NodeFacade.EmptyLiteral();
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(EmptyStatement n, C ctx) throws Exception {
        EmptyStatement img = NodeFacade.EmptyStatement();
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(EnclosedExpression n, C ctx) throws Exception {
        EnclosedExpression img = NodeFacade.EnclosedExpression();
        if (n.getInner() != null) {
            img.setInner((Expression) n.getInner().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ExpressionStatement n, C ctx) throws Exception {
        ExpressionStatement img = NodeFacade.ExpressionStatement();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(FieldAccessExpression n, C ctx) throws Exception {
        FieldAccessExpression img = NodeFacade.FieldAccessExpression();
        if (n.getScope() != null) {
            img.setScope((Expression) n.getScope().accept(this, ctx));
        }
        if (n.getField() != null) {
            img.setField((IdentifierName) n.getField().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(FloatLiteral n, C ctx) throws Exception {
        FloatLiteral img = NodeFacade.FloatLiteral();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ForeachStatement n, C ctx) throws Exception {
        ForeachStatement img = NodeFacade.ForeachStatement();
        if (n.getVariable() != null) {
            img.setVariable((Expression) n.getVariable().accept(this, ctx));
        }
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ForStatement n, C ctx) throws Exception {
        ForStatement img = NodeFacade.ForStatement();
        if (n.getVariable() != null) {
            img.setVariable((Expression) n.getVariable().accept(this, ctx));
        }
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getExpr() != null) {
            img.setExpr((Expression) n.getExpr().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(FunctionDeclaration n, C ctx) throws Exception {
        FunctionDeclaration img = NodeFacade.FunctionDeclaration();
        img.setName(n.getName());
        if (n.getParameters() != null) {
            img.setParameters((NodeList<Parameter>) n.getParameters().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Block) n.getBody().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(FunctionExpression n, C ctx) throws Exception {
        FunctionExpression img = NodeFacade.FunctionExpression();
        img.setName(n.getName());
        if (n.getParameters() != null) {
            img.setParameters((NodeList<Parameter>) n.getParameters().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Block) n.getBody().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(GetAssignment n, C ctx) throws Exception {
        GetAssignment img = NodeFacade.GetAssignment();
        if (n.getBlock() != null) {
            img.setBlock((Block) n.getBlock().accept(this, ctx));
        }
        if (n.getPropertyName() != null) {
            img.setPropertyName((PropertyName) n.getPropertyName().accept(this, ctx));
        }
        if (n.getComment() != null) {
            img.setComment((Comment) n.getComment().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(HexIntegerLiteral n, C ctx) throws Exception {
        HexIntegerLiteral img = NodeFacade.HexIntegerLiteral();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(IdentifierName n, C ctx) throws Exception {
        IdentifierName img = NodeFacade.IdentifierName();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(IfStatement n, C ctx) throws Exception {
        IfStatement img = NodeFacade.IfStatement();
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getThenStatement() != null) {
            img.setThenStatement((Statement) n.getThenStatement().accept(this, ctx));
        }
        if (n.getElseStatement() != null) {
            img.setElseStatement((Statement) n.getElseStatement().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ImportStatement n, C ctx) throws Exception {
        ImportStatement img = NodeFacade.ImportStatement();
        if (n.getName() != null) {
            img.setName((IdentifierName) n.getName().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(JSDocComment n, C ctx) throws Exception {
        JSDocComment img = NodeFacade.JSDocComment();
        img.setContent(n.getContent());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(LabelledStatement n, C ctx) throws Exception {
        LabelledStatement img = NodeFacade.LabelledStatement();
        img.setLabel(n.getLabel());
        if (n.getStatement() != null) {
            img.setStatement((Statement) n.getStatement().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(LetDefinition n, C ctx) throws Exception {
        LetDefinition img = NodeFacade.LetDefinition();
        if (n.getVariableDeclarations() != null) {
            img.setVariableDeclarations((NodeList<VariableDeclaration>) n.getVariableDeclarations().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(LetExpression n, C ctx) throws Exception {
        LetExpression img = NodeFacade.LetExpression();
        if (n.getVariableDeclarations() != null) {
            img.setVariableDeclarations((NodeList<VariableDeclaration>) n.getVariableDeclarations().accept(this, ctx));
        }
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(LetStatement n, C ctx) throws Exception {
        LetStatement img = NodeFacade.LetStatement();
        if (n.getVariableDeclarations() != null) {
            img.setVariableDeclarations((NodeList<VariableDeclaration>) n.getVariableDeclarations().accept(this, ctx));
        }
        if (n.getStatement() != null) {
            img.setStatement((Statement) n.getStatement().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(LineComment n, C ctx) throws Exception {
        LineComment img = NodeFacade.LineComment();
        img.setContent(n.getContent());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(NewExpression n, C ctx) throws Exception {
        NewExpression img = NodeFacade.NewExpression();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getInitializer() != null) {
            img.setInitializer((ObjectLiteral) n.getInitializer().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public <E extends Node> Node visit(NodeList<E> n, C ctx) throws Exception {
        NodeList<E> img = NodeFacade.NodeList();
        if (n.getNodes() != null) {
            List<E> nodes = new ArrayList<E>();
            for (E item : n.getNodes()) {
                if (item != null) {
                    nodes.add((E) item.accept(this, ctx));
                }
            }
            img.setNodes(nodes);
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(NullLiteral n, C ctx) throws Exception {
        NullLiteral img = NodeFacade.NullLiteral();
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ObjectLiteral n, C ctx) throws Exception {
        ObjectLiteral img = NodeFacade.ObjectLiteral();
        if (n.getPropertyAssignments() != null) {
            img.setPropertyAssignments((NodeList<PropertyAssignment>) n.getPropertyAssignments().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(OctalLiteral n, C ctx) throws Exception {
        OctalLiteral img = NodeFacade.OctalLiteral();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(Parameter n, C ctx) throws Exception {
        Parameter img = NodeFacade.Parameter();
        img.setName(n.getName());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(Project n, C ctx) throws Exception {
        Project img = NodeFacade.Project();
        if (n.getCompilationUnits() != null) {
            img.setCompilationUnits((NodeList<CompilationUnit>) n.getCompilationUnits().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(PutAssignment n, C ctx) throws Exception {
        PutAssignment img = NodeFacade.PutAssignment();
        if (n.getPropertyValue() != null) {
            img.setPropertyValue((Expression) n.getPropertyValue().accept(this, ctx));
        }
        if (n.getPropertyName() != null) {
            img.setPropertyName((PropertyName) n.getPropertyName().accept(this, ctx));
        }
        if (n.getComment() != null) {
            img.setComment((Comment) n.getComment().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(RegexpLiteral n, C ctx) throws Exception {
        RegexpLiteral img = NodeFacade.RegexpLiteral();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ReturnStatement n, C ctx) throws Exception {
        ReturnStatement img = NodeFacade.ReturnStatement();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(SequenceExpression n, C ctx) throws Exception {
        SequenceExpression img = NodeFacade.SequenceExpression();
        if (n.getExpressions() != null) {
            img.setExpressions((NodeList<Expression>) n.getExpressions().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(SetAssignment n, C ctx) throws Exception {
        SetAssignment img = NodeFacade.SetAssignment();
        img.setIdentifier(n.getIdentifier());
        if (n.getBlock() != null) {
            img.setBlock((Block) n.getBlock().accept(this, ctx));
        }
        if (n.getPropertyName() != null) {
            img.setPropertyName((PropertyName) n.getPropertyName().accept(this, ctx));
        }
        if (n.getComment() != null) {
            img.setComment((Comment) n.getComment().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(StringLiteralDouble n, C ctx) throws Exception {
        StringLiteralDouble img = NodeFacade.StringLiteralDouble();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(StringLiteralSingle n, C ctx) throws Exception {
        StringLiteralSingle img = NodeFacade.StringLiteralSingle();
        img.setValue(n.getValue());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(SuperExpression n, C ctx) throws Exception {
        SuperExpression img = NodeFacade.SuperExpression();
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(SwitchStatement n, C ctx) throws Exception {
        SwitchStatement img = NodeFacade.SwitchStatement();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getBlock() != null) {
            img.setBlock((CaseBlock) n.getBlock().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ThisExpression n, C ctx) throws Exception {
        ThisExpression img = NodeFacade.ThisExpression();
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(ThrowStatement n, C ctx) throws Exception {
        ThrowStatement img = NodeFacade.ThrowStatement();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(TryStatement n, C ctx) throws Exception {
        TryStatement img = NodeFacade.TryStatement();
        if (n.getTryBlock() != null) {
            img.setTryBlock((Block) n.getTryBlock().accept(this, ctx));
        }
        if (n.getCatchClause() != null) {
            img.setCatchClause((CatchClause) n.getCatchClause().accept(this, ctx));
        }
        if (n.getFinallyBlock() != null) {
            img.setFinallyBlock((Block) n.getFinallyBlock().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(UnaryExpression n, C ctx) throws Exception {
        UnaryExpression img = NodeFacade.UnaryExpression();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        img.setUnaryOperator(n.getUnaryOperator());
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(UnaryOperator n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(VariableDeclaration n, C ctx) throws Exception {
        VariableDeclaration img = NodeFacade.VariableDeclaration();
        img.setName(n.getName());
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(VariableExpression n, C ctx) throws Exception {
        VariableExpression img = NodeFacade.VariableExpression();
        if (n.getVariableDeclarations() != null) {
            img.setVariableDeclarations((NodeList<VariableDeclaration>) n.getVariableDeclarations().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(VariableStatement n, C ctx) throws Exception {
        VariableStatement img = NodeFacade.VariableStatement();
        if (n.getVariableDeclarations() != null) {
            img.setVariableDeclarations((NodeList<VariableDeclaration>) n.getVariableDeclarations().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(WhileStatement n, C ctx) throws Exception {
        WhileStatement img = NodeFacade.WhileStatement();
        if (n.getCondition() != null) {
            img.setCondition((Expression) n.getCondition().accept(this, ctx));
        }
        if (n.getBody() != null) {
            img.setBody((Statement) n.getBody().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    @Override
    public Node visit(WithStatement n, C ctx) throws Exception {
        WithStatement img = NodeFacade.WithStatement();
        if (n.getExpression() != null) {
            img.setExpression((Expression) n.getExpression().accept(this, ctx));
        }
        if (n.getStatement() != null) {
            img.setStatement((Statement) n.getStatement().accept(this, ctx));
        }
        if (n.getJsDocComment() != null) {
            img.setJsDocComment((JSDocComment) n.getJsDocComment().accept(this, ctx));
        }
        img.setPosBegin(n.getPosBegin());
        img.setPosEnd(n.getPosEnd());
        return img;
    }

    public CloneVisitor() {
        super();
    }

}
