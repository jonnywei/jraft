package com.github.jraft.rpc.protocol;

import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.Result;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO 这里写注释
 * date：2015/8/21-15:32
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class InvokerInvocationHandler implements InvocationHandler {

    private Invoker<?> invoker;

    public InvokerInvocationHandler(Invoker<?> invoker) {
        this.invoker = invoker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Result result =  invoker.invoke(new RpcInvocation(method,args));
        return result.recreate();
    }
}
