package com.github.jraft.remoting.exchange.header;

import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.exchange.ExchangeClient;
import com.github.jraft.remoting.exchange.ExchangeHandler;
import com.github.jraft.remoting.exchange.ExchangeServer;
import com.github.jraft.remoting.exchange.Exchanger;
import com.github.jraft.remoting.transport.bio.BioTransporter;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:27
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class HeadExchanger implements Exchanger {
    @Override
    public ExchangeServer bind(String host, int port , ExchangeHandler handler) throws RemotingException {
        return new HeaderExchangeServer((new BioTransporter()).bind(host,port,handler));
    }

    @Override
    public ExchangeClient connect(String host, int port, ExchangeHandler handler) throws RemotingException {
        return  new HeaderExchangeClient((new BioTransporter()).connect(host, port,handler));
    }
}
