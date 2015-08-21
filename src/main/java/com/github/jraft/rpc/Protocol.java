package com.github.jraft.rpc;

/**
 * TODO ����дע��
 * date��2015/8/21-11:40
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public interface Protocol {


    /**
     * �����Ĭ�϶˿�
     * @return
     */
    int getDefaultPort();

    <T> Exporter<T>  export(Invoker<T> invoker) ;


    <T> Invoker<T>  refer(Class<T> type);

}
