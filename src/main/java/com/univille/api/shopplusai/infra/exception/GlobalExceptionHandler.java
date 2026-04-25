package com.univille.api.shopplusai.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        var responseError = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        var responseError = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
    "Erro de validação dos campos",
            errors.stream().map(ErrorValidationResponse::new).collect(Collectors.toList())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
}
