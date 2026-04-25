package com.univille.api.shopplusai.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        var responseError = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }
}
