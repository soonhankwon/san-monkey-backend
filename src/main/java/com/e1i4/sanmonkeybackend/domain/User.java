package com.e1i4.sanmonkeybackend.domain;

import com.e1i4.sanmonkeybackend.dto.FindUserIdResDto;
import com.e1i4.sanmonkeybackend.dto.LoginResDto;
import com.e1i4.sanmonkeybackend.dto.UpdateUserPasswordReqDto;
import com.e1i4.sanmonkeybackend.exception.ErrorCode;
import com.e1i4.sanmonkeybackend.exception.RequestException;
import com.e1i4.sanmonkeybackend.utils.BaseTimeEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String profileImageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<UserStamp> userStamps = new ArrayList<>();

    public User(String userId, String nickname, String password, String email) {
        if (!isEmailWithAtAndDot(email)) {
            throw new RequestException(ErrorCode.EMAIL_WITH_INVALID_EXPRESSION);
        }
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public static LoginResDto createLoginResDto(User user) {
        return new LoginResDto(user.id, user.userId, user.profileImageUrl, user.email, user.nickname);
    }

    public FindUserIdResDto createFindUserIdResDto() {
        return new FindUserIdResDto(userId, super.getCreatedAt());
    }

    public void updatePassword(UpdateUserPasswordReqDto dto) {
        if(this.password.equals(dto.getPassword())) {
            throw new RequestException(ErrorCode.SAME_PASSWORD);
        }
        this.password = dto.getPassword();
    }

    public void updateStamp(UserStamp userStamp) {
        this.userStamps.add(userStamp);
    }

    private boolean isEmailWithAtAndDot(String email) {
        return email.contains("@") && email.contains(".");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(nickname, user.nickname) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(profileImageUrl, user.profileImageUrl) && Objects.equals(userStamps, user.userStamps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nickname, password, email, profileImageUrl, userStamps);
    }
}
