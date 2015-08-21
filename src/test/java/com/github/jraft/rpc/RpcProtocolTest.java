package com.github.jraft.rpc;

import com.github.jraft.simple.ServiceConfig;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 这里写注释
 * date：2015/8/19-15:56
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class RpcProtocolTest {

    public static void main(String[] args) throws Exception{
        HelloWordServiceImpl helloWordService = new HelloWordServiceImpl();
        ServiceConfig<HelloWorldService> serviceConfig = new ServiceConfig<HelloWorldService>(HelloWorldService.class, helloWordService);
        serviceConfig.export();
    }

}


