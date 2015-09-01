package com.github.jraft.rpc.protocol.yi;

import com.github.jraft.remoting.exchange.ExchangeClient;
import com.github.jraft.remoting.exchange.ResponseFuture;
import com.github.jraft.rpc.Invocation;
import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.Result;
import com.github.jraft.rpc.RpcException;
import com.github.jraft.rpc.protocol.RpcResult;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * TODO ����дע��
 * date��2015/8/21-14:43
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public class YiInvoker<T>  implements Invoker<T> {

    private ExchangeClient  client ;

    private Class<T>  type;
    public YiInvoker(Class<T> type, ExchangeClient client){
        this.type = type;
        this.client = client;
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
        ResponseFuture responseFuture = client.request(invocation, 100);
        return (Result) responseFuture.get();
    }
}
