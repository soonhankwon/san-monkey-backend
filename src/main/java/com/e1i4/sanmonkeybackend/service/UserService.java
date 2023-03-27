package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.domain.User;
import com.e1i4.sanmonkeybackend.dto.*;
import com.e1i4.sanmonkeybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<?> signUp(SignupReqDto dto) {
        if (!userRepository.existsUserByEmail(dto.getEmail())) {
            userRepository.save(new User(dto.getNickname(), dto.getPassword(), dto.getEmail()));
            return ResponseEntity.status(200).body("Success");
        } else {
            throw new RuntimeException();
        }
    }

    public ResponseEntity<LoginResDto> login(LoginReqDto dto) {
        if (!isExistsUserByPasswordAndEmail(dto)) {
            throw new IllegalArgumentException("이메일과 패스워드가 존재하지 않습니다.");
        } else {
            User user = userRepository.findUserByEmail(dto.getEmailId());
            return ResponseEntity.ok(new LoginResDto(user.getId(), user.getEmail(), user.getProfileImageUrl()));
        }
    }

    public ResponseEntity<Boolean> duplicatedEmailCheck(DuplicatedCheckReqDto dto) {
        return ResponseEntity.ok(userRepository.existsUserByEmail(dto.getEmailId()));
    }

    private boolean isExistsUserByPasswordAndEmail(LoginReqDto dto) {
        return userRepository.existsUserByPasswordAndEmail(dto.getPassword(), dto.getEmailId());
    }
}
