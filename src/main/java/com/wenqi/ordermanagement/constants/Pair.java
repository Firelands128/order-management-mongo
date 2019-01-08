package com.wenqi.ordermanagement.constants;

import java.util.Objects;

public class Pair<T, U> {
    private T key;
    private U value;

    public Pair(T key, U value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public U getValue() {
        return value;
    }

    public void setValue(U value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key) &&
                Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
