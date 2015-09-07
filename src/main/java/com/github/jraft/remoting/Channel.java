package com.github.jraft.remoting;


/**
 * TODO 这里写注释
 * date：2015/9/7-11:01
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Channel {


    ChannelHandler getChannelHandler();


    /**
     * on message sent.
     *
     * @param message message.
     */
    void sent(Object message) throws RemotingException;

    /**
     * on message received.
     * @param message message.
     */
    void received( Object message) throws RemotingException;

}
