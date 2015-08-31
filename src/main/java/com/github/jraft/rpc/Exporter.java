package com.github.jraft.rpc;

/**
 * TODO ����дע��
 * date��2015/8/21-11:44
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public interface Exporter<T> {


    /**
     * �õ�Invoker����
     * @return
     */
    Invoker<T> getInvoker();

    void unexport();
}
