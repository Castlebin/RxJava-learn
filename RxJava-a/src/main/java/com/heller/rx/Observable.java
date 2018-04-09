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
    
    public <R> Observable<R> map(Transformer<? super T, ? extends R> transformer) {
        return create(new MapOnSubscribe<T, R>(this, transformer));
    }
    
    public Observable<T> subscribeOn(Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onStart();
                // 将事件的生产切换到新的线程。
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                    }
                });
            }
        });
    }
    
    public interface Transformer<T, R> {
        R call(T from);
    }
    
}
