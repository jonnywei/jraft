package com.github.jraft.remoting.exchange;

import com.github.jraft.remoting.RemotingException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 这里写注释
 * date：2015/8/31-17:21
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class DefaultFuture implements ResponseFuture {

    private static final Map<Long, DefaultFuture> FUTURES   = new ConcurrentHashMap<Long, DefaultFuture>();

    private Request request;

    private int timeout;


    public DefaultFuture( Request request, int timeout) {
        this.request = request;
        this.timeout = timeout;
        FUTURES.put(request.getId(), this);
    }

    @Override
    public Object get() throws RemotingException {
        return null;
    }

    @Override
    public Object get(int timeoutInMillis) throws RemotingException {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
