package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.domain.User;
import com.e1i4.sanmonkeybackend.dto.*;
import com.e1i4.sanmonkeybackend.exception.ErrorCode;
import com.e1i4.sanmonkeybackend.exception.RequestException;
import com.e1i4.sanmonkeybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<GlobalResDto> signUp(SignupReqDto dto) {
        if (userRepository.existsUserByEmail(dto.getEmail())) {
            throw new RequestException(ErrorCode.EXISTS_USER_BY_EMAIL);
        } else {
            userRepository.save(new User(dto.getUserId(), dto.getNickname(), dto.getPassword(), dto.getEmail()));
            return ResponseEntity.ok(new GlobalResDto("Success"));
        }
    }

    public ResponseEntity<LoginResDto> login(LoginReqDto dto) {
        User user = userRepository.findUserByEmailAndPassword(dto.getEmailId(), dto.getPassword())
                .orElseThrow(() -> new RequestException(ErrorCode.LOGIN_INVALID_VALUE));
        return ResponseEntity.ok(User.createLoginResDto(user));
    }

    public ResponseEntity<GlobalResDto> availableIdCheck(IdAvailableReqDto dto) {
        if(userRepository.existsUserByUserId(dto.getUserId())) {
            return ResponseEntity.ok(new GlobalResDto("사용중인 아이디입니다."));
        } else {
            return ResponseEntity.ok(new GlobalResDto("사용가능한 아이디입니다."));
        }
    }

    public ResponseEntity<DuplicatedResDto> duplicatedEmailCheck(DuplicatedEmailReqDto dto) {
        return ResponseEntity.ok(new DuplicatedResDto(userRepository.existsUserByEmail(dto.getEmailId())));
    }

    public ResponseEntity<DuplicatedResDto> duplicatedNicknameCheck(DuplicatedNickReqDto dto) {
        return ResponseEntity.ok(new DuplicatedResDto(userRepository.existsUserByNickname(dto.getNickname())));
    }

    public ResponseEntity<FindUserIdResDto> findUserId(FindUserIdReqDto dto) {
        User user = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(() -> new RequestException(ErrorCode.USERID_NOT_FOUND));
        return ResponseEntity.ok(user.createFindUserIdResDto());
    }
}
