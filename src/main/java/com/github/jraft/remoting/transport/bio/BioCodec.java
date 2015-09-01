package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.Codec;
import com.github.jraft.remoting.exchange.Request;
import com.github.jraft.remoting.exchange.Response;

import java.io.*;

/**
 * TODO 这里写注释
 * date：2015/8/31-18:31
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioCodec implements Codec {
    @Override
    public void encode(OutputStream output, Object message) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(output);
        if(message instanceof Request){
            oos.writeObject(message);
        }else if(message instanceof Response){
            oos.writeObject(message);
        }
        oos.flush();
    }

    @Override
    public Object decode(InputStream input )throws IOException {

        ObjectInputStream ois = new ObjectInputStream(input);
        Object result;
        try {
             result = ois.readObject();
        } catch (ClassNotFoundException e) {
            throw  new IOException("decode error", e);
        }
        if(result instanceof Request){
            return (Request) result;
        }
        else {
            return (Response) result;

        }

    }
}
