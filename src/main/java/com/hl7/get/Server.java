package com.hl7.get;

import com.hl7.get.view.MessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {

    public static void main(String[] args) {
        Server.start(8080);
    }

    private static void start(int port) {
        ServerBootstrap b;
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline p = ch.pipeline();
//                        p.addLast(new FixedLengthFrameDecoder(1024));
//                        p.addLast(new StringDecoder());
                        p.addLast(new ServerHandler());
                    }
                });
            ChannelFuture future = b.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            //TODO: handle exception
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
