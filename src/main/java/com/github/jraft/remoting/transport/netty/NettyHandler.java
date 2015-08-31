package com.github.jraft.remoting.transport.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by wjj on 15-8-15.
 */
public class NettyHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx,msg);
        System.out.println("income message");
        ctx.write("hello");
        ctx.flush();
    }
}
