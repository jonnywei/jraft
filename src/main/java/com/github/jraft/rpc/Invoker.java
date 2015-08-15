package com.github.jraft.rpc;

/**
 * TODO 这里写注释
 * date：2015/8/14-17:15
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Invoker<T> {


    /**
     * 得到服务的接口
     * @return
     */
    Class<T> getInterface();


    /**
     * 调用
     */
    Result invoke(Invocation invocation) ;
}
