package com.paulis.gributs.interceptor.cs4227.interception;

public class LoggingInterceptor implements Interceptor {

    @Override
    public void beforeAction(Context context) {
        System.out.println("Before processing request: " + context.getRequest());
    }

    @Override
    public void afterAction(Context context) {
        System.out.println("After processing request: " + context.getResponse());
    }
}

