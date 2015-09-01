package com.github.jraft.rpc.protocol;

import com.github.jraft.rpc.Invocation;
import com.github.jraft.rpc.Invoker;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * date 2015/8/21-14:30
 * author weijianjun
 * Copyright (c) 2014
 */

public class RpcInvocation implements Invocation, Serializable {

    private String               methodName;

    private Class<?>[]           parameterTypes;

    private Object[]             arguments;

    private String               interfaceName;

    public RpcInvocation(Method method, Object[] arguments,String interfaceName) {
        this(method.getName(), method.getParameterTypes(), arguments, interfaceName);
    }

    public RpcInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments,String interfaceName){
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;
        this.interfaceName = interfaceName;
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

    @Override
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
