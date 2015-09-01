package com.github.jraft.rpc.protocol.yi;

import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.exchange.ExchangeClient;
import com.github.jraft.remoting.exchange.ExchangeHandler;
import com.github.jraft.remoting.exchange.ExchangeServer;
import com.github.jraft.remoting.exchange.header.HeadExchanger;
import com.github.jraft.rpc.*;
import com.github.jraft.rpc.protocol.RpcInvocation;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * date 2015/8/21-11:52
 * author weijianjun
 * Copyright (c) 2014
 */

public class YiProtocol  implements Protocol{

    private Map<String ,Exporter>   exporterMap = new HashMap<String,Exporter>();


    ExchangeServer server;


    ExchangeClient client;


    private ExchangeHandler exchangeHandler = new ExchangeHandler() {

        /**
         * 服务器端对请求响应
         * @param msg
         * @return
         */
        public   Object reply(Object msg) throws RemotingException {
            if(msg instanceof Invocation){
                Invocation invocation = (Invocation) msg;

                Invoker<?> invoker = getInvoker(invocation);
                return     invoker.invoke(invocation);
            }
            throw  new RemotingException("msg error");
        }

        private    Invoker<?> getInvoker(Invocation invocation) {

            String interfaceName = invocation.getInterfaceName();

            Exporter<?> exporter = exporterMap.get(interfaceName);

            return exporter.getInvoker();

        }
        @Override
        public void sent(Object message) throws RemotingException {

        }

        @Override
        public void received(Object message) throws RemotingException {

        }
    };



    @Override
    public int getDefaultPort() {
        return 8081;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) {
        YiExporter<T> exporter = new YiExporter<T>(invoker);
        exporterMap.put(invoker.getInterface().getName(),exporter);
        try {
            openServer(getDefaultPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exporter;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type) {
        YiInvoker<T> yiInvoker = new YiInvoker<T>(type,getClient("localhost", getDefaultPort()));
        return yiInvoker;
    }

    private void openServer ( int port) throws Exception{
        HeadExchanger exchanger = new HeadExchanger();
        server = exchanger.bind("localhost",port,exchangeHandler);
    }


    private ExchangeClient getClient(String serverHost, int port){
        if(client == null){
            client = createClient(serverHost,port);
        }
        return  client;
    }

    private ExchangeClient createClient(String serverHost, int port){
        try {
            HeadExchanger exchanger = new HeadExchanger();
            return exchanger.connect(serverHost,port,exchangeHandler) ;
        } catch (RemotingException e) {
            throw   new RpcException("socket error",e);
        }
    }
}
