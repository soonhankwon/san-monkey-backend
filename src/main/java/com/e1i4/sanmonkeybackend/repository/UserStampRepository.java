package com.e1i4.sanmonkeybackend.repository;

import com.e1i4.sanmonkeybackend.domain.User;
import com.e1i4.sanmonkeybackend.domain.UserStamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStampRepository extends JpaRepository<UserStamp, Long> {
    List<UserStamp> findAllByUser(User user);
}
