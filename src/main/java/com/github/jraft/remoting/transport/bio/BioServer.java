package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.Server;
import com.github.jraft.remoting.exchange.Request;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:56
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioServer implements Server {

    private ServerSocket serverSocket;

    /**
     * Allows the server developer to specify the backlog that
     * should be used for server sockets. By default, this value
     * is 100.
     */
    protected int backlog = 100;

    protected int  executorThreadCount = 100;

    protected int  acceptorThreadCount = 1;

    protected Executor executor = null;


    /**
     * Running state of the endpoint.
     */
    protected volatile boolean running = false;



    public BioServer(String host, int port) throws RemotingException {

        try {
            serverSocket  = new ServerSocket(port,backlog,InetAddress.getByName(host));
            executor = Executors.newFixedThreadPool(executorThreadCount);
            running = true;
            doOpen();
        } catch (Throwable e) {
           throw  new RemotingException("create server error",e);
        }
    }

    protected   void doOpen() throws Throwable{

        for(int i = 0;  i < acceptorThreadCount; i ++){
            Thread acceptorThread = new Thread(new Acceptor(),"Bio-Acceptor" +i);
            acceptorThread.setDaemon(true);
            acceptorThread.start();
        }
    }


    /**
     * 处理socket请求
     * @param socket
     * @return
     */
    protected  boolean processSocket(Socket socket){

        try {
            executor.execute(new SocketProcessor(socket));
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    @Override
    public boolean isBound() {
        return false;
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


    /**
     * Server socket acceptor thread.
     */
    protected class Acceptor implements Runnable {

        @Override
        public void run() {
            while (running){
                try {

                    Socket socket =   serverSocket.accept();
                    if ( !processSocket(socket)){
                        try {
                            socket.close();
                        }catch (IOException e){
                            //ignore
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    protected class SocketProcessor implements Runnable{

        Socket socket ;

        public SocketProcessor(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BioCodec bioCodec = new BioCodec();
                Object object = bioCodec.decode(socket.getInputStream());
                if(object instanceof Request){

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
