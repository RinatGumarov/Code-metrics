package csmc.javacc.parse.context;

/**
 * A simple context class that represents parsing context
 * @param <K> - context key
 * @param <V> - context value
 */
public abstract class ParseContext<K, V> {
    protected ParseContext parent;
    protected K key;
    protected V value;

    public ParseContext getParent() {
        return parent;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    /**
     * Return context hierarchy
     */
    @Override
    public String toString() {
        return parent == null || parent.toString().isEmpty() ? key.toString() : parent.toString() + "." + key.toString();
    }
}
