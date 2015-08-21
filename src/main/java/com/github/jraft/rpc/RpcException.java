package com.github.jraft.rpc;

/**
 * 远程调用异常
 * date：2015/8/21-14:59
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class RpcException extends RuntimeException {

    public RpcException() {
        super();
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(Throwable cause) {
        super(cause);
    }

}
