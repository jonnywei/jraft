package com.github.jraft.simple;

import com.github.jraft.rpc.Exporter;
import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.Protocol;
import com.github.jraft.rpc.ProxyFactory;
import com.github.jraft.rpc.protocol.JdkProxyFactory;
import com.github.jraft.rpc.protocol.yi.YiProtocol;

/**
 * TODO ����дע��
 * date��2015/8/21-11:47
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public class ServiceConfig<T> {


    private Protocol protocol = new YiProtocol();

    private ProxyFactory proxyFactory = new JdkProxyFactory();

    private Class<?>            interfaceClass;

    private  T  ref;

    public ServiceConfig(Class<?> interfaceClass, T ref) {
        this.interfaceClass = interfaceClass;
        this.ref = ref;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    public  <T>  Exporter<T>  export(){
        Invoker<?> invoker =  proxyFactory.getInvoker(ref,  (Class) interfaceClass);
        Exporter<?> exporter = protocol.export(invoker);
        return null;
    }



}
