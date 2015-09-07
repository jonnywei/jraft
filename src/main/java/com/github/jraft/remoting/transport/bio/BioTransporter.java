package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.Client;
import com.github.jraft.remoting.ChannelHandler;
import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.Server;
import com.github.jraft.remoting.transport.Transporter;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:37
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioTransporter implements Transporter {
    @Override
    public Server bind(String host, int port,ChannelHandler handler)  throws RemotingException {
        return new BioServer(host,port,new BioChannelHandler(handler));
    }

    @Override
    public Client connect(String host, int port,ChannelHandler handler)  throws RemotingException {
        return new BioClient(host, port,new BioChannelHandler(handler));
    }
}
