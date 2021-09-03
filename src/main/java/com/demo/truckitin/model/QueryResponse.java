package com.demo.truckitin.model;

public class QueryResponse {

    private String postfix;
    private double result;

    public QueryResponse(){}

    public QueryResponse(String postfix, double result) {
        this.postfix = postfix;
        this.result = result;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getPostfix() {
        return postfix;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "QueryResponse{" +
                "postfix='" + postfix + '\'' +
                ", result=" + result +
                '}';
    }
}
