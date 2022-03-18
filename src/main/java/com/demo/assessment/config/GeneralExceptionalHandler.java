package com.demo.assessment.config;

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

    @ExceptionHandler(value = BadArgumentException.class)
    public ResponseEntity<Object> handleUnprocessedMsgException(BadArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleUnprocessedMsgException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

}