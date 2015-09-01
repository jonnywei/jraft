package com.github.jraft.remoting;

/**
 * client 接口， exchange 和 transporter公用
 * date：2015/8/31-15:09
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Client {

    /**
     * reconnect.
     */
    void reconnect() throws RemotingException;



    /**
     * send message.
     *
     * @param message
     * @throws RemotingException
     */
    void send(Object message) throws RemotingException;

    /**
     * send message.
     *
     * @param message
     * @param sent 是否已经发送完成 。false，只放到io缓冲区即可
     */
    void send(Object message, boolean sent) throws RemotingException;


    /**
     * close the channel.
     */
    void close();

    /**
     * Graceful close the channel.
     */
    void close(int timeout);

    /**
     * is closed.
     *
     * @return closed
     */
    boolean isClosed();
}
