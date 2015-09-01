package com.github.jraft.rpc;

import java.util.Map;

/**

 * TODO 这里写注释
 * date：2015/8/14-17:11
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
 */

public interface Invocation {

    /**
     * 调用的方法名字
     * @return
     */
    String getMethodName();

    Class<?>[] getParameterTypes();

    Object[] getArguments();

    Map<String, String > getAttachments();

    String getInterfaceName();

    /**
     * 得到当前的调用者
     * @return
     */
    Invoker<?> getInvoker();
}
