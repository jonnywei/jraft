package com.github.jraft.rpc;

/**
 * Զ�̵����쳣
 * date��2015/8/21-14:59
 * author��weijianjun
 * Copyright (c) 2014��������-��Ȩ����
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
