package com.github.jraft.remoting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TODO 这里写注释
 * date：2015/8/31-18:25
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Codec {


    /**
     * 编码
     * @param output
     * @param message
     */
    void encode( OutputStream output, Object message) throws IOException;

    /**
     * 解码
     * @param inputStream
     * @return
     */
    Object decode(InputStream inputStream) throws IOException;
}
