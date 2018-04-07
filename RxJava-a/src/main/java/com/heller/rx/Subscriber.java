package com.heller.rx;

public abstract class Subscriber<T> implements Observer<T> {
    
    public abstract void onStart();
    
}
