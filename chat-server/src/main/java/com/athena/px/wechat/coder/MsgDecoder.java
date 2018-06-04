package com.athena.px.wechat.coder;

import com.athena.px.wechat.enums.Chat;
import com.athena.px.wechat.protocol.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.msgpack.MessagePack;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/1 19:10
 */
public class MsgDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        MessagePack msgPack = new MessagePack();
        final int len = in.readableBytes();
        byte[] bytes = new byte[len];
        in.getBytes(len,bytes,0,len);
        list.add(msgPack.read(bytes));
    }

    public Message decode(String txt){
        Message msg = new Message();
        String[] txts = StringUtils.delimitedListToStringArray(txt,"][");
         String head = txts[0].replace("[","");
         String tail = txts[txts.length-1].replace("]","");
        if(head.equals(Chat.CHAT.getName())){
            msg.setContent(txts[2]);
        }
        msg.setProtocol(head);
        msg.setSender(txts[1]);
        msg.setDate(tail);
        return msg;
    }
}
