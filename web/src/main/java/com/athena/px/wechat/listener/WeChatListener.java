package com.athena.px.wechat.listener;

import com.athena.px.wechat.chatserver.ChatServer;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/2 10:19
 */
@Component
public class WeChatListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${athena.chat-server}")
    public String hostAddress;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Observable.just("hahaha")
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ChatServer cs = new ChatServer();
                        cs.start(hostAddress);
                    }
                });
    }
}
