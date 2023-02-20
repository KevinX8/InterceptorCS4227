package com.paulis.gributs.interceptor.cs4227.interception;

import java.util.ArrayList;

public class Dispatcher {
    private static Dispatcher instance;
    ArrayList<Interceptor> interceptors =  new ArrayList<>();

    public static Dispatcher getInstance() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

    public void dispatch(Context context) {
        for (Interceptor interceptor : interceptors) {
            interceptor.beforeAction(context);
            context.proceed();
            interceptor.afterAction(context);
        }
    }

    public void register(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
}
