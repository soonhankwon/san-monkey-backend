package com.e1i4.sanmonkeybackend.dto;

import lombok.Getter;

@Getter
public class LoginResDto {
    private final Long userId;
    private final String emailId;
    private String accessToken;
    private final String profileImageUrl;

    public LoginResDto(Long userId, String email, String profileImageUrl) {
        this.userId = userId;
        this.emailId = email;
        this.profileImageUrl = profileImageUrl;
    }
}
