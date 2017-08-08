package javascriptParser.com.digiarea.es5;

/**
 * The Class Literal.
 */
public abstract class Literal extends Expression {

    Literal() {
        super();
    }

    Literal(JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
    }

}
