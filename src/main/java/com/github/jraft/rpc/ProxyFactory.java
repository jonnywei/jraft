package com.github.jraft.rpc;

/**
 * TODO ����дע��
 * date��2015/8/21-11:54
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public interface ProxyFactory {

    <T> T  getProxy(Invoker<T> invoker);


    <T> Invoker<T> getInvoker(T proxy, Class<T> type);
}
