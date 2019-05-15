package com.hl7.get;

import com.hl7.manage.GetMessage;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MessageGet {

    /**
     * 作为服务器将获取各种HL7协议相关数据包
     * 
     */

    ServerBootstrap b;

    GetMessage getMessage;

    public MessageGet(GetMessage getMessage) {
        this.getMessage = getMessage;
        start(8080);
    }

    public MessageGet(int port, GetMessage getMessage) {
        this.getMessage = getMessage;
        start(port);
    }

    private void start(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        final MessageHandler messageHandler = new MessageHandler(this.getMessage);
        try {
            b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1000)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception{
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(messageHandler);
                    }
                });
                ChannelFuture future = b.bind(port).sync();
                future.channel().closeFuture().sync();
        } catch (Exception e) {
            //TODO: handle exception
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public void close(){
        if(b == null){
            return;
        }

    }
}
