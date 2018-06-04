package com.athena.px.wechat.handler;

import com.athena.px.wechat.process.IMProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/1 18:45
 */
public class WebChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final static Logger logger = LoggerFactory.getLogger(WebChatHandler.class);

    private final IMProcessor imProcessor = new IMProcessor();


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        imProcessor.process(channelHandlerContext.channel(),textWebSocketFrame.text());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("有新成员加入聊天室!");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("有成员离开了聊天室!");
    }
}
