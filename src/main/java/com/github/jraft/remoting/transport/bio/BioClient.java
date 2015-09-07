package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.Client;
import com.github.jraft.remoting.RemotingException;

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

    BioProcessor bioProcessor;

    /**
     * Running state of the endpoint.
     */
    protected volatile boolean running = false;


    public BioClient(String host, int port ,BioChannelHandler handler)  throws RemotingException {
        try {
            inetSocketAddress = new InetSocketAddress(host,port);

            bioProcessor = new BioProcessor(handler);

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
        Thread reciverThread = new Thread(new Receiver(),"client-receiver");
        reciverThread.setDaemon(true);
        reciverThread.start();

    }


    @Override
    public void reconnect() throws RemotingException {

    }

    @Override
    public void send(Object message) throws RemotingException {
      bioProcessor.writeRequested(socket, message);
    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        send(message);
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



    protected class  Receiver implements Runnable {

        @Override
        public void run() {
            while (running){
                try {
                    bioProcessor.messageReceived(socket);
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
