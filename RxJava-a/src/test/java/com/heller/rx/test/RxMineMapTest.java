package com.heller.rx.test;

import com.heller.rx.Observable;
import com.heller.rx.Observable.OnSubscribe;
import com.heller.rx.Observable.Transformer;
import com.heller.rx.Subscriber;
import org.junit.Test;

public class RxMineMapTest {
    
    @Test
    public void testBasic1() {
        Observable.create(new OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).map(new Transformer<Integer, String>() {
            @Override
            public String call(Integer from) {
                return "mapping: " + from;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("on start[String]");
            }
            
            @Override
            public void onCompleted() {}
            
            @Override
            public void onError(Throwable t) {}
            
            @Override
            public void onNext(String t) {
                System.out.println(t);
            }
        });
    }
    
}
