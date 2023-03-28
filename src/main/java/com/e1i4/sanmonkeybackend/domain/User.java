package com.e1i4.sanmonkeybackend.domain;

import com.e1i4.sanmonkeybackend.dto.LoginReqDto;
import com.e1i4.sanmonkeybackend.dto.LoginResDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String profileImageUrl;

    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public static LoginResDto createLoginResDto (User user) {
         return new LoginResDto(user.id, user.email, user.profileImageUrl);
    }
}
