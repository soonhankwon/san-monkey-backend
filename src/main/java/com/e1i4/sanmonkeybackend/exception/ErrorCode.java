package com.e1i4.sanmonkeybackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMAIL_WITH_INVALID_EXPRESSION(HttpStatus.BAD_REQUEST, "이메일은 @와 .이 포함되어야 합니다."),
    PASSWORD_INVALID(HttpStatus.BAD_REQUEST, "잘못된 패스워드 입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
