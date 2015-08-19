package com.github.jraft.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 这里写注释
 * date：2015/8/19-15:56
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class RpcProtocol {

    private static Map<String ,Object>  exporterMap = new HashMap<String, Object>();

    public static void export(final Object  service) {
            System.out.println("export " +service.getClass().getInterfaces()[0].getName());
            exporterMap.put(service.getClass().getInterfaces()[0].getName(), service);

    }

    public  static void  startServer( int port) throws Exception{
        ServerSocket serverSocket = new ServerSocket(port);

        while(true) {
            final Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        String interfaceName = ois.readUTF();
                        String methodName = ois.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
                        Object[] parameterObjects = (Object[]) ois.readObject();
                        Object service = exporterMap.get(interfaceName);
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        try {
                            oos.writeObject(method.invoke(service, parameterObjects));
                        } catch (Throwable throwable) {
                            oos.writeObject(throwable);
                        }
                        oos.flush();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }
        }

    public static  <T> T refer(final Class<T> tClass, final  String host ,final int port) throws Exception{

        return  (T)  Proxy.newProxyInstance(RpcProtocol.class.getClassLoader(), new Class<?>[]{tClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeUTF(tClass.getName());
                oos.writeUTF(method.getName());
                oos.writeObject(method.getParameterTypes());
                oos.writeObject(args);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object object = ois.readObject();
                socket.close();
                if (object instanceof Throwable) {
                    throw (Throwable) object;
                }
                return object;
            }
        });
    }


    public static void main(String[] args) throws Exception{
        HelloWordServiceImpl helloWordService = new HelloWordServiceImpl();
        HelloService2Impl helloWordService2 = new HelloService2Impl();
        RpcProtocol.export(helloWordService);
        RpcProtocol.export(helloWordService2);
        RpcProtocol.startServer( 4567);

    }

}


 interface  HelloWorldService {

    String sayHello(String name);

     String sayObject(RpcClient client ,int s);
}

interface  HelloWorldService2 {

    String sayHello(String name);

    String sayObject(RpcClient client ,int s);
}

class HelloWordServiceImpl implements  HelloWorldService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public String sayObject(RpcClient client ,int s) {
        if (s %9 == 0 ){
            throw new RuntimeException("error");
        }
        return client.toString() + " " + s;
    }
}

class HelloService2Impl implements  HelloWorldService2{

    @Override
    public String sayHello(String name) {
        return "2";
    }

    @Override
    public String sayObject(RpcClient client, int s) {
        return "2";
    }
}