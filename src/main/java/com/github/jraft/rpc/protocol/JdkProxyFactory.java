package com.github.jraft.rpc.protocol;

import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.ProxyFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO 这里写注释
 * date：2015/8/21-11:56
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class JdkProxyFactory implements ProxyFactory {
    @Override
    public <T> T getProxy(Invoker<T> invoker) {
        Class<?>[] interfaces = new Class<?>[] {invoker.getInterface()};
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),interfaces,new InvokerInvocationHandler(invoker) );
    }

    @Override
    public <T> Invoker<T> getInvoker(T proxy, Class<T> type) {
        return new  AbstractProxyInvoker<T>(proxy,type){

            @Override
            protected Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable {
                Method method = proxy.getClass().getMethod(methodName,parameterTypes);
                return method.invoke(proxy,arguments);
            }
        };
    }
}
