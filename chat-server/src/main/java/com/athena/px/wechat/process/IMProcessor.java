package com.athena.px.wechat.process;

import com.athena.px.wechat.coder.MsgDecoder;
import com.athena.px.wechat.enums.Chat;
import com.athena.px.wechat.protocol.Message;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/1 18:53
 */
public class IMProcessor {

    private static class LazyHolder{
        private static final IMProcessor imProcessor = new IMProcessor();
    }
    private IMProcessor(){}

    public static final IMProcessor getInstance(){
        return LazyHolder.imProcessor;
    }

    private final static ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final static MsgDecoder msgDecoder = MsgDecoder.getInstance();

    public void process(Channel client,String text){
        Message message = msgDecoder.decode(text);
        if(message.getProtocol().equals(Chat.LOGIN.getName())){
            onlineUsers.add(client);
        }else if(message.getProtocol().equals(Chat.LOGOUT.getName())){
            onlineUsers.remove(client);
        }
        for(Channel channel : onlineUsers){
            if(client.equals(channel)){
                continue;
            }
            channel.writeAndFlush(new TextWebSocketFrame(text));
        }
    }
}
