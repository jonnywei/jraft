package com.github.jraft.remoting.exchange;

import com.github.jraft.remoting.IoHandler;
import com.github.jraft.remoting.RemotingException;

/**
 * TODO 这里写注释
 * date：2015/9/1-16:09
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface ExchangeHandler  extends IoHandler {

    Object reply(Object request) throws RemotingException;
}
