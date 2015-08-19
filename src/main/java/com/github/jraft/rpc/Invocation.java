package com.github.jraft.rpc;

import java.util.Map;

/**
<<<<<<< HEAD
 * TODO ????????
 * date??2015/8/14-17:11
 * author??weijianjun
 * Copyright (c) 2014????????-???????
=======
 * TODO 这里写注释
 * date：2015/8/14-17:11
 * author：weijianjun
 * Copyright (c) 2014畅游天下-版权所有
>>>>>>> 99a63c21e497034b068955706001e77a140c228b
 */

public interface Invocation {

    /**
<<<<<<< HEAD
     * ???�????????
=======
     * 调用的方法名字
>>>>>>> 99a63c21e497034b068955706001e77a140c228b
     * @return
     */
    String getMethodName();

    Class<?>[] getParameterTypes();

    Object[] getArguments();

    Map<String, String > getAttachments();

    /**
<<<<<<< HEAD
     * ?�???????????
=======
     * 得到当前的调用者
>>>>>>> 99a63c21e497034b068955706001e77a140c228b
     * @return
     */
    Invoker<?> getInvoker();
}
