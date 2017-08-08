package csmc.javacc.parse.util;

import java.util.Objects;

public class Tuple3<F, S, T> {
    private F first;
    private S second;
    private T third;

    public Tuple3(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
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

    public T getThird() {
        return third;
    }

    public void setThird(T third) {
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        return Objects.equals(first, tuple3.first) &&
                Objects.equals(second, tuple3.second) &&
                Objects.equals(third, tuple3.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }
}
