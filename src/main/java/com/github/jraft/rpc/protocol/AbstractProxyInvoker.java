package com.github.jraft.rpc.protocol;

import com.github.jraft.rpc.Invocation;
import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.Result;
import com.github.jraft.rpc.RpcException;

/**
 * TODO 这里写注释
 * date：2015/8/21-15:17
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public abstract  class AbstractProxyInvoker<T> implements Invoker<T> {

    private final T proxy;

    private final Class<T> type;

    public AbstractProxyInvoker(T proxy, Class<T> type) {
        this.proxy = proxy;
        this.type = type;
    }

    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        try {
            return new  RpcResult(doInvoke(proxy, invocation.getMethodName(), invocation.getParameterTypes(),invocation.getArguments()));
        } catch (Throwable throwable) {
            throw new RpcException("invoke error",throwable);
        }
    }

    protected abstract Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable;


}
