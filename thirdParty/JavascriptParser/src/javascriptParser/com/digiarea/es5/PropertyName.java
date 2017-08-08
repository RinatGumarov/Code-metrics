package javascriptParser.com.digiarea.es5;

/**
 * The Class PropertyName.
 */
public abstract class PropertyName extends StringBasedLiteral {

    PropertyName() {
        super();
    }

    PropertyName(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(value, jsDocComment, posBegin, posEnd);
    }

}
