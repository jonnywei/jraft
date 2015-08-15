package com.github.jraft.rpc;

/**
 * TODO 这里写注释
 * date：2015/8/14-17:17
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Result {


    /**
     * 得到调用的结果
     * @return
     */
    Object getValue();


    /**
     * 得到调用的异常
     * @return
     */
    Throwable getException();


    /**
     * 是否有异常情况
     * @return
     */
    boolean hasException();

}
