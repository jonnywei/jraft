package com.github.jraft.remoting.exchange;

import com.github.jraft.remoting.RemotingException;

/**
 * 信息交换层，封装请求响应模式，同步转异步，以Request, Response为中心，
 * 扩展接口为Exchanger,   ExchangeClient, ExchangeServer
 * date：2015/8/31-15:25
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Exchanger {

    /**
     * bind
     * @param host
     * @param port
     * @return
     */
    ExchangeServer bind(String host, int port) throws RemotingException;


    /**
     * connect
     * @param host
     * @param port
     * @return
     */
    ExchangeClient connect(String host, int port) throws RemotingException;
}
