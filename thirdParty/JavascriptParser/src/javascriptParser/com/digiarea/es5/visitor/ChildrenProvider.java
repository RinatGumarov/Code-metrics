package javascriptParser.com.digiarea.es5.visitor;

import java.util.List;

import javascriptParser.com.digiarea.es5.AllocationExpression;
import java.util.ArrayList;
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

public class ChildrenProvider<C> implements GenericVisitor<List<Object>, Void> {

    @Override
    public List<Object> visit(AllocationExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ArrayAccessExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getIndex() != null) {
            img.add(n.getIndex());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ArrayLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpressions() != null) {
            img.add(n.getExpressions());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(AssignmentExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTarget() != null) {
            img.add(n.getTarget());
        }
        if (n.getValue() != null) {
            img.add(n.getValue());
        }
        img.add(n.getAssignOperator());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(AssignOperator n, Void ctx) throws Exception {
        return null;
    }

    @Override
    public List<Object> visit(BinaryExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getLeft() != null) {
            img.add(n.getLeft());
        }
        if (n.getRight() != null) {
            img.add(n.getRight());
        }
        img.add(n.getBinaryOperator());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(BinaryOperator n, Void ctx) throws Exception {
        return null;
    }

    @Override
    public List<Object> visit(Block n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getStatements() != null) {
            img.add(n.getStatements());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(BlockComment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getContent());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(BooleanLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.isValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(BreakStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getIdentifier());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(CallExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getScope() != null) {
            img.add(n.getScope());
        }
        if (n.getArgs() != null) {
            img.add(n.getArgs());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(CaseBlock n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getDefaultClause() != null) {
            img.add(n.getDefaultClause());
        }
        if (n.getCaseClauses() != null) {
            img.add(n.getCaseClauses());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(CaseClause n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getStatements() != null) {
            img.add(n.getStatements());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(CatchClause n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getString());
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(CompilationUnit n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getElements() != null) {
            img.add(n.getElements());
        }
        if (n.getComments() != null) {
            img.add(n.getComments());
        }
        img.add(n.getName());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ConditionalExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getThenExpr() != null) {
            img.add(n.getThenExpr());
        }
        if (n.getElseExpr() != null) {
            img.add(n.getElseExpr());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ConstantStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariableDeclarations() != null) {
            img.add(n.getVariableDeclarations());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ContinueStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getIdentifier());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(DebuggerStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(DecimalLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(DefaultClause n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getStatements() != null) {
            img.add(n.getStatements());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(DoWhileStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(EmptyLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(EmptyStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(EnclosedExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getInner() != null) {
            img.add(n.getInner());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ExpressionStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(FieldAccessExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getScope() != null) {
            img.add(n.getScope());
        }
        if (n.getField() != null) {
            img.add(n.getField());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(FloatLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ForeachStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariable() != null) {
            img.add(n.getVariable());
        }
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ForStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariable() != null) {
            img.add(n.getVariable());
        }
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getExpr() != null) {
            img.add(n.getExpr());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(FunctionDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getParameters() != null) {
            img.add(n.getParameters());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(FunctionExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getParameters() != null) {
            img.add(n.getParameters());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(GetAssignment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getPropertyName() != null) {
            img.add(n.getPropertyName());
        }
        if (n.getComment() != null) {
            img.add(n.getComment());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(HexIntegerLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(IdentifierName n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(IfStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getThenStatement() != null) {
            img.add(n.getThenStatement());
        }
        if (n.getElseStatement() != null) {
            img.add(n.getElseStatement());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ImportStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getName() != null) {
            img.add(n.getName());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(JSDocComment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getContent());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(LabelledStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getLabel());
        if (n.getStatement() != null) {
            img.add(n.getStatement());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(LetDefinition n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariableDeclarations() != null) {
            img.add(n.getVariableDeclarations());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(LetExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariableDeclarations() != null) {
            img.add(n.getVariableDeclarations());
        }
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(LetStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariableDeclarations() != null) {
            img.add(n.getVariableDeclarations());
        }
        if (n.getStatement() != null) {
            img.add(n.getStatement());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(LineComment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getContent());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(NewExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getInitializer() != null) {
            img.add(n.getInitializer());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public <E extends Node> List<Object> visit(NodeList<E> n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getNodes() != null) {
            img.add(n.getNodes());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(NullLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ObjectLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getPropertyAssignments() != null) {
            img.add(n.getPropertyAssignments());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(OctalLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(Parameter n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(Project n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCompilationUnits() != null) {
            img.add(n.getCompilationUnits());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(PutAssignment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getPropertyValue() != null) {
            img.add(n.getPropertyValue());
        }
        if (n.getPropertyName() != null) {
            img.add(n.getPropertyName());
        }
        if (n.getComment() != null) {
            img.add(n.getComment());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(RegexpLiteral n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ReturnStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(SequenceExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpressions() != null) {
            img.add(n.getExpressions());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(SetAssignment n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getIdentifier());
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getPropertyName() != null) {
            img.add(n.getPropertyName());
        }
        if (n.getComment() != null) {
            img.add(n.getComment());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(StringLiteralDouble n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(StringLiteralSingle n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getValue());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(SuperExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(SwitchStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getBlock() != null) {
            img.add(n.getBlock());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ThisExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(ThrowStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(TryStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getTryBlock() != null) {
            img.add(n.getTryBlock());
        }
        if (n.getCatchClause() != null) {
            img.add(n.getCatchClause());
        }
        if (n.getFinallyBlock() != null) {
            img.add(n.getFinallyBlock());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(UnaryExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        img.add(n.getUnaryOperator());
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(UnaryOperator n, Void ctx) throws Exception {
        return null;
    }

    @Override
    public List<Object> visit(VariableDeclaration n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        img.add(n.getName());
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(VariableExpression n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariableDeclarations() != null) {
            img.add(n.getVariableDeclarations());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(VariableStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getVariableDeclarations() != null) {
            img.add(n.getVariableDeclarations());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(WhileStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getCondition() != null) {
            img.add(n.getCondition());
        }
        if (n.getBody() != null) {
            img.add(n.getBody());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    @Override
    public List<Object> visit(WithStatement n, Void ctx) throws Exception {
        List<Object> img = new ArrayList<Object>();
        if (n.getExpression() != null) {
            img.add(n.getExpression());
        }
        if (n.getStatement() != null) {
            img.add(n.getStatement());
        }
        if (n.getJsDocComment() != null) {
            img.add(n.getJsDocComment());
        }
        img.add(n.getPosBegin());
        img.add(n.getPosEnd());
        return img;
    }

    public ChildrenProvider() {
        super();
    }

}
