package javascriptParser.com.digiarea.es5;

/**
 * The Class Expression.
 */
public abstract class Expression extends Node {

    Expression() {
        super();
    }

    Expression(JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
    }

}
