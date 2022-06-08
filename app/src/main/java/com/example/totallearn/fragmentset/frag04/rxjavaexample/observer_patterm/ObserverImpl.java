package com.example.totallearn.fragmentset.frag04.rxjavaexample.observer_patterm;

public class ObserverImpl implements Observer {

    @Override
    public <T> void changeAction(T observableInfo) {
        System.out.println(observableInfo);
    }
}
