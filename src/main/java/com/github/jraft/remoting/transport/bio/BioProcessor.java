package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.ChannelHandler;
import com.github.jraft.remoting.RemotingException;

import java.net.Socket;

/**
 * BIO 处理类
 * date：2015/9/7-11:36
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioProcessor {

    private   BioCodec bioCodec = new BioCodec();
    private ChannelHandler channelHandler;

    public BioProcessor(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    public void messageReceived(Socket socket) throws Exception {
        BioChannel bioChannel =  BioChannel.getOrAddChannel(socket, channelHandler);
        Object object = bioCodec.decode(socket.getInputStream());
        channelHandler.received(bioChannel,object);
    }


    public void writeRequested(Socket socket, Object message) throws RemotingException{
        BioChannel bioChannel =  BioChannel.getOrAddChannel(socket, channelHandler);
        channelHandler.sent(bioChannel,message);
    }

    public void exceptionCaught(){

    }

}
