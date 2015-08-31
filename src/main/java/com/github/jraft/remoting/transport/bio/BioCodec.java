package com.github.jraft.remoting.transport.bio;

import com.github.jraft.remoting.Codec;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * TODO 这里写注释
 * date：2015/8/31-18:31
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public class BioCodec implements Codec {
    @Override
    public void encode(OutputStream output, Object message) {
        
    }

    @Override
    public Object decode(InputStream inputStream) {
        return null;
    }
}
