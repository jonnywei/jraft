package com.github.jraft.rpc;

/**
 * TODO 这里写注释
 * date：2015/8/21-11:44
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Exporter<T> {


    /**
     * 得到Invoker对象
     * @return
     */
    Invoker<T> getInvoker();

    void unexport();
}
