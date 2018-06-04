package com.athena.px.wechat.chatserver;

import com.athena.px.wechat.coder.MsgDecoder;
import com.athena.px.wechat.coder.MsgEncoder;
import com.athena.px.wechat.handler.WebChatHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/1 18:30
 */
public class ChatServer{

    private final static Logger logger = LoggerFactory.getLogger(ChatServer.class);

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        try {
            //获取服务器IP
            InetAddress address = InetAddress.getLocalHost();
            String hostAddress = address.getHostAddress();


            server.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            //黏包与拆包
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            pipeline.addLast(new LengthFieldPrepender(4));
                            //序列化与反序列化
                            pipeline.addLast(new MsgDecoder());
                            pipeline.addLast(new MsgEncoder());
                            //协议
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                            //自定义Handler
                            pipeline.addLast(new WebChatHandler());
                        }
                    });
            ChannelFuture f = server.bind("192.168.2.103",80).sync();
            logger.info("WeChat 服务器已启动!监听地址:192.168.2.103:80");
            f.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
