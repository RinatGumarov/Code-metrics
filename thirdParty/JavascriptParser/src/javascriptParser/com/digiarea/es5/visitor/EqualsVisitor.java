package javascriptParser.com.digiarea.es5.visitor;

import javascriptParser.com.digiarea.es5.Node;

import java.util.List;
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

@SuppressWarnings("unchecked")
public class EqualsVisitor implements GenericVisitor<Boolean, Node> {

    private static final EqualsVisitor SINGLETON = new EqualsVisitor();

    public static boolean equals(Node n1, Node n2) throws Exception {
        return SINGLETON.nodeEquals(n1, n2);
    }

    protected <T extends Node> boolean nodeEquals(T n1, T n2) throws Exception {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        if (n1.getClass() != n2.getClass()) {
            return false;
        }
        return n1.accept(this, n2).booleanValue();
    }

    protected <T extends Node> boolean nodesEquals(List<T> nodes1, List<T> nodes2) throws Exception {
        if (nodes1 == null) {
            if (nodes2 == null) {
                return true;
            }
            return false;
        } else if (nodes2 == null) {
            return false;
        }
        if (nodes1.size() != nodes2.size()) {
            return false;
        }
        for (int i = 0; i < nodes1.size(); i++) {
            if (!nodeEquals(nodes1.get(i), nodes2.get(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean objEquals(Object n1, Object n2) {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        return n1.equals(n2);
    }

    @Override
    public Boolean visit(AllocationExpression n, Node ctx) throws Exception {
        AllocationExpression x = (AllocationExpression) ctx;
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ArrayAccessExpression n, Node ctx) throws Exception {
        ArrayAccessExpression x = (ArrayAccessExpression) ctx;
        if (!nodeEquals(n.getName(), x.getName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getIndex(), x.getIndex())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ArrayLiteral n, Node ctx) throws Exception {
        ArrayLiteral x = (ArrayLiteral) ctx;
        if (!nodeEquals(n.getExpressions(), x.getExpressions())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(AssignmentExpression n, Node ctx) throws Exception {
        AssignmentExpression x = (AssignmentExpression) ctx;
        if (!nodeEquals(n.getTarget(), x.getTarget())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        if (n.getAssignOperator() != x.getAssignOperator()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(AssignOperator n, Node ctx) throws Exception {
        return Boolean.FALSE;
    }

    @Override
    public Boolean visit(BinaryExpression n, Node ctx) throws Exception {
        BinaryExpression x = (BinaryExpression) ctx;
        if (!nodeEquals(n.getLeft(), x.getLeft())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getRight(), x.getRight())) {
            return Boolean.FALSE;
        }
        if (n.getBinaryOperator() != x.getBinaryOperator()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(BinaryOperator n, Node ctx) throws Exception {
        return Boolean.FALSE;
    }

    @Override
    public Boolean visit(Block n, Node ctx) throws Exception {
        Block x = (Block) ctx;
        if (!nodeEquals(n.getStatements(), x.getStatements())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(BlockComment n, Node ctx) throws Exception {
        BlockComment x = (BlockComment) ctx;
        if (!objEquals(n.getContent(), x.getContent())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(BooleanLiteral n, Node ctx) throws Exception {
        BooleanLiteral x = (BooleanLiteral) ctx;
        if (n.isValue() != x.isValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(BreakStatement n, Node ctx) throws Exception {
        BreakStatement x = (BreakStatement) ctx;
        if (!objEquals(n.getIdentifier(), x.getIdentifier())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(CallExpression n, Node ctx) throws Exception {
        CallExpression x = (CallExpression) ctx;
        if (!nodeEquals(n.getScope(), x.getScope())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getArgs(), x.getArgs())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(CaseBlock n, Node ctx) throws Exception {
        CaseBlock x = (CaseBlock) ctx;
        if (!nodeEquals(n.getDefaultClause(), x.getDefaultClause())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getCaseClauses(), x.getCaseClauses())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(CaseClause n, Node ctx) throws Exception {
        CaseClause x = (CaseClause) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getStatements(), x.getStatements())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(CatchClause n, Node ctx) throws Exception {
        CatchClause x = (CatchClause) ctx;
        if (!objEquals(n.getString(), x.getString())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(CompilationUnit n, Node ctx) throws Exception {
        CompilationUnit x = (CompilationUnit) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getElements(), x.getElements())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ConditionalExpression n, Node ctx) throws Exception {
        ConditionalExpression x = (ConditionalExpression) ctx;
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getThenExpr(), x.getThenExpr())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getElseExpr(), x.getElseExpr())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ConstantStatement n, Node ctx) throws Exception {
        ConstantStatement x = (ConstantStatement) ctx;
        if (!nodeEquals(n.getVariableDeclarations(), x.getVariableDeclarations())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ContinueStatement n, Node ctx) throws Exception {
        ContinueStatement x = (ContinueStatement) ctx;
        if (!objEquals(n.getIdentifier(), x.getIdentifier())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(DebuggerStatement n, Node ctx) throws Exception {
        return ctx instanceof DebuggerStatement;
    }

    @Override
    public Boolean visit(DecimalLiteral n, Node ctx) throws Exception {
        DecimalLiteral x = (DecimalLiteral) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(DefaultClause n, Node ctx) throws Exception {
        DefaultClause x = (DefaultClause) ctx;
        if (!nodeEquals(n.getStatements(), x.getStatements())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(DoWhileStatement n, Node ctx) throws Exception {
        DoWhileStatement x = (DoWhileStatement) ctx;
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(EmptyLiteral n, Node ctx) throws Exception {
        return ctx instanceof EmptyLiteral;
    }

    @Override
    public Boolean visit(EmptyStatement n, Node ctx) throws Exception {
        return ctx instanceof EmptyStatement;
    }

    @Override
    public Boolean visit(EnclosedExpression n, Node ctx) throws Exception {
        EnclosedExpression x = (EnclosedExpression) ctx;
        if (!nodeEquals(n.getInner(), x.getInner())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ExpressionStatement n, Node ctx) throws Exception {
        ExpressionStatement x = (ExpressionStatement) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(FieldAccessExpression n, Node ctx) throws Exception {
        FieldAccessExpression x = (FieldAccessExpression) ctx;
        if (!nodeEquals(n.getScope(), x.getScope())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getField(), x.getField())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(FloatLiteral n, Node ctx) throws Exception {
        FloatLiteral x = (FloatLiteral) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ForeachStatement n, Node ctx) throws Exception {
        ForeachStatement x = (ForeachStatement) ctx;
        if (!nodeEquals(n.getVariable(), x.getVariable())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ForStatement n, Node ctx) throws Exception {
        ForStatement x = (ForStatement) ctx;
        if (!nodeEquals(n.getVariable(), x.getVariable())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getExpr(), x.getExpr())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(FunctionDeclaration n, Node ctx) throws Exception {
        FunctionDeclaration x = (FunctionDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getParameters(), x.getParameters())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(FunctionExpression n, Node ctx) throws Exception {
        FunctionExpression x = (FunctionExpression) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getParameters(), x.getParameters())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(GetAssignment n, Node ctx) throws Exception {
        GetAssignment x = (GetAssignment) ctx;
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getPropertyName(), x.getPropertyName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getComment(), x.getComment())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(HexIntegerLiteral n, Node ctx) throws Exception {
        HexIntegerLiteral x = (HexIntegerLiteral) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(IdentifierName n, Node ctx) throws Exception {
        IdentifierName x = (IdentifierName) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(IfStatement n, Node ctx) throws Exception {
        IfStatement x = (IfStatement) ctx;
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getThenStatement(), x.getThenStatement())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getElseStatement(), x.getElseStatement())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ImportStatement n, Node ctx) throws Exception {
        ImportStatement x = (ImportStatement) ctx;
        if (!nodeEquals(n.getName(), x.getName())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(JSDocComment n, Node ctx) throws Exception {
        JSDocComment x = (JSDocComment) ctx;
        if (!objEquals(n.getContent(), x.getContent())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(LabelledStatement n, Node ctx) throws Exception {
        LabelledStatement x = (LabelledStatement) ctx;
        if (!objEquals(n.getLabel(), x.getLabel())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getStatement(), x.getStatement())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(LetDefinition n, Node ctx) throws Exception {
        LetDefinition x = (LetDefinition) ctx;
        if (!nodeEquals(n.getVariableDeclarations(), x.getVariableDeclarations())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(LetExpression n, Node ctx) throws Exception {
        LetExpression x = (LetExpression) ctx;
        if (!nodeEquals(n.getVariableDeclarations(), x.getVariableDeclarations())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(LetStatement n, Node ctx) throws Exception {
        LetStatement x = (LetStatement) ctx;
        if (!nodeEquals(n.getVariableDeclarations(), x.getVariableDeclarations())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getStatement(), x.getStatement())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(LineComment n, Node ctx) throws Exception {
        LineComment x = (LineComment) ctx;
        if (!objEquals(n.getContent(), x.getContent())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(NewExpression n, Node ctx) throws Exception {
        NewExpression x = (NewExpression) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getInitializer(), x.getInitializer())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public <E extends Node> Boolean visit(NodeList<E> n, Node ctx) throws Exception {
        NodeList<E> x = (NodeList<E>) ctx;
        if (!nodesEquals(n.getNodes(), x.getNodes())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(NullLiteral n, Node ctx) throws Exception {
        return ctx instanceof NullLiteral;
    }

    @Override
    public Boolean visit(ObjectLiteral n, Node ctx) throws Exception {
        ObjectLiteral x = (ObjectLiteral) ctx;
        if (!nodeEquals(n.getPropertyAssignments(), x.getPropertyAssignments())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(OctalLiteral n, Node ctx) throws Exception {
        OctalLiteral x = (OctalLiteral) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(Parameter n, Node ctx) throws Exception {
        Parameter x = (Parameter) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(Project n, Node ctx) throws Exception {
        Project x = (Project) ctx;
        if (!nodeEquals(n.getCompilationUnits(), x.getCompilationUnits())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(PutAssignment n, Node ctx) throws Exception {
        PutAssignment x = (PutAssignment) ctx;
        if (!nodeEquals(n.getPropertyValue(), x.getPropertyValue())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getPropertyName(), x.getPropertyName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getComment(), x.getComment())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(RegexpLiteral n, Node ctx) throws Exception {
        RegexpLiteral x = (RegexpLiteral) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ReturnStatement n, Node ctx) throws Exception {
        ReturnStatement x = (ReturnStatement) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(SequenceExpression n, Node ctx) throws Exception {
        SequenceExpression x = (SequenceExpression) ctx;
        if (!nodeEquals(n.getExpressions(), x.getExpressions())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(SetAssignment n, Node ctx) throws Exception {
        SetAssignment x = (SetAssignment) ctx;
        if (!objEquals(n.getIdentifier(), x.getIdentifier())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getPropertyName(), x.getPropertyName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getComment(), x.getComment())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(StringLiteralDouble n, Node ctx) throws Exception {
        StringLiteralDouble x = (StringLiteralDouble) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(StringLiteralSingle n, Node ctx) throws Exception {
        StringLiteralSingle x = (StringLiteralSingle) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(SuperExpression n, Node ctx) throws Exception {
        return ctx instanceof SuperExpression;
    }

    @Override
    public Boolean visit(SwitchStatement n, Node ctx) throws Exception {
        SwitchStatement x = (SwitchStatement) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBlock(), x.getBlock())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(ThisExpression n, Node ctx) throws Exception {
        return ctx instanceof ThisExpression;
    }

    @Override
    public Boolean visit(ThrowStatement n, Node ctx) throws Exception {
        ThrowStatement x = (ThrowStatement) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(TryStatement n, Node ctx) throws Exception {
        TryStatement x = (TryStatement) ctx;
        if (!nodeEquals(n.getTryBlock(), x.getTryBlock())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getCatchClause(), x.getCatchClause())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getFinallyBlock(), x.getFinallyBlock())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(UnaryExpression n, Node ctx) throws Exception {
        UnaryExpression x = (UnaryExpression) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        if (n.getUnaryOperator() != x.getUnaryOperator()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(UnaryOperator n, Node ctx) throws Exception {
        return Boolean.FALSE;
    }

    @Override
    public Boolean visit(VariableDeclaration n, Node ctx) throws Exception {
        VariableDeclaration x = (VariableDeclaration) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(VariableExpression n, Node ctx) throws Exception {
        VariableExpression x = (VariableExpression) ctx;
        if (!nodeEquals(n.getVariableDeclarations(), x.getVariableDeclarations())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(VariableStatement n, Node ctx) throws Exception {
        VariableStatement x = (VariableStatement) ctx;
        if (!nodeEquals(n.getVariableDeclarations(), x.getVariableDeclarations())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(WhileStatement n, Node ctx) throws Exception {
        WhileStatement x = (WhileStatement) ctx;
        if (!nodeEquals(n.getCondition(), x.getCondition())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getBody(), x.getBody())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean visit(WithStatement n, Node ctx) throws Exception {
        WithStatement x = (WithStatement) ctx;
        if (!nodeEquals(n.getExpression(), x.getExpression())) {
            return Boolean.FALSE;
        }
        if (!nodeEquals(n.getStatement(), x.getStatement())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public EqualsVisitor() {
        super();
    }

}
