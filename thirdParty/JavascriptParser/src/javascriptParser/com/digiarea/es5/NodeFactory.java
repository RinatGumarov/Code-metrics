package javascriptParser.com.digiarea.es5;

import java.util.List;

class NodeFactory {

    public static AllocationExpression AllocationExpression() {
        return new AllocationExpression();
    }

    public static AllocationExpression AllocationExpression(Expression body, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new AllocationExpression(body, jsDocComment, posBegin, posEnd);
    }

    public static ArrayAccessExpression ArrayAccessExpression() {
        return new ArrayAccessExpression();
    }

    public static ArrayAccessExpression ArrayAccessExpression(Expression name, Expression index, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ArrayAccessExpression(name, index, jsDocComment, posBegin, posEnd);
    }

    public static ArrayLiteral ArrayLiteral() {
        return new ArrayLiteral();
    }

    public static ArrayLiteral ArrayLiteral(NodeList<Expression> expressions, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ArrayLiteral(expressions, jsDocComment, posBegin, posEnd);
    }

    public static AssignmentExpression AssignmentExpression() {
        return new AssignmentExpression();
    }

    public static AssignmentExpression AssignmentExpression(Expression target, Expression value, AssignmentExpression.AssignOperator assignOperator, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new AssignmentExpression(target, value, assignOperator, jsDocComment, posBegin, posEnd);
    }

    public static BinaryExpression BinaryExpression() {
        return new BinaryExpression();
    }

    public static BinaryExpression BinaryExpression(Expression left, Expression right, BinaryExpression.BinaryOperator binaryOperator, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new BinaryExpression(left, right, binaryOperator, jsDocComment, posBegin, posEnd);
    }

    public static Block Block() {
        return new Block();
    }

    public static Block Block(NodeList<Statement> statements, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new Block(statements, jsDocComment, posBegin, posEnd);
    }

    public static BlockComment BlockComment() {
        return new BlockComment();
    }

    public static BlockComment BlockComment(String content, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new BlockComment(content, jsDocComment, posBegin, posEnd);
    }

    public static BooleanLiteral BooleanLiteral() {
        return new BooleanLiteral();
    }

    public static BooleanLiteral BooleanLiteral(boolean value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new BooleanLiteral(value, jsDocComment, posBegin, posEnd);
    }

    public static BreakStatement BreakStatement() {
        return new BreakStatement();
    }

    public static BreakStatement BreakStatement(String identifier, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new BreakStatement(identifier, jsDocComment, posBegin, posEnd);
    }

    public static CallExpression CallExpression() {
        return new CallExpression();
    }

    public static CallExpression CallExpression(Expression scope, NodeList<Expression> args, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new CallExpression(scope, args, jsDocComment, posBegin, posEnd);
    }

    public static CaseBlock CaseBlock() {
        return new CaseBlock();
    }

    public static CaseBlock CaseBlock(DefaultClause defaultClause, NodeList<CaseClause> caseClauses, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new CaseBlock(defaultClause, caseClauses, jsDocComment, posBegin, posEnd);
    }

    public static CaseClause CaseClause() {
        return new CaseClause();
    }

    public static CaseClause CaseClause(Expression expression, NodeList<Statement> statements, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new CaseClause(expression, statements, jsDocComment, posBegin, posEnd);
    }

    public static CatchClause CatchClause() {
        return new CatchClause();
    }

    public static CatchClause CatchClause(String string, Block block, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new CatchClause(string, block, jsDocComment, posBegin, posEnd);
    }

    public static CompilationUnit CompilationUnit() {
        return new CompilationUnit();
    }

    public static CompilationUnit CompilationUnit(NodeList<Statement> elements, NodeList<Comment> comments, String name, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new CompilationUnit(elements, comments, name, jsDocComment, posBegin, posEnd);
    }

    public static ConditionalExpression ConditionalExpression() {
        return new ConditionalExpression();
    }

    public static ConditionalExpression ConditionalExpression(Expression condition, Expression thenExpr, Expression elseExpr, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ConditionalExpression(condition, thenExpr, elseExpr, jsDocComment, posBegin, posEnd);
    }

    public static ConstantStatement ConstantStatement() {
        return new ConstantStatement();
    }

