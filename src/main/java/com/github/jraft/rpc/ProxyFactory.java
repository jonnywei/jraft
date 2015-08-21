package com.github.jraft.rpc;

/**
 * TODO 这里写注释
 * date：2015/8/21-11:54
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface ProxyFactory {

    <T> T  getProxy(Invoker<T> invoker);


    <T> Invoker<T> getInvoker(T proxy, Class<T> type);
}
