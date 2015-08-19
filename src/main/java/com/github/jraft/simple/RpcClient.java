package com.github.jraft.simple;

import java.io.Serializable;

/**
 * TODO 这里写注释
 * date：2015/8/19-16:32
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class RpcClient implements Serializable {

    public static void main(String[] args) throws Exception{
        HelloWorldService hws = RpcProtocol.refer(HelloWorldService.class, "localhost", 4567);
        HelloWorldService2 hws2 = RpcProtocol.refer(HelloWorldService2.class, "localhost", 4567);
        for(int i= 1; i < 100; i++){
            System.out.println(hws.sayHello("jonny"));
            System.out.println(hws2.sayObject(new RpcClient(), i ));
        }

    }
}
