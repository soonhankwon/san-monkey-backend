package com.e1i4.sanmonkeybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupReqDto {
    private String userId;
    private String password;
    private String nickname;
    private String email;
}
