package com.github.jraft.rpc.protocol;

import com.github.jraft.rpc.Result;

import java.io.Serializable;

/**
 * TODO 这里写注释
 * date：2015/8/21-15:09
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class RpcResult implements Result,Serializable {

    private static final long serialVersionUID = -5955341258505441467L;

    private Object                   result;

    private Throwable                exception;

    public RpcResult(){
    }

    public RpcResult(Object result){
        this.result = result;
    }

    public RpcResult(Throwable exception){
        this.exception = exception;
    }


    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public boolean hasException() {
        if(exception != null){
            return true;
        }
        return false;
    }


    public Object recreate() throws Throwable {
        if (exception != null) {
            throw exception;
        }
        return result;
    }
}
