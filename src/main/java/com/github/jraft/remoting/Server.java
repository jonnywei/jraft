package com.github.jraft.remoting;

/**
 * server interface
 * 服务器抽象接口
 * date：2015/8/31-14:49
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Server {


    /**
     * is bound.
     *
     * @return bound
     */
    boolean isBound();



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
     * @param sent 是否已发送完成
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
