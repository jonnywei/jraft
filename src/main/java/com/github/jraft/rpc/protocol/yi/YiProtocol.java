package com.github.jraft.rpc.protocol.yi;

import com.github.jraft.rpc.Exporter;
import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.Protocol;
import com.github.jraft.rpc.RpcException;
import com.github.jraft.rpc.protocol.RpcInvocation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 这里写注释
 * date：2015/8/21-11:52
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class YiProtocol  implements Protocol{

    private Map<String ,Exporter>   exporterMap = new HashMap<String,Exporter>();

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
        YiInvoker<T> yiInvoker = new YiInvoker<T>(type,createClient("localhost",getDefaultPort()));
        return yiInvoker;
    }

    private void openServer ( int port) throws Exception{
        ServerSocket serverSocket = new ServerSocket(port);
        while(true) {
            final Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                            String interfaceName = ois.readUTF();
                            RpcInvocation invocation = (RpcInvocation) ois.readObject();
                            Exporter<?> exporter = exporterMap.get(interfaceName);
                            Invoker<?> invoker = exporter.getInvoker();
                            Object result =  invoker.invoke(invocation);
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                oos.writeObject(result);
                            } catch (Throwable throwable) {
                                oos.writeObject(throwable);
                            }
                            oos.flush();

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
//                            socket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }

    private Socket createClient(String serverHost, int port){
        try {
            Socket socket = new Socket(serverHost,port);
            return socket;
        } catch (IOException e) {
            throw   new RpcException("socket error",e);
        }
    }
}
