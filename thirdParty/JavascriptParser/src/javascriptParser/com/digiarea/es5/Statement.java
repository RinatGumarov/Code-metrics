package javascriptParser.com.digiarea.es5;

/**
 * The Class Statement.
 */
public abstract class Statement extends Node {

    Statement() {
        super();
    }

    Statement(JSDocComment jsDocComment, int posBegin, int posEnd) {
        super(jsDocComment, posBegin, posEnd);
    }

}
