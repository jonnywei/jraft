package com.github.jraft.rpc;

import java.util.Map;

/**
 * TODO ����дע��
 * date��2015/8/14-17:11
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public interface Invocation {

    /**
     * ���õķ�������
     * @return
     */
    String getMethodName();

    Class<?>[] getParameterTypes();

    Object[] getArguments();

    Map<String, String > getAttachments();

    /**
     * �õ���ǰ�ĵ�����
     * @return
     */
    Invoker<?> getInvoker();
}
