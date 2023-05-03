package com.e1i4.sanmonkeybackend.exception;

import com.e1i4.sanmonkeybackend.dto.GlobalResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<GlobalResDto> handleRequestException(ApiException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new GlobalResDto(e.getMessage()));
    }
}
