package com.univille.api.shopplusai.infra.exception;

import java.util.List;

public record ErrorResponse (Integer status, String message, List<ErrorValidationResponse> details){
}
