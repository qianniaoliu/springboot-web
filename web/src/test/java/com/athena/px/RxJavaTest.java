package com.athena.px;

import com.athena.px.wechat.chatserver.ChatServer;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/2 16:45
 */
public class RxJavaTest {


    @Test
    @Ignore
    public void rxTest(){
        Flowable.just("Hello world")
                .subscribe(System.out::println);
        Observable.just("hahaha")
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Thread.sleep(10000);
                        System.out.println(s);
                    }
                });
        System.out.println("213213123");
    }
}
