package com.demo.truckitin.model;

public class QueryRequest {

    private String equation;

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    @Override
    public String toString() {
        return "QueryRequest{" +
                "equation='" + equation + '\'' +
                '}';
    }
}
