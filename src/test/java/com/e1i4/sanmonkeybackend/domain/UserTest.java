package com.e1i4.sanmonkeybackend.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    @DisplayName("유저 객체 생성 테스트")
    void createUser() {
        String nickname = "soon";
        String password = "1234";
        String email = "soon@test.com";

        User user = new User(nickname, password, email);
        assertThat(user).isEqualTo(new User("soon", "1234", "soon@test.com"));
    }

    @Test
    @DisplayName("이메일 @ && .이 포함되어 있지않을떄 예외처리 테스트")
    void userEmailWithNoAtAndDot() {
        String nickname = "soon";
        String password = "1234";
        String email = "soontestcom";

        assertThatThrownBy(() -> {
            new User(nickname, password, email);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
