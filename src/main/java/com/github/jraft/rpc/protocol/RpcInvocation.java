package com.github.jraft.rpc.protocol;

import com.github.jraft.rpc.Invocation;
import com.github.jraft.rpc.Invoker;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ���кŶ���
 * date��2015/8/21-14:30
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public class RpcInvocation implements Invocation, Serializable {

    private String               methodName;

    private Class<?>[]           parameterTypes;

    private Object[]             arguments;


    public RpcInvocation(Method method, Object[] arguments) {
        this(method.getName(), method.getParameterTypes(), arguments);
    }

    public RpcInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments){
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;

    }
    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Map<String, String> getAttachments() {
        return null;
    }

    @Override
    public Invoker<?> getInvoker() {
        return null;
    }
}
