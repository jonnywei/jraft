package com.github.jraft.remoting;

/**
 * io处理类
 * date：2015/9/1-11:39
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface IoHandler {


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
