package com.e1i4.sanmonkeybackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class Error {
    private HttpStatus httpStatus;
    private String message;
}
