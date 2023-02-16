package com.paulis.gributs.interceptor.cs4227.interception;

public interface Interceptor {
    void beforeAction(Context context);
    void afterAction(Context context);
}
