package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.Channel;
import com.github.jraft.remoting.ChannelHandler;
import com.github.jraft.remoting.RemotingException;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 这里写注释
 * date：2015/9/7-11:05
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioChannel implements Channel {


    private static  ConcurrentHashMap<Socket, BioChannel >  channelsMap  = new ConcurrentHashMap<Socket, BioChannel>();


    private Socket socket;


    private  ChannelHandler channelHandler;


    BioCodec codec = new BioCodec();


    public BioChannel(Socket socket, ChannelHandler channelHandler) {
        this.socket = socket;
        this.channelHandler = channelHandler;
    }

    /**
     * 得到BioChannel对象
     * @param socket
     * @param channelHandler
     * @return
     */
    static BioChannel getOrAddChannel(Socket socket, ChannelHandler channelHandler){
            if(!channelsMap.contains(socket)){
                BioChannel bioChannel = new BioChannel(socket, channelHandler);
                channelsMap.putIfAbsent(socket, bioChannel);
            }
        return channelsMap.get(socket);
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return channelHandler;
    }

    @Override
    public void sent(Object message) throws RemotingException {
        try {
            codec.encode(socket.getOutputStream(),message);
        } catch (IOException e) {
            throw new RemotingException("codec error", e);
        }
    }

    @Override
    public void received(Object message) throws RemotingException {

    }
}
