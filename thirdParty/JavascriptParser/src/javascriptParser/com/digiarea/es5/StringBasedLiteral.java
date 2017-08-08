package javascriptParser.com.digiarea.es5;

/**
 * The Class StringBasedLiteral.
 */
public abstract class StringBasedLiteral extends Literal {

    /** 
     * The value.
     */
    protected String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    StringBasedLiteral() {
        super();
    }

    StringBasedLiteral(String value, JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
        this.value = value;
    }

}
