package com.github.jraft.remoting.exchange;

import java.io.Serializable;

/**
 * TODO 这里写注释
 * date：2015/8/31-17:13
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class Response implements Serializable {


    private static final long serialVersionUID = 2519269405109728908L;
    private long             mId               = 0;


    private Object           mResult;

    public Response(){
    }

    public Response(long id){
        mId = id;
    }


    public Object getResult() {
        return mResult;
    }

    public void setResult(Object msg) {
        mResult = msg;
    }
}
