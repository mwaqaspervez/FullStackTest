package com.demo.truckitin.advice;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GeneralExceptionalHandler {

    @ExceptionHandler(value = UnsupportedOperationException.class)
    public ResponseEntity<Object> handleUnprocessedMsgException(UnsupportedOperationException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

}