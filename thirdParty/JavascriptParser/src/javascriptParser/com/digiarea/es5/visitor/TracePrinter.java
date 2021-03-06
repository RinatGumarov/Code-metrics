/*******************************************************************************
 * Copyright (c) 2011 - 2014 DigiArea, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     DigiArea, Inc. - initial API and implementation
 *******************************************************************************/
package javascriptParser.com.digiarea.es5.visitor;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javascriptParser.com.digiarea.common.utils.SourcePrinter;
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
import javascriptParser.com.digiarea.es5.Expression;
import javascriptParser.com.digiarea.es5.ExpressionStatement;
import javascriptParser.com.digiarea.es5.FieldAccessExpression;
import javascriptParser.com.digiarea.es5.FloatLiteral;
import javascriptParser.com.digiarea.es5.ForStatement;
import javascriptParser.com.digiarea.es5.ForeachStatement;
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
import javascriptParser.com.digiarea.es5.PropertyAssignment;
import javascriptParser.com.digiarea.es5.PutAssignment;
import javascriptParser.com.digiarea.es5.RegexpLiteral;
import javascriptParser.com.digiarea.es5.ReturnStatement;
import javascriptParser.com.digiarea.es5.SequenceExpression;
import javascriptParser.com.digiarea.es5.SetAssignment;
import javascriptParser.com.digiarea.es5.Statement;
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

/**
 * The Class TracePrinter.
 */
public class TracePrinter implements VoidVisitor<SourcePrinter> {

	private void printInlineJSDoc(JSDocComment jsDocComment,
			SourcePrinter printer) throws Exception {
		if (jsDocComment != null) {
			String content = jsDocComment.getContent();
			if (content != null) {
				content = content.replaceAll("\t", " ");
				content = content.replaceAll("\n", " ");
				content = content.replaceAll("\r", " ");
				printer.print("/**");
				printer.print(content);
				printer.print("*/");
				printer.print(" ");
			}

		}
	}

