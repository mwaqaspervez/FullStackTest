package com.demo.assessment.config;

public class BadArgumentException extends Exception{

    public BadArgumentException(){
        super();
    }

    public BadArgumentException(String message) {
        super(message);
    }
}
