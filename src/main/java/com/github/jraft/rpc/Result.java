package com.github.jraft.rpc;

/**
 * TODO ����дע��
 * date��2015/8/14-17:17
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public interface Result {


    /**
     * �õ����õĽ��
     * @return
     */
    Object getValue();


    /**
     * �õ����õ��쳣
     * @return
     */
    Throwable getException();


    /**
     * �Ƿ����쳣���
     * @return
     */
    boolean hasException();

}
