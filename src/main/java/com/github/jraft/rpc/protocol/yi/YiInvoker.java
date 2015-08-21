package com.github.jraft.rpc.protocol.yi;

import com.github.jraft.rpc.Invocation;
import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.Result;
import com.github.jraft.rpc.RpcException;
import com.github.jraft.rpc.protocol.RpcResult;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * TODO 这里写注释
 * date：2015/8/21-14:43
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class YiInvoker<T>  implements Invoker<T> {

    private  Socket socket ;

    private Class<T>  type;
    public YiInvoker(Class<T> type, Socket client){
        this.type = type;
        socket = client;
    }

    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException{
        try {
            return doInvoke(invocation);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return new RpcResult(throwable);
        }
    }

    private Result doInvoke(Invocation invocation)  throws Throwable {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeUTF(getInterface().getName());
        oos.writeObject(invocation);
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        RpcResult object = (RpcResult) ois.readObject();
        return object;
    }
}
