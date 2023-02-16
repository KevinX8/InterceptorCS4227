package com.paulis.gributs.interceptor.cs4227.interception;

import com.paulis.gributs.interceptor.cs4227.Customer;
import com.paulis.gributs.interceptor.cs4227.Movie;
import com.paulis.gributs.interceptor.cs4227.Rental;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InterceptorTest {
    private Customer customer;
    private Dispatcher dispatcher;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @BeforeEach
    public void setup() {
        dispatcher = new Dispatcher();
        customer = new Customer("iJoshie", dispatcher);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void interceptionTest() {
        Movie movie = new Movie("Bad Teacher", 0, dispatcher);
        assertEquals("", outContent.toString());

        customer.addRental(new Rental(movie, 7));
        assertEquals("", outContent.toString());

        Interceptor logger = new LoggingInterceptor();
        Interceptor advertiser = new AdvertisingInterceptor();
        dispatcher.register(advertiser);
        dispatcher.register(logger);

        Movie movie2 = new Movie("Devil wears Prada", 1, dispatcher);
        String prevOut = outContent.toString().replaceAll("\\r\\n?", "\n");
        assertEquals("""
                Thank you for purchasing from our store!
                Here is a 20% discount code for your next visit: 20OFF
                Before processing request: setPriceCode
                After processing request: Response for setPriceCode
                """, prevOut);

        customer.addRental(new Rental(movie2, 14));
        assertEquals(prevOut + """
                Before processing request: addRental
                After processing request: Response for addRental
                """, outContent.toString().replaceAll("\\r\\n?", "\n"));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}