package com.e1i4.sanmonkeybackend.repository;

import com.e1i4.sanmonkeybackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    boolean existsUserByPasswordAndEmail(String password, String email);
    User findUserByEmail(String email);
}
