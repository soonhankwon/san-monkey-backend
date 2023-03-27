package com.e1i4.sanmonkeybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginReqDto {
    private String emailId;
    private String password;
}
