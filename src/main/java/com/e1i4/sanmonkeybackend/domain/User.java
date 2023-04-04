package com.e1i4.sanmonkeybackend.domain;

import com.e1i4.sanmonkeybackend.dto.LoginResDto;
import com.e1i4.sanmonkeybackend.exception.ErrorCode;
import com.e1i4.sanmonkeybackend.exception.RequestException;
import com.e1i4.sanmonkeybackend.utils.BaseTimeEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

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
        if (!isEmailWithAtAndDot(email)) {
            throw new RequestException(ErrorCode.EMAIL_WITH_INVALID_EXPRESSION);
        }
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public static LoginResDto createLoginResDto(User user) {
        return new LoginResDto(user.id, user.email, user.profileImageUrl);
    }

    private boolean isEmailWithAtAndDot(String email) {
        return email.contains("@") && email.contains(".");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nickname, user.nickname) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(profileImageUrl, user.profileImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, email, profileImageUrl);
    }
}
