package com.github.jraft.remoting.exchange;

import com.github.jraft.remoting.RemotingException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 这里写注释
 * date：2015/8/31-17:21
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class DefaultFuture implements ResponseFuture {

    private static final Map<Long, DefaultFuture> FUTURES   = new ConcurrentHashMap<Long, DefaultFuture>();

    private Request request;

    private int timeout;

    private final Lock lock = new ReentrantLock();

    private final Condition done = lock.newCondition();

    private final int DEFAULT_TIMEOUT = 10*1000;


    private volatile Response                     response;


    public DefaultFuture( Request request, int timeout) {
        this.request = request;
        this.timeout = timeout;
        FUTURES.put(request.getId(), this);
    }

    @Override
    public Object get() throws RemotingException {
        return get(DEFAULT_TIMEOUT);
    }

    @Override
    public Object get(int timeoutInMillis) throws RemotingException {
        lock.lock();
        try {
            while (! isDone()){
                done.await(timeoutInMillis, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return response.getResult();
    }

    @Override
    public boolean isDone() {
        return response != null;
    }


    private void doReceived(Response response){
        this.response = response;
    }

    public static void received( Response response) {
        DefaultFuture future = FUTURES.remove(response.getId());
        if(future != null){
            future.doReceived(response);
        }
    }
}
