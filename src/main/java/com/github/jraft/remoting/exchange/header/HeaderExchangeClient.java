package com.github.jraft.remoting.exchange.header;

import com.github.jraft.remoting.Client;
import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.exchange.DefaultFuture;
import com.github.jraft.remoting.exchange.ExchangeClient;
import com.github.jraft.remoting.exchange.Request;
import com.github.jraft.remoting.exchange.ResponseFuture;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:28
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class HeaderExchangeClient implements ExchangeClient {


    protected static final int DEFAULT_TIMEOUT = 1000;


    final Client client;


    private volatile boolean    closed      = false;


    public HeaderExchangeClient(Client client) {

        this.client = client;
    }

    @Override
    public void reconnect() throws RemotingException {
        client.reconnect();
    }

    @Override
    public void send(Object message) throws RemotingException {
        client.send(message);
    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        client.send(message, sent);
    }

    @Override
    public void close() {
        closed = true;
        client.close();
    }

    @Override
    public void close(int timeout) {
        client.close(timeout);
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public ResponseFuture request(Object request) throws RemotingException {
        return request(request,DEFAULT_TIMEOUT);
    }

    @Override
    public ResponseFuture request(Object request, int timeout) throws RemotingException {
        Request req = new Request();
        req.setData(request);
        DefaultFuture future = new DefaultFuture(req,timeout);
        client.send(req);
        return future;
    }
}
