package com.paulis.gributs.interceptor.cs4227;

import com.paulis.gributs.interceptor.cs4227.interception.AdvertisingInterceptor;
import com.paulis.gributs.interceptor.cs4227.interception.Dispatcher;
import com.paulis.gributs.interceptor.cs4227.interception.Interceptor;
import com.paulis.gributs.interceptor.cs4227.interception.LoggingInterceptor;

public class Main {
    public static void main(String[] args) {
        Dispatcher dispatcher = Dispatcher.getInstance();
        Movie movie = new Movie("Bad Teacher", 0);
        Customer customer = new Customer("iJoshie");
        customer.addRental(new Rental(movie, 7));

        Interceptor logger = new LoggingInterceptor();
        Interceptor advertiser = new AdvertisingInterceptor();
        dispatcher.register(advertiser);
        dispatcher.register(logger);
        Movie movie2 = new Movie("Devil wears Prada", 1);
        customer.addRental(new Rental(movie2, 14));
    }
}