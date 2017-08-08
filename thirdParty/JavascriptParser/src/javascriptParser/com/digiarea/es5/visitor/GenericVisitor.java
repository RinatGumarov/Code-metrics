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

public interface GenericVisitor<R, C> {

    public R visit(AllocationExpression n, C ctx) throws Exception;

    public R visit(ArrayAccessExpression n, C ctx) throws Exception;

    public R visit(ArrayLiteral n, C ctx) throws Exception;

    public R visit(AssignmentExpression n, C ctx) throws Exception;

    public R visit(AssignOperator n, C ctx) throws Exception;

    public R visit(BinaryExpression n, C ctx) throws Exception;

    public R visit(BinaryOperator n, C ctx) throws Exception;

    public R visit(Block n, C ctx) throws Exception;

    public R visit(BlockComment n, C ctx) throws Exception;

    public R visit(BooleanLiteral n, C ctx) throws Exception;

    public R visit(BreakStatement n, C ctx) throws Exception;

    public R visit(CallExpression n, C ctx) throws Exception;

    public R visit(CaseBlock n, C ctx) throws Exception;

    public R visit(CaseClause n, C ctx) throws Exception;

    public R visit(CatchClause n, C ctx) throws Exception;

    public R visit(CompilationUnit n, C ctx) throws Exception;

    public R visit(ConditionalExpression n, C ctx) throws Exception;

    public R visit(ConstantStatement n, C ctx) throws Exception;

    public R visit(ContinueStatement n, C ctx) throws Exception;

    public R visit(DebuggerStatement n, C ctx) throws Exception;

    public R visit(DecimalLiteral n, C ctx) throws Exception;

    public R visit(DefaultClause n, C ctx) throws Exception;

    public R visit(DoWhileStatement n, C ctx) throws Exception;

    public R visit(EmptyLiteral n, C ctx) throws Exception;

    public R visit(EmptyStatement n, C ctx) throws Exception;

    public R visit(EnclosedExpression n, C ctx) throws Exception;

    public R visit(ExpressionStatement n, C ctx) throws Exception;

    public R visit(FieldAccessExpression n, C ctx) throws Exception;

    public R visit(FloatLiteral n, C ctx) throws Exception;

    public R visit(ForeachStatement n, C ctx) throws Exception;

    public R visit(ForStatement n, C ctx) throws Exception;

    public R visit(FunctionDeclaration n, C ctx) throws Exception;

    public R visit(FunctionExpression n, C ctx) throws Exception;

    public R visit(GetAssignment n, C ctx) throws Exception;

    public R visit(HexIntegerLiteral n, C ctx) throws Exception;

    public R visit(IdentifierName n, C ctx) throws Exception;

    public R visit(IfStatement n, C ctx) throws Exception;

    public R visit(ImportStatement n, C ctx) throws Exception;

    public R visit(JSDocComment n, C ctx) throws Exception;

    public R visit(LabelledStatement n, C ctx) throws Exception;

    public R visit(LetDefinition n, C ctx) throws Exception;

    public R visit(LetExpression n, C ctx) throws Exception;

    public R visit(LetStatement n, C ctx) throws Exception;

    public R visit(LineComment n, C ctx) throws Exception;

    public R visit(NewExpression n, C ctx) throws Exception;

    public <E extends Node> R visit(NodeList<E> n, C ctx) throws Exception;

    public R visit(NullLiteral n, C ctx) throws Exception;

    public R visit(ObjectLiteral n, C ctx) throws Exception;

    public R visit(OctalLiteral n, C ctx) throws Exception;

    public R visit(Parameter n, C ctx) throws Exception;

    public R visit(Project n, C ctx) throws Exception;

    public R visit(PutAssignment n, C ctx) throws Exception;

    public R visit(RegexpLiteral n, C ctx) throws Exception;

    public R visit(ReturnStatement n, C ctx) throws Exception;

    public R visit(SequenceExpression n, C ctx) throws Exception;

    public R visit(SetAssignment n, C ctx) throws Exception;

    public R visit(StringLiteralDouble n, C ctx) throws Exception;

    public R visit(StringLiteralSingle n, C ctx) throws Exception;

    public R visit(SuperExpression n, C ctx) throws Exception;

    public R visit(SwitchStatement n, C ctx) throws Exception;

    public R visit(ThisExpression n, C ctx) throws Exception;

    public R visit(ThrowStatement n, C ctx) throws Exception;

    public R visit(TryStatement n, C ctx) throws Exception;

    public R visit(UnaryExpression n, C ctx) throws Exception;

    public R visit(UnaryOperator n, C ctx) throws Exception;

    public R visit(VariableDeclaration n, C ctx) throws Exception;

    public R visit(VariableExpression n, C ctx) throws Exception;

    public R visit(VariableStatement n, C ctx) throws Exception;

    public R visit(WhileStatement n, C ctx) throws Exception;

    public R visit(WithStatement n, C ctx) throws Exception;

}
