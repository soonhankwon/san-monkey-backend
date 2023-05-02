package com.e1i4.sanmonkeybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResDto {
    private String accessToken;
    private final Long id;
    private final String userId;
    private final String profileImageUrl;
    private final String email;
    private final String nickname;

    public LoginResDto(Long id, String userId, String profileImageUrl, String email, String nickname) {
        this.id = id;
        this.userId = userId;
        this.profileImageUrl = profileImageUrl;
        this.email = email;
        this.nickname = nickname;
    }
}
