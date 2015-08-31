package com.github.jraft.remoting.exchange;

import com.github.jraft.remoting.RemotingException;

/**
 * TODO 这里写注释
 * date：2015/8/31-15:47
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface ResponseFuture {



    /**
     * get result.
     *
     * @return result.
     */
    Object get() throws RemotingException;

    /**
     * get result with the sp
     * ecified timeout.
     *
     * @param timeoutInMillis timeout.
     * @return result.
     */
    Object get(int timeoutInMillis) throws RemotingException;



    /**
     * check is done.
     *
     * @return done or not.
     */
    boolean isDone();



}
