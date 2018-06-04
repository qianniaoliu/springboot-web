package com.athena.px.wechat.coder;

import com.athena.px.wechat.enums.Chat;
import com.athena.px.wechat.protocol.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/1 19:10
 */
public class MsgEncoder extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        byteBuf.writeBytes(messagePack.write(s));
    }

    public String encode(Message msg){
        StringBuilder sb = new StringBuilder();
        sb.append("\\[" + msg.getProtocol() + "\\]")
                .append("\\[" + msg.getSender() + "\\]");
        if(msg.getProtocol().equals(Chat.CHAT)){
            sb.append("\\[" + msg.getContent() + "\\]");
        }
        sb.append("\\[" + msg.getDate() + "\\]");
        return sb.toString();
    }
}
