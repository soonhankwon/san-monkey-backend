package com.e1i4.sanmonkeybackend.dto;

import com.e1i4.sanmonkeybackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResDto {
    private Long userId;
    private String emailId;
    private String accessToken;
    private String profileImageUrl;

    public LoginResDto (User user) {
        this.userId = user.getId();
        this.emailId = user.getEmail();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
