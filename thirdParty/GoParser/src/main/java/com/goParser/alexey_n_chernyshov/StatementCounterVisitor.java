package com.goParser.alexey_n_chernyshov;

import com.goParser.alexey_n_chernyshov.generated.*;

public class StatementCounterVisitor extends AbstractASTVisitor {

    private int statements;

    public int getStatements() {
        return statements;
    }

    @Override
    public Object visit(SimpleNode node, Object data) {return null;}

    @Override
    public Object visit(ASTIntegerLiteral node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFloatLiteral node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTImaginaryLiteral node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTRuneLiteral node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTStringLiteral node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFunctionLit node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFunction node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTResult node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTParameters node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTIdentifierList node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFunctionBody node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTMapType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTChannelType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTArrayType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTStructType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFunctionType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSliceType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFieldDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTInterfaceType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTPointerType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTMethodSpec node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTMethodName node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTInterfaceTypeName node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTBlock node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTStatementList node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTStatement node, Object data) {
        statements++;
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTLabeledStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTGoStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTReturnStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTBreakStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTContinueStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTGotoStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFallthroughStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTDeferStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTElseStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTIfStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSelectStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTRecvStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSendStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTChannel node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTTypeSwitchStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTTypeCaseClause node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTExprSwitchStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTExprCaseClause node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTForStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTCondition node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTForClause node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTRangeClause node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTLabel node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSimpleStatement node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTShortVarDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTIncStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTDecStmt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSourceFile node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTPackageClause node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTImportDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTImportPath node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTTopLevelDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTMethodDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTReciever node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTDeclaration node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTConstDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTTypeDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTTypeSpec node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTConstSpec node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTVarDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTVarSpec node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFunctionDecl node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTFunctionName node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTPackageName node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTReceiverType node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTMethodExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTOperand node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTConversion node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTPrimaryExpression node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSlice node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTTypeAssertion node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSelector node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTIndex node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTArguments node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTExpressionList node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTUnaryPlus node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTUnaryMinus node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTUnaryNot node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTUnaryXor node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTUnaryMult node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTUnaryAnd node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTUnaryLessMinus node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTMult node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTDiv node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTDivInt node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTShiftLeft node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTShiftRight node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTAndXor node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTAdd node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTSub node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTXor node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTEqual node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTNotEqual node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTLess node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTLessEqual node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTGreater node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTGreaterEqual node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTAndAnd node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTOrOr node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLiteralType node, Object data){
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTLiteralValue node, Object data){
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTElementList node, Object data){
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTKeyedElement node, Object data){
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTKey node, Object data){
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTElement node, Object data){
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTCompositeLit node, Object data){
        visitAllChildren(node, data);
        return data;
    }

}