    public static ConstantStatement ConstantStatement(NodeList<VariableDeclaration> variableDeclarations, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ConstantStatement(variableDeclarations, jsDocComment, posBegin, posEnd);
    }

    public static ContinueStatement ContinueStatement() {
        return new ContinueStatement();
    }

    public static ContinueStatement ContinueStatement(String identifier, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ContinueStatement(identifier, jsDocComment, posBegin, posEnd);
    }

    public static DebuggerStatement DebuggerStatement() {
        return new DebuggerStatement();
    }

    public static DebuggerStatement DebuggerStatement(JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new DebuggerStatement(jsDocComment, posBegin, posEnd);
    }

    public static DecimalLiteral DecimalLiteral() {
        return new DecimalLiteral();
    }

    public static DecimalLiteral DecimalLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new DecimalLiteral(value, jsDocComment, posBegin, posEnd);
    }

    public static DefaultClause DefaultClause() {
        return new DefaultClause();
    }

    public static DefaultClause DefaultClause(NodeList<Statement> statements, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new DefaultClause(statements, jsDocComment, posBegin, posEnd);
    }

    public static DoWhileStatement DoWhileStatement() {
        return new DoWhileStatement();
    }

    public static DoWhileStatement DoWhileStatement(Expression condition, Statement body, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new DoWhileStatement(condition, body, jsDocComment, posBegin, posEnd);
    }

    public static EmptyLiteral EmptyLiteral() {
        return new EmptyLiteral();
    }

    public static EmptyLiteral EmptyLiteral(JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new EmptyLiteral(jsDocComment, posBegin, posEnd);
    }

    public static EmptyStatement EmptyStatement() {
        return new EmptyStatement();
    }

    public static EmptyStatement EmptyStatement(JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new EmptyStatement(jsDocComment, posBegin, posEnd);
    }

    public static EnclosedExpression EnclosedExpression() {
        return new EnclosedExpression();
    }

    public static EnclosedExpression EnclosedExpression(Expression inner, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new EnclosedExpression(inner, jsDocComment, posBegin, posEnd);
    }

    public static ExpressionStatement ExpressionStatement() {
        return new ExpressionStatement();
    }

    public static ExpressionStatement ExpressionStatement(Expression expression, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ExpressionStatement(expression, jsDocComment, posBegin, posEnd);
    }

    public static FieldAccessExpression FieldAccessExpression() {
        return new FieldAccessExpression();
    }

    public static FieldAccessExpression FieldAccessExpression(Expression scope, IdentifierName field, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new FieldAccessExpression(scope, field, jsDocComment, posBegin, posEnd);
    }

    public static FloatLiteral FloatLiteral() {
        return new FloatLiteral();
    }

    public static FloatLiteral FloatLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new FloatLiteral(value, jsDocComment, posBegin, posEnd);
    }

    public static ForeachStatement ForeachStatement() {
        return new ForeachStatement();
    }

    public static ForeachStatement ForeachStatement(Expression variable, Expression expression, Statement body, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ForeachStatement(variable, expression, body, jsDocComment, posBegin, posEnd);
    }

    public static ForStatement ForStatement() {
        return new ForStatement();
    }

    public static ForStatement ForStatement(Expression variable, Expression condition, Expression expr, Statement body, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ForStatement(variable, condition, expr, body, jsDocComment, posBegin, posEnd);
    }

    public static FunctionDeclaration FunctionDeclaration() {
        return new FunctionDeclaration();
    }

    public static FunctionDeclaration FunctionDeclaration(String name, NodeList<Parameter> parameters, Block body, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new FunctionDeclaration(name, parameters, body, jsDocComment, posBegin, posEnd);
    }

    public static FunctionExpression FunctionExpression() {
        return new FunctionExpression();
    }

    public static FunctionExpression FunctionExpression(String name, NodeList<Parameter> parameters, Block body, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new FunctionExpression(name, parameters, body, jsDocComment, posBegin, posEnd);
    }

    public static GetAssignment GetAssignment() {
        return new GetAssignment();
    }

    public static GetAssignment GetAssignment(Block block, PropertyName propertyName, Comment comment, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new GetAssignment(block, propertyName, comment, jsDocComment, posBegin, posEnd);
    }

    public static HexIntegerLiteral HexIntegerLiteral() {
        return new HexIntegerLiteral();
    }

    public static HexIntegerLiteral HexIntegerLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new HexIntegerLiteral(value, jsDocComment, posBegin, posEnd);
    }

    public static IdentifierName IdentifierName() {
        return new IdentifierName();
    }

    public static IdentifierName IdentifierName(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new IdentifierName(value, jsDocComment, posBegin, posEnd);
    }

    public static IfStatement IfStatement() {
        return new IfStatement();
    }

    public static IfStatement IfStatement(Expression condition, Statement thenStatement, Statement elseStatement, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new IfStatement(condition, thenStatement, elseStatement, jsDocComment, posBegin, posEnd);
    }

    public static ImportStatement ImportStatement() {
        return new ImportStatement();
    }

    public static ImportStatement ImportStatement(IdentifierName name, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ImportStatement(name, jsDocComment, posBegin, posEnd);
    }

    public static JSDocComment JSDocComment() {
        return new JSDocComment();
    }

    public static JSDocComment JSDocComment(String content, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new JSDocComment(content, jsDocComment, posBegin, posEnd);
    }

    public static LabelledStatement LabelledStatement() {
        return new LabelledStatement();
    }

    public static LabelledStatement LabelledStatement(String label, Statement statement, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new LabelledStatement(label, statement, jsDocComment, posBegin, posEnd);
    }

    public static LetDefinition LetDefinition() {
        return new LetDefinition();
    }

    public static LetDefinition LetDefinition(NodeList<VariableDeclaration> variableDeclarations, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new LetDefinition(variableDeclarations, jsDocComment, posBegin, posEnd);
    }

    public static LetExpression LetExpression() {
        return new LetExpression();
    }

    public static LetExpression LetExpression(NodeList<VariableDeclaration> variableDeclarations, Expression expression, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new LetExpression(variableDeclarations, expression, jsDocComment, posBegin, posEnd);
    }

    public static LetStatement LetStatement() {
        return new LetStatement();
    }

    public static LetStatement LetStatement(NodeList<VariableDeclaration> variableDeclarations, Statement statement, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new LetStatement(variableDeclarations, statement, jsDocComment, posBegin, posEnd);
    }

    public static LineComment LineComment() {
        return new LineComment();
    }

    public static LineComment LineComment(String content, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new LineComment(content, jsDocComment, posBegin, posEnd);
    }

    public static NewExpression NewExpression() {
        return new NewExpression();
    }

    public static NewExpression NewExpression(Expression expression, ObjectLiteral initializer, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new NewExpression(expression, initializer, jsDocComment, posBegin, posEnd);
    }

    public static <E extends Node> NodeList<E> NodeList() {
        return new NodeList<E>();
    }

    public static <E extends Node> NodeList<E> NodeList(List<E> nodes, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new NodeList<E>(nodes, jsDocComment, posBegin, posEnd);
    }

    public static NullLiteral NullLiteral() {
        return new NullLiteral();
    }

    public static NullLiteral NullLiteral(JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new NullLiteral(jsDocComment, posBegin, posEnd);
    }

    public static ObjectLiteral ObjectLiteral() {
        return new ObjectLiteral();
    }

    public static ObjectLiteral ObjectLiteral(NodeList<PropertyAssignment> propertyAssignments, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ObjectLiteral(propertyAssignments, jsDocComment, posBegin, posEnd);
    }

    public static OctalLiteral OctalLiteral() {
        return new OctalLiteral();
    }

    public static OctalLiteral OctalLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new OctalLiteral(value, jsDocComment, posBegin, posEnd);
    }

    public static Parameter Parameter() {
        return new Parameter();
    }

    public static Parameter Parameter(String name, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new Parameter(name, jsDocComment, posBegin, posEnd);
    }

    public static Project Project() {
        return new Project();
    }

    public static Project Project(NodeList<CompilationUnit> compilationUnits, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new Project(compilationUnits, jsDocComment, posBegin, posEnd);
    }

    public static PutAssignment PutAssignment() {
        return new PutAssignment();
    }

    public static PutAssignment PutAssignment(Expression propertyValue, PropertyName propertyName, Comment comment, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new PutAssignment(propertyValue, propertyName, comment, jsDocComment, posBegin, posEnd);
    }

    public static RegexpLiteral RegexpLiteral() {
        return new RegexpLiteral();
    }

    public static RegexpLiteral RegexpLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new RegexpLiteral(value, jsDocComment, posBegin, posEnd);
    }

    public static ReturnStatement ReturnStatement() {
        return new ReturnStatement();
    }

    public static ReturnStatement ReturnStatement(Expression expression, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ReturnStatement(expression, jsDocComment, posBegin, posEnd);
    }

    public static SequenceExpression SequenceExpression() {
        return new SequenceExpression();
    }

    public static SequenceExpression SequenceExpression(NodeList<Expression> expressions, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new SequenceExpression(expressions, jsDocComment, posBegin, posEnd);
    }

    public static SetAssignment SetAssignment() {
        return new SetAssignment();
    }

    public static SetAssignment SetAssignment(String identifier, Block block, PropertyName propertyName, Comment comment, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new SetAssignment(identifier, block, propertyName, comment, jsDocComment, posBegin, posEnd);
    }

    public static StringLiteralDouble StringLiteralDouble() {
        return new StringLiteralDouble();
    }

    public static StringLiteralDouble StringLiteralDouble(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new StringLiteralDouble(value, jsDocComment, posBegin, posEnd);
    }

    public static StringLiteralSingle StringLiteralSingle() {
        return new StringLiteralSingle();
    }

    public static StringLiteralSingle StringLiteralSingle(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new StringLiteralSingle(value, jsDocComment, posBegin, posEnd);
    }

    public static SuperExpression SuperExpression() {
        return new SuperExpression();
    }

    public static SuperExpression SuperExpression(JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new SuperExpression(jsDocComment, posBegin, posEnd);
    }

    public static SwitchStatement SwitchStatement() {
        return new SwitchStatement();
    }

    public static SwitchStatement SwitchStatement(Expression expression, CaseBlock block, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new SwitchStatement(expression, block, jsDocComment, posBegin, posEnd);
    }

    public static ThisExpression ThisExpression() {
        return new ThisExpression();
    }

    public static ThisExpression ThisExpression(JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ThisExpression(jsDocComment, posBegin, posEnd);
    }

    public static ThrowStatement ThrowStatement() {
        return new ThrowStatement();
    }

    public static ThrowStatement ThrowStatement(Expression expression, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new ThrowStatement(expression, jsDocComment, posBegin, posEnd);
    }

    public static TryStatement TryStatement() {
        return new TryStatement();
    }

    public static TryStatement TryStatement(Block tryBlock, CatchClause catchClause, Block finallyBlock, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new TryStatement(tryBlock, catchClause, finallyBlock, jsDocComment, posBegin, posEnd);
    }

    public static UnaryExpression UnaryExpression() {
        return new UnaryExpression();
    }

    public static UnaryExpression UnaryExpression(Expression expression, UnaryExpression.UnaryOperator unaryOperator, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new UnaryExpression(expression, unaryOperator, jsDocComment, posBegin, posEnd);
    }

    public static VariableDeclaration VariableDeclaration() {
        return new VariableDeclaration();
    }

    public static VariableDeclaration VariableDeclaration(String name, Expression expression, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new VariableDeclaration(name, expression, jsDocComment, posBegin, posEnd);
    }

    public static VariableExpression VariableExpression() {
        return new VariableExpression();
    }

    public static VariableExpression VariableExpression(NodeList<VariableDeclaration> variableDeclarations, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new VariableExpression(variableDeclarations, jsDocComment, posBegin, posEnd);
    }

    public static VariableStatement VariableStatement() {
        return new VariableStatement();
    }

    public static VariableStatement VariableStatement(NodeList<VariableDeclaration> variableDeclarations, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new VariableStatement(variableDeclarations, jsDocComment, posBegin, posEnd);
    }

    public static WhileStatement WhileStatement() {
        return new WhileStatement();
    }

    public static WhileStatement WhileStatement(Expression condition, Statement body, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new WhileStatement(condition, body, jsDocComment, posBegin, posEnd);
    }

    public static WithStatement WithStatement() {
        return new WithStatement();
    }

    public static WithStatement WithStatement(Expression expression, Statement statement, JSDocComment jsDocComment, int posBegin, int posEnd) {
        return new WithStatement(expression, statement, jsDocComment, posBegin, posEnd);
    }

}
