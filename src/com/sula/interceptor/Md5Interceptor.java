package com.sula.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * md5签名拦截器
 */
public abstract class Md5Interceptor implements Interceptor {
    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
