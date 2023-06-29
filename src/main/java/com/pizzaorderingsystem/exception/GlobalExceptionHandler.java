package com.pizzaorderingsystem.exception;

import com.pizzaorderingsystem.contants.Constants;
import com.pizzaorderingsystem.reponse.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ApiResponse> handleNoSuchElementException(NoSuchElementException ex) {
        log.warn("EXCEPTION of Type: {} , with MESSAGE: {}",ex.getClass().getSimpleName(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, Constants.DATA_NOT_FOUND,new ArrayList<>()));
    }
}
