package com.e1i4.sanmonkeybackend.repository;

import com.e1i4.sanmonkeybackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    Optional<User> findUserByEmailAndPassword(String emailId, String password);

    boolean existsUserByNickname(String nickname);

    Optional<User> findUserByEmail(String email);

    boolean existsUserByUserId(String userId);

    Optional<User> findUserByUserId(String userId);
}
