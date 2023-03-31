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
        if (userRepository.existsUserByEmail(dto.getEmail())) {
            throw new IllegalArgumentException();
        } else {
            userRepository.save(new User(dto.getNickname(), dto.getPassword(), dto.getEmail()));
            return ResponseEntity.ok("Success");
        }
    }

    public ResponseEntity<LoginResDto> login(LoginReqDto dto) {
        User user = userRepository.findUserByEmailAndPassword(dto.getEmailId(), dto.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("이메일과 패스워드가 존재하지 않습니다."));
        return ResponseEntity.ok(User.createLoginResDto(user));
    }

    public ResponseEntity<DuplicatedResDto> duplicatedEmailCheck(DuplicatedEmailReqDto dto) {
        return ResponseEntity.ok(new DuplicatedResDto(userRepository.existsUserByEmail(dto.getEmailId())));
    }

    public ResponseEntity<DuplicatedResDto> duplicatedNicknameCheck(DuplicatedNickReqDto dto) {
        return ResponseEntity.ok(new DuplicatedResDto(userRepository.existsUserByNickname(dto.getNickname())));
    }
}
