package com.e1i4.sanmonkeybackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMAIL_WITH_INVALID_EXPRESSION(HttpStatus.BAD_REQUEST, "이메일은 @와 .이 포함되어야 합니다."),
    PASSWORD_INVALID(HttpStatus.BAD_REQUEST, "잘못된 패스워드 입니다."),
    LOGIN_INVALID_VALUE(HttpStatus.BAD_REQUEST, "이메일 또는 패스워드가 일치하지 않습니다."),
    EXISTS_USER_BY_EMAIL(HttpStatus.BAD_REQUEST, "가입된 이메일이 존재합니다."),
    USERID_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다."),
    SAME_PASSWORD(HttpStatus.BAD_REQUEST, "기존 비밀번호와 동일한 비밀번호 입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
