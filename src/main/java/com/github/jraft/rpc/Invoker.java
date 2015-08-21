package com.github.jraft.rpc;

/**
 * TODO ����дע��
 * date��2015/8/14-17:15
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public interface Invoker<T> {


    /**
     * �õ�����Ľӿ�
     * @return
     */
    Class<T> getInterface();


    /**
     * ����
     */
    Result invoke(Invocation invocation)  throws RpcException;
}
