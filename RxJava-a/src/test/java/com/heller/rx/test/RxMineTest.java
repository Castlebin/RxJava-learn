package com.heller.rx.test;

import com.heller.rx.Observable;
import com.heller.rx.Subscriber;
import org.junit.Test;

public class RxMineTest {
    
    @Test
    public void testBasic1() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                System.out.println("on start");
            }
            
            @Override
            public void onCompleted() {}
            
            @Override
            public void onError(Throwable t) {}
            
            @Override
            public void onNext(Integer t) {
                System.out.println(t);
            }
        });
    }
    
}
