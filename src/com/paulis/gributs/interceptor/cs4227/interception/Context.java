package com.paulis.gributs.interceptor.cs4227.interception;

public class Context {
    private String request;
    private String response;

    public Context(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    public void proceed() {
        // processing the request and setting the response
        response = "Response for " + request;
    }
}

