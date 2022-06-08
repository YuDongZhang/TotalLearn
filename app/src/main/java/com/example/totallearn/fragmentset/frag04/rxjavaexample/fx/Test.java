package com.example.totallearn.fragmentset.frag04.rxjavaexample.fx;

public class Test<T> {

    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

}
