package com.heller.rx;

public class Observable<T> {
    
    final OnSubscribe<T> onSubscribe;
    
    private Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }
    
    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }
    
    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }
    
    public interface OnSubscribe<T> {
        void call(Subscriber<? super T> subscriber);
    }
    
}
