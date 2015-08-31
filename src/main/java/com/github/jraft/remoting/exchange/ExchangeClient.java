package com.github.jraft.remoting.exchange;

import com.github.jraft.remoting.Client;
import com.github.jraft.remoting.RemotingException;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:16
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface ExchangeClient extends Client {

    /**
     * send request.
     *
     * @param request
     * @return response future
     * @throws RemotingException
     */
    ResponseFuture request(Object request) throws RemotingException;

    /**
     * send request.
     *
     * @param request
     * @param timeout
     * @return response future
     * @throws RemotingException
     */
    ResponseFuture request(Object request, int timeout) throws RemotingException;
}
