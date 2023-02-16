package com.paulis.gributs.interceptor.cs4227.interception;

public class AdvertisingInterceptor implements Interceptor {
    @Override
    public void beforeAction(Context context) {
        if (context.getRequest().contains("setPriceCode")) {
            System.out.println("Thank you for purchasing from our store!");
        }
    }

    @Override
    public void afterAction(Context context) {
        if (context.getResponse().contains("setPriceCode")) {
            System.out.println("Here is a 20% discount code for your next visit: 20OFF");
        }
    }
}
