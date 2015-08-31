package com.github.jraft.remoting.exchange;

import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO 这里写注释
 * date：2015/8/31-17:12
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class Request {


    private  static final AtomicLong ATOMIC_LONG = new AtomicLong(0);

    private final Long mId;

    private Object mData;


    public Request() {
        mId = ATOMIC_LONG.incrementAndGet();
    }

    public long getId() {
        return mId;
    }

    public Object getData() {
        return mData;
    }

    public void setData(Object msg) {
        mData = msg;
    }




}
