package com.github.jraft.remoting.transport.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * Created by wjj on 15-8-15.
 */
public class NettyClientHandler extends ChannelOutboundHandlerAdapter {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        ctx.write(new String("aaa"));
    }
}
