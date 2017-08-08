package csmc.javacc.parse.util;

import java.util.Objects;

public class Tuple2<F, S> {
    private F first;
    private S second;

    public Tuple2(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(first, tuple2.first) &&
                Objects.equals(second, tuple2.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
