package javascriptParser.com.digiarea.es5;

/**
 * The Class StringLiteral.
 */
public abstract class StringLiteral extends PropertyName {

    StringLiteral() {
        super();
    }

    StringLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(value, jsDocComment, posBegin, posEnd);
    }

}
