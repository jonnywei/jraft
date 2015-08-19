package com.github.jraft.remoting;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by wjj on 15-8-15.
 */
public class NettyClient {

    public static void main(String[] args) {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.channel(NioSocketChannel.class).group(eventLoopGroup).handler(new ChannelInitializer<SocketChannel>() {

        @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ObjectDecoder(null));
                ch.pipeline().addLast(new ObjectEncoder());
             ch.pipeline().addLast(new NettyClientHandler());
            }
        });


        try {
            ChannelFuture f = bootstrap.connect("localhost",8080).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
