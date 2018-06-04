package com.athena.px.wechat.listener;

import com.athena.px.wechat.chatserver.ChatServer;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/2 10:19
 */
@Configuration
public class WeChatListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Observable.just("hahaha")
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ChatServer cs = new ChatServer();
                        cs.start();
                    }
                });
    }
}