	/**
	 * Prints the expressions.
	 * 
	 * @param expressions
	 *            the expressions
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void printExpressions(List<Expression> expressions,
			SourcePrinter printer) throws Exception, IOException {
		for (Iterator<Expression> i = expressions.iterator(); i.hasNext();) {
			Expression expression = i.next();
			if (expression != null) {
				expression.accept(this, printer);
			}
			if (i.hasNext()) {
				printer.print(", ");
			}
		}
	}

	/**
	 * Prints the statements.
	 * 
	 * @param statements
	 *            the statements
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void printStatements(List<Statement> statements,
			SourcePrinter printer) throws Exception, IOException {
		for (Iterator<Statement> i = statements.iterator(); i.hasNext();) {
			Statement statement = i.next();
			statement.accept(this, printer);
			if (i.hasNext()) {

			}
		}
	}

	/**
	 * Prints the variable declarations.
	 * 
	 * @param decls
	 *            the decls
	 * @param printer
	 *            the printer
	 * @throws Exception
	 *             the exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void printVariableDeclarations(List<VariableDeclaration> decls,
			SourcePrinter printer) throws Exception, IOException {
		for (Iterator<VariableDeclaration> i = decls.iterator(); i.hasNext();) {
			VariableDeclaration expression = i.next();
			expression.accept(this, printer);
			if (i.hasNext()) {
				printer.print(", ");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * AllocationExpression, java.lang.Object)
	 */
	@Override
	public void visit(AllocationExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("new ");
		if (n.getBody() != null) {
			n.getBody().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * ArrayAccessExpression, java.lang.Object)
	 */
	@Override
	public void visit(ArrayAccessExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getName() != null) {
			n.getName().accept(this, printer);
		}
		printer.print("[");
		if (n.getIndex() != null) {
			n.getIndex().accept(this, printer);
		}
		printer.print("]");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ArrayLiteral,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ArrayLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("[");
		List<Expression> expressions = n.getExpressions();
		if (expressions != null) {
			printExpressions(expressions, printer);
		}
		printer.print("]");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * AssignmentExpression, java.lang.Object)
	 */
	@Override
	public void visit(AssignmentExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getTarget() != null) {
			n.getTarget().accept(this, printer);
		}
		if (n.getAssignOperator() != null) {
			n.getAssignOperator().accept(this, printer);
		}
		if (n.getValue() != null) {
			n.getValue().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * AssignmentExpression.AssignOperator, java.lang.Object)
	 */
	@Override
	public void visit(AssignOperator n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		switch (n) {
		case and:
			printer.print("&=");
			break;
		case lShift:
			printer.print("<<=");
			break;
		case assign:
			printer.print("=");
			break;
		case minus:
			printer.print("-=");
			break;
		case or:
			printer.print("|=");
			break;
		case plus:
			printer.print("+=");
			break;
		case rem:
			printer.print("%=");
			break;
		case rSignedShift:
			printer.print(">>=");
			break;
		case rUnsignedShift:
			printer.print(">>>=");
			break;
		case slash:
			printer.print("/=");
			break;
		case star:
			printer.print("*=");
			break;
		case xor:
			printer.print("^=");
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.BinaryExpression
	 * , java.lang.Object)
	 */
	@Override
	public void visit(BinaryExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getLeft() != null) {
			n.getLeft().accept(this, printer);
		}
		if (n.getBinaryOperator() != null) {
			n.getBinaryOperator().accept(this, printer);
		}
		if (n.getRight() != null) {
			n.getRight().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.BinaryExpression
	 * .BinaryOperator, java.lang.Object)
	 */
	@Override
	public void visit(BinaryOperator n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		switch (n) {
		case and:
			printer.print("&&");
			break;
		case binAnd:
			printer.print("&");
			break;
		case binOr:
			printer.print("|");
			break;
		case divide:
			printer.print("/");
			break;
		case equals:
			printer.print("==");
			break;
		case greater:
			printer.print(">");
			break;
		case greaterEquals:
			printer.print(">=");
			break;
		case identity:
			printer.print("===");
			break;
		case in:
			printer.print("in");
			break;
		case lShift:
			printer.print("<<");
			break;
		case less:
			printer.print("<");
			break;
		case lessEquals:
			printer.print("<=");
			break;
		case minus:
			printer.print("-");
			break;
		case notEquals:
			printer.print("!=");
			break;
		case notIdentity:
			printer.print("!==");
			break;
		case opInstanceof:
			printer.print("instanceof");
			break;
		case or:
			printer.print("||");
			break;
		case plus:
			printer.print("+");
			break;
		case rSignedShift:
			printer.print(">>");
			break;
		case rUnsignedShift:
			printer.print(">>>");
			break;
		case remainder:
			printer.print("%");
			break;
		case times:
			printer.print("*");
			break;
		case xor:
			printer.print("^");
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.Block,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Block n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("{");
		if (n.getStatements() != null) {
			for (Statement item : n.getStatements()) {
				if (item != null) {
					item.accept(this, printer);
				}
			}
		}
		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.BooleanLiteral
	 * , java.lang.Object)
	 */
	@Override
	public void visit(BooleanLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.isValue() ? "true" : "false");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.BreakStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(BreakStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("break");
		if (n.getIdentifier() != null) {
			printer.print(" ");
			printer.print(n.getIdentifier());
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.CallExpression
	 * , java.lang.Object)
	 */
	@Override
	public void visit(CallExpression n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getScope() != null) {
			n.getScope().accept(this, printer);
		}
		printer.print("(");
		if (n.getArgs() != null) {
			printExpressions(n.getArgs(), printer);
		}
		printer.print(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.CaseBlock,
	 * java.lang.Object)
	 */
	@Override
	public void visit(CaseBlock n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(" {");
		if (n.getCaseClauses() != null) {

			for (CaseClause item : n.getCaseClauses()) {
				if (item != null) {
					item.accept(this, printer);
				}
			}

		}
		if (n.getDefaultClause() != null) {
			n.getDefaultClause().accept(this, printer);
		}

		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.CaseClause,
	 * java.lang.Object)
	 */
	@Override
	public void visit(CaseClause n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);

		if (n.getExpression() != null) {
			printer.print("case ");
			n.getExpression().accept(this, printer);
			printer.print(":");
		}
		List<Statement> statements = n.getStatements();
		if (statements != null) {

			printStatements(statements, printer);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.CatchClause,
	 * java.lang.Object)
	 */
	@Override
	public void visit(CatchClause n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(" catch ( ");
		printer.print(n.getString());
		printer.print(" ) ");
		if (n.getBlock() != null) {
			n.getBlock().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.CompilationUnit
	 * , java.lang.Object)
	 */
	@Override
	public void visit(CompilationUnit n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getElements() != null) {
			for (Statement item : n.getElements()) {
				if (item != null) {
					item.accept(this, printer);

				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * ConditionalExpression, java.lang.Object)
	 */
	@Override
	public void visit(ConditionalExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getCondition() != null) {
			n.getCondition().accept(this, printer);
		}
		printer.print(" ? ");
		if (n.getThenExpr() != null) {
			n.getThenExpr().accept(this, printer);
		}
		printer.print(" : ");
		if (n.getElseExpr() != null) {
			n.getElseExpr().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ConstantStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ConstantStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("const ");
		List<VariableDeclaration> decls = n.getVariableDeclarations();
		if (decls != null) {
			printVariableDeclarations(decls, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ContinueStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ContinueStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("continue");
		if (n.getIdentifier() != null) {
			printer.print(" ");
			printer.print(n.getIdentifier());
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.DebuggerStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(DebuggerStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.printLn("debugger;");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.DecimalLiteral
	 * , java.lang.Object)
	 */
	@Override
	public void visit(DecimalLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.DefaultClause
	 * , java.lang.Object)
	 */
	@Override
	public void visit(DefaultClause n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);

		printer.print("default:");
		List<Statement> statements = n.getStatements();
		if (statements != null) {

			printStatements(statements, printer);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.DoWhileStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(DoWhileStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getBody() != null) {
			printer.print("do ");
			n.getBody().accept(this, printer);
		}
		if (n.getCondition() != null) {
			printer.print(" while (");
			n.getCondition().accept(this, printer);
			printer.print(")");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.EmptyLiteral,
	 * java.lang.Object)
	 */
	@Override
	public void visit(EmptyLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.EmptyStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(EmptyStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * EnclosedExpression, java.lang.Object)
	 */
	@Override
	public void visit(EnclosedExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("(");
		if (n.getInner() != null) {
			n.getInner().accept(this, printer);
		}
		printer.print(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * ExpressionStatement, java.lang.Object)
	 */
	@Override
	public void visit(ExpressionStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getExpression() != null) {
			n.getExpression().accept(this, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * FieldAccessExpression, java.lang.Object)
	 */
	@Override
	public void visit(FieldAccessExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getScope() != null) {
			n.getScope().accept(this, printer);
		}
		if (n.getField() != null) {
			printer.print(".");
			n.getField().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.FloatLiteral,
	 * java.lang.Object)
	 */
	@Override
	public void visit(FloatLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ForeachStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ForeachStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("for (");
		if (n.getVariable() != null) {
			n.getVariable().accept(this, printer);
		}
		printer.print(" in ");
		if (n.getExpression() != null) {
			n.getExpression().accept(this, printer);
		}
		printer.print(") ");
		if (n.getBody() != null) {
			n.getBody().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ForStatement,
	 * java.lang.Object)
	 */
	@Override
	public void visit(ForStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("for (");
		if (n.getVariable() != null) {
			n.getVariable().accept(this, printer);
		}
		printer.print("; ");
		if (n.getCondition() != null) {
			n.getCondition().accept(this, printer);
		}
		printer.print("; ");
		if (n.getExpr() != null) {
			n.getExpr().accept(this, printer);
		}
		printer.print(") ");
		if (n.getBody() != null) {
			n.getBody().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * FunctionDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(FunctionDeclaration n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("function ");
		printer.print(n.getName());
		printer.print("(");
		List<Parameter> args = n.getParameters();
		if (args != null) {
			for (Iterator<Parameter> i = args.iterator(); i.hasNext();) {
				Parameter e = i.next();
				e.accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(") ");
		if (n.getBody() != null) {
			n.getBody().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * FunctionExpression, java.lang.Object)
	 */
	@Override
	public void visit(FunctionExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("function");
		if (n.getName() != null) {
			printer.print(" ");
			printer.print(n.getName());
		}
		printer.print("(");
		if (n.getParameters() != null) {
			for (Iterator<Parameter> i = n.getParameters().iterator(); i
					.hasNext();) {
				i.next().accept(this, printer);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(") ");
		if (n.getBody() != null) {
			n.getBody().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.GetAssignment
	 * , java.lang.Object)
	 */
	@Override
	public void visit(GetAssignment n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("get ");
		if (n.getPropertyName() != null) {
			n.getPropertyName().accept(this, printer);
		}
		printer.print("() ");
		if (n.getBlock() != null) {
			n.getBlock().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.HexIntegerLiteral
	 * , java.lang.Object)
	 */
	@Override
	public void visit(HexIntegerLiteral n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.IdentifierName
	 * , java.lang.Object)
	 */
	@Override
	public void visit(IdentifierName n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.IfStatement,
	 * java.lang.Object)
	 */
	@Override
	public void visit(IfStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("if (");
		if (n.getCondition() != null) {
			n.getCondition().accept(this, printer);
		}
		printer.print(") ");
		if (n.getThenStatement() != null) {
			n.getThenStatement().accept(this, printer);
		}
		if (n.getElseStatement() != null) {
			printer.print(" else ");
			n.getElseStatement().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ImportStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ImportStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("import ");
		if (n.getName() != null) {
			n.getName().accept(this, printer);
		}
		printer.printLn(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.LabelledStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(LabelledStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getLabel());
		printer.print(":");
		if (n.getStatement() != null) {
			n.getStatement().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.LetDefinition
	 * , java.lang.Object)
	 */
	@Override
	public void visit(LetDefinition n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("let ");
		List<VariableDeclaration> decls = n.getVariableDeclarations();
		if (decls != null) {
			printVariableDeclarations(decls, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.LetExpression
	 * , java.lang.Object)
	 */
	@Override
	public void visit(LetExpression n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("let ");
		if (n.getExpression() != null) {
			printer.print("(");
		}
		List<VariableDeclaration> decls = n.getVariableDeclarations();
		if (decls != null) {
			printVariableDeclarations(decls, printer);
		}
		if (n.getExpression() != null) {
			printer.print(") ");
			n.getExpression().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.LetStatement,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LetStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("let (");
		List<VariableDeclaration> decls = n.getVariableDeclarations();
		if (decls != null) {
			printVariableDeclarations(decls, printer);
		}
		printer.print(") ");
		if (n.getStatement() != null) {
			n.getStatement().accept(this, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.NewExpression
	 * , java.lang.Object)
	 */
	@Override
	public void visit(NewExpression n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("new ");
		if (n.getExpression() != null) {
			n.getExpression().accept(this, printer);
		}
		if (n.getInitializer() != null) {
			n.getInitializer().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.NullLiteral,
	 * java.lang.Object)
	 */
	@Override
	public void visit(NullLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("null");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ObjectLiteral
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ObjectLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("{");
		List<PropertyAssignment> propertyAssignments = n
				.getPropertyAssignments();
		if (propertyAssignments != null) {

			for (Iterator<PropertyAssignment> i = propertyAssignments
					.iterator(); i.hasNext();) {
				PropertyAssignment propertyAssignment = i.next();
				propertyAssignment.accept(this, printer);
				if (i.hasNext()) {
					printer.print(",");
				}

			}

		}
		printer.print("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.OctalLiteral,
	 * java.lang.Object)
	 */
	@Override
	public void visit(OctalLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.Parameter,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Parameter n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.Project,
	 * java.lang.Object)
	 */
	@Override
	public void visit(Project n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getCompilationUnits() != null) {
			for (CompilationUnit item : n.getCompilationUnits()) {
				if (item != null) {
					item.accept(this, printer);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.PutAssignment
	 * , java.lang.Object)
	 */
	@Override
	public void visit(PutAssignment n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getPropertyName() != null) {
			n.getPropertyName().accept(this, printer);
		}
		printer.print(" : ");
		if (n.getPropertyValue() != null) {
			n.getPropertyValue().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.RegexpLiteral
	 * , java.lang.Object)
	 */
	@Override
	public void visit(RegexpLiteral n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ReturnStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ReturnStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("return");
		if (n.getExpression() != null) {
			printer.print(" ");
			n.getExpression().accept(this, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * SequenceExpression, java.lang.Object)
	 */
	@Override
	public void visit(SequenceExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getExpressions() != null) {
			for (Iterator<Expression> i = n.getExpressions().iterator(); i
					.hasNext();) {
				Expression arg = i.next();
				if (arg != null) {
					arg.accept(this, printer);
					if (i.hasNext()) {
						printer.print(", ");
					}
				}

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.SetAssignment
	 * , java.lang.Object)
	 */
	@Override
	public void visit(SetAssignment n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("set ");
		if (n.getPropertyName() != null) {
			n.getPropertyName().accept(this, printer);
		}
		printer.print("(");
		printer.print(n.getIdentifier());
		printer.print(") ");
		if (n.getBlock() != null) {
			n.getBlock().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * StringLiteralSingle, java.lang.Object)
	 */
	@Override
	public void visit(StringLiteralSingle n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("'");
		printer.print(n.getValue());
		printer.print("'");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * StringLiteralDouble, java.lang.Object)
	 */
	@Override
	public void visit(StringLiteralDouble n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("\"");
		printer.print(n.getValue());
		printer.print("\"");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.SuperExpression
	 * , java.lang.Object)
	 */
	@Override
	public void visit(SuperExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("super");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.SwitchStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(SwitchStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getExpression() != null) {
			printer.print("switch (");
			n.getExpression().accept(this, printer);
			printer.print(")");
		}
		if (n.getBlock() != null) {
			n.getBlock().accept(this, printer);
		} else {
			printer.print("{}");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ThisExpression
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ThisExpression n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("this");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.ThrowStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(ThrowStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("throw ");
		if (n.getExpression() != null) {
			n.getExpression().accept(this, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.TryStatement,
	 * java.lang.Object)
	 */
	@Override
	public void visit(TryStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getTryBlock() != null) {
			printer.print("try ");
			n.getTryBlock().accept(this, printer);
		}
		if (n.getCatchClause() != null) {
			n.getCatchClause().accept(this, printer);
		}
		if (n.getFinallyBlock() != null) {
			printer.print(" finally ");
			n.getFinallyBlock().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.UnaryExpression
	 * , java.lang.Object)
	 */
	@Override
	public void visit(UnaryExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		switch (n.getUnaryOperator()) {
		case delete:
			printer.print(" delete ");
			break;
		case inverse:
			printer.print(" ~");
			break;
		case negative:
			printer.print(" -");
			break;
		case not:
			printer.print(" !");
			break;
		case opVoid:
			printer.print(" void ");
			break;
		case positive:
			printer.print(" +");
			break;
		case preDecrement:
			printer.print(" --");
			break;
		case preIncrement:
			printer.print(" ++");
			break;
		case typeof:
			printer.print(" typeof ");
			break;
		case rem:
			printer.print(" %");
			break;
		default:
			break;
		}
		if (n.getExpression() != null) {
			n.getExpression().accept(this, printer);
		}
		switch (n.getUnaryOperator()) {
		case posDecrement:
			printer.print("-- ");
			break;
		case posIncrement:
			printer.print("++ ");
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.UnaryExpression
	 * .UnaryOperator, java.lang.Object)
	 */
	@Override
	public void visit(UnaryOperator n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		switch (n) {
		case delete:
			printer.print("delete ");
			break;
		case inverse:
			printer.print("~");
			break;
		case negative:
			printer.print("-");
			break;
		case not:
			printer.print("!");
			break;
		case opVoid:
			printer.print("void ");
			break;
		case positive:
			printer.print("+");
			break;
		case preDecrement:
			printer.print("--");
			break;
		case preIncrement:
			printer.print("++");
			break;
		case typeof:
			printer.print("typeof ");
			break;
		case posDecrement:
			printer.print("--");
			break;
		case posIncrement:
			printer.print("++");
			break;
		case rem:
			printer.print("%");
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * VariableDeclaration, java.lang.Object)
	 */
	@Override
	public void visit(VariableDeclaration n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print(n.getName());
		if (n.getExpression() != null) {
			printer.print("=");
			n.getExpression().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.
	 * VariableExpression, java.lang.Object)
	 */
	@Override
	public void visit(VariableExpression n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("var ");
		List<VariableDeclaration> decls = n.getVariableDeclarations();
		if (decls != null) {
			printVariableDeclarations(decls, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.VariableStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(VariableStatement n, SourcePrinter printer)
			throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("var ");
		List<VariableDeclaration> decls = n.getVariableDeclarations();
		if (decls != null) {
			printVariableDeclarations(decls, printer);
		}
		printer.print(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.WhileStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(WhileStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("while (");
		if (n.getCondition() != null) {
			n.getCondition().accept(this, printer);
		}
		printer.print(") ");
		if (n.getBody() != null) {
			n.getBody().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.WithStatement
	 * , java.lang.Object)
	 */
	@Override
	public void visit(WithStatement n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		if (n.getExpression() != null) {
			printer.print("with (");
			n.getExpression().accept(this, printer);
			printer.print(")");
		}
		if (n.getStatement() != null) {
			n.getStatement().accept(this, printer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.BlockComment,
	 * java.lang.Object)
	 */
	@Override
	public void visit(BlockComment n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("/*");
		printer.print(n.getContent().replaceAll("\t", SourcePrinter.INDENT));
		printer.printLn("*/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.JSDocComment,
	 * java.lang.Object)
	 */
	@Override
	public void visit(JSDocComment n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("/**");
		printer.print(n.getContent().replaceAll("\t", SourcePrinter.INDENT));
		printer.printLn("*/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.LineComment,
	 * java.lang.Object)
	 */
	@Override
	public void visit(LineComment n, SourcePrinter printer) throws Exception {
		printer.print(n.getClass().getName());
		printer.print(":->");
		printInlineJSDoc(n.getJsDocComment(), printer);
		printer.print("//");
		printer.printLn(n.getContent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javascriptParser.com.digiarea.es5.visitor.VoidVisitor#visit(javascriptParser.com.digiarea.es5.NodeList,
	 * java.lang.Object)
	 */
	@Override
	public <E extends Node> void visit(NodeList<E> n, SourcePrinter printer)
			throws Exception {
		// not used
	}

}
