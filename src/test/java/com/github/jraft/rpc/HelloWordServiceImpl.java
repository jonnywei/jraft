package com.github.jraft.rpc;

public class HelloWordServiceImpl implements  HelloWorldService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }


}