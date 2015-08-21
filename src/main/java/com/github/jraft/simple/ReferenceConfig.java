package com.github.jraft.simple;

import com.github.jraft.rpc.Invoker;
import com.github.jraft.rpc.Protocol;
import com.github.jraft.rpc.ProxyFactory;
import com.github.jraft.rpc.protocol.JdkProxyFactory;
import com.github.jraft.rpc.protocol.yi.YiProtocol;

/**
 * TODO 这里写注释
 * date：2015/8/21-11:47
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class ReferenceConfig <T> {

    private Protocol protocol = new YiProtocol();
    private ProxyFactory proxyFactory = new JdkProxyFactory();

    private  T  ref;

    private  Class<T> interfaceClass;

   public  synchronized  T  get(){
        if(ref == null){
            init();
        }
       return  ref;
    }


    public void setInterface(Class<T> interfaceClass){
        this.interfaceClass = interfaceClass;
    }

    private  void init(){
        Invoker<T> invoker = protocol.refer(interfaceClass);
        ref = proxyFactory.getProxy(invoker);
    }
}
