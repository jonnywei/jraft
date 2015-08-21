package com.github.jraft.rpc.protocol.yi;

import com.github.jraft.rpc.Exporter;
import com.github.jraft.rpc.Invoker;

/**
 * TODO 这里写注释
 * date：2015/8/21-14:19
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class YiExporter <T> implements Exporter<T> {

    private Invoker<T> invoker;

    public  YiExporter(Invoker<T> invoker){
        this.invoker = invoker;
    }
    @Override
    public Invoker<T> getInvoker() {
        return invoker;
    }

    @Override
    public void unexport() {

    }
}
