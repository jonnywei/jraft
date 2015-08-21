package com.github.jraft.rpc;

import com.github.jraft.simple.ReferenceConfig;

import java.io.Serializable;

/**
 * TODO ����дע��
 * date��2015/8/19-16:32
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
 */

public class RpcClientTest implements Serializable {

    public static void main(String[] args) throws Exception{


        ReferenceConfig<HelloWorldService>  referenceConfig = new ReferenceConfig<HelloWorldService>();
        referenceConfig.setInterface(HelloWorldService.class);

        HelloWorldService hws = referenceConfig.get();
        for(int i= 1; i < 100; i++){
            System.out.println(hws.sayHello("jonny"));
        }

    }
}
