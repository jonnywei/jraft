package com.github.jraft.rpc;

/**
 * TODO 这里写注释
 * date：2015/8/21-11:40
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Protocol {


    /**
     * 服务的默认端口
     * @return
     */
    int getDefaultPort();

    <T> Exporter<T>  export(Invoker<T> invoker) ;


    <T> Invoker<T>  refer(Class<T> type);

}
