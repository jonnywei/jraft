package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.Channel;
import com.github.jraft.remoting.ChannelHandler;
import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.exchange.DefaultFuture;
import com.github.jraft.remoting.exchange.ExchangeHandler;
import com.github.jraft.remoting.exchange.Request;
import com.github.jraft.remoting.exchange.Response;


/**
 * 输入输出处理类
 * date：2015/9/1-11:36
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioChannelHandler implements ChannelHandler {


    private ExchangeHandler  handler;


    public BioChannelHandler(ChannelHandler handler) {
        this.handler =(ExchangeHandler) handler;
    }


    @Override
    public void sent(Channel channel, Object message) throws RemotingException {
         channel.sent(message);
    }


    @Override
    public void received(Channel channel, Object message) throws RemotingException {
        if(message instanceof Response){
            Response response = (Response) message;
            DefaultFuture.received(  response);
        }else if(message instanceof Request){
            Request request = (Request) message;
            Response response = new Response(request.getId());
            Object msg = request.getData();
            Object result = handler.reply(msg);
            response.setResult(result);
            sent(channel,response);
        }
    }



}
