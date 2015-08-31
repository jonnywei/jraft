package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.Client;
import com.github.jraft.remoting.RemotingException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:56
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioClient implements Client {

    InetSocketAddress  inetSocketAddress ;
    Socket socket;
    public BioClient(String host, int port)  throws RemotingException {
        try {
            inetSocketAddress = new InetSocketAddress(host,port);
            doOpen();
            doConnect();
        } catch (Throwable e) {
            throw  new RemotingException("connector error", e);
        }
    }


    private  void doOpen() throws Throwable{

    }


    private  void doConnect() throws Throwable{
         socket = new Socket();
         socket. connect(inetSocketAddress);
    }


    @Override
    public void reconnect() throws RemotingException {

    }

    @Override
    public void send(Object message) throws RemotingException {

    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {

    }

    @Override
    public void close() {

    }

    @Override
    public void close(int timeout) {

    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
