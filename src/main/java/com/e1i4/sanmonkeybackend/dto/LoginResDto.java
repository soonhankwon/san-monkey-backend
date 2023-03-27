package com.e1i4.sanmonkeybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResDto {
    private Long userId;
    private String emailId;
    private String accessToken;
    private String profileImageUrl;

    public LoginResDto (Long userId, String emailId, String profileImageUrl) {
        this.userId = userId;
        this.emailId = emailId;
        this.profileImageUrl = profileImageUrl;
    }
}
