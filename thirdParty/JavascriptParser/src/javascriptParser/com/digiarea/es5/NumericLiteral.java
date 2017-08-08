package javascriptParser.com.digiarea.es5;

/**
 * The Class NumericLiteral.
 */
public abstract class NumericLiteral extends PropertyName {

    NumericLiteral() {
        super();
    }

    NumericLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(value, jsDocComment, posBegin, posEnd);
    }

}
