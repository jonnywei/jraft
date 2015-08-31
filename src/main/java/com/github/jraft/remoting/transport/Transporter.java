package com.github.jraft.remoting.transport;

import com.github.jraft.remoting.Client;
import com.github.jraft.remoting.RemotingException;
import com.github.jraft.remoting.Server;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:36
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Transporter {


    /**
     * bind
     * @param host
     * @param port
     * @return
     */
    Server bind(String host, int port)  throws RemotingException;


    /**
     * connect
     * @param host
     * @param port
     * @return
     */
    Client connect(String host, int port)  throws RemotingException;
}
