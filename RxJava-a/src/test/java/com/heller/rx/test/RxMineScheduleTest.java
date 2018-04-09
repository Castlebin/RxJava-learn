package com.heller.rx.test;

import com.heller.rx.Observable;
import com.heller.rx.Schedulers;
import com.heller.rx.Subscriber;
import org.junit.Test;

/**
 * 由于junit的线程问题，这里又使用了线程池，所以使用了Thread.join()方法
 *
 * (
 * Junit本身是不支持普通的多线程测试的，这是因为Junit的底层实现上，
 * 是用System.exit退出用例执行的，
 * 主线程终止jvm都停了，其他线程肯定执行不了了(具体分析查看源码org.junit.runner.JUnitCore)。)
 *
 * http://www.coderli.com/multi-thread-junit-grobountils/
 */
public class RxMineScheduleTest {
    
    @Test
    public void testSchedule() throws InterruptedException {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName()); //new Thread
                subscriber.onNext(1);
            }
        })
            .subscribeOn(Schedulers.io())
            .subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() { }
                @Override
                public void onError(Throwable t) { }
                @Override
                public void onNext(Integer var1) {
                    System.out.println("Subscriber@ " + Thread.currentThread().getName()); // new Thread
                    System.out.println(var1);
                }
            });
        
        // 等待2再让junit结束
        Thread.currentThread().join(2000);
    }
    
}
