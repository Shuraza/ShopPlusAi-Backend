package com.univille.api.shopplusai.infra.exception;

import org.springframework.validation.FieldError;

public record ErrorValidationResponse(String campo, String message) {
    public ErrorValidationResponse(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
