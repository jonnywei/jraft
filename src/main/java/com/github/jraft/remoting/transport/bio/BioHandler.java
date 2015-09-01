package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.IoHandler;
import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.exchange.DefaultFuture;
import com.github.jraft.remoting.exchange.ExchangeHandler;
import com.github.jraft.remoting.exchange.Request;
import com.github.jraft.remoting.exchange.Response;
import com.github.jraft.rpc.Invocation;
import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.protocol.RpcInvocation;

import java.io.IOException;
import java.net.Socket;

/**
 * 输入输出处理类
 * date：2015/9/1-11:36
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioHandler implements IoHandler {


    private ExchangeHandler  handler;


    private Socket socket;

    BioCodec codec = new BioCodec();

    public BioHandler(IoHandler handler) {
        this.handler =(ExchangeHandler) handler;
    }


    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void sent(Object message) throws RemotingException {
        try {
            codec.encode(socket.getOutputStream(),message);
        } catch (IOException e) {
            throw new RemotingException("codec error", e);
        }
    }

    public void received(){
        Object object = null;
        try {
            object =  codec.decode(socket.getInputStream());
            received(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void received(Object message) throws RemotingException {
        if(message instanceof Response){
            Response response = (Response) message;
            DefaultFuture.received(  response);
        }else if(message instanceof Request){
            Request request = (Request) message;
            Response response = new Response(request.getId());
            Object msg = request.getData();
            Object result = handler.reply(msg);
            response.setResult(result);
            sent(response);
        }
    }



}
