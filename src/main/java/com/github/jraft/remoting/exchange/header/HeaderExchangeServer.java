package com.github.jraft.remoting.exchange.header;

import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.Server;
import com.github.jraft.remoting.exchange.ExchangeServer;

/**
 * sever的装饰器 ，包装传输层的server
 * date：2015/8/31-15:28
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class HeaderExchangeServer implements ExchangeServer {


   private  final Server server;

    public HeaderExchangeServer(Server server) {
        this.server = server;
    }

    @Override
    public boolean isBound() {
        return server.isBound();
    }

    @Override
    public void send(Object message) throws RemotingException {
        server.send(message);
    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        server.send(message, sent);
    }

    @Override
    public void close() {
        server.close();
    }

    @Override
    public void close(int timeout) {
        server.close(timeout);
    }

    @Override
    public boolean isClosed() {
        return server.isClosed();
    }
}
