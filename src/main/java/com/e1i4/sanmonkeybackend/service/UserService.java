package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.domain.User;
import com.e1i4.sanmonkeybackend.dto.*;
import com.e1i4.sanmonkeybackend.exception.ApiException;
import com.e1i4.sanmonkeybackend.exception.ErrorCode;
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
            throw new ApiException(ErrorCode.EXISTS_USER_BY_EMAIL);
        } else {
            userRepository.save(new User(dto.getUserId(), dto.getNickname(), dto.getPassword(), dto.getEmail()));
            return ResponseEntity.ok(new GlobalResDto("Success"));
        }
    }

    @Transactional
    public ResponseEntity<LoginResDto> login(LoginReqDto dto) {
        User user = userRepository.findUserByEmailAndPassword(dto.getEmailId(), dto.getPassword())
                .orElseThrow(() -> new ApiException(ErrorCode.LOGIN_INVALID_VALUE));
        return ResponseEntity.ok(User.createLoginResDto(user));
    }

    @Transactional
    public ResponseEntity<GlobalResDto> availableIdCheck(IdAvailableReqDto dto) {
        if(userRepository.existsUserByUserId(dto.getUserId())) {
            return ResponseEntity.ok(new GlobalResDto("사용중인 아이디입니다."));
        } else {
            return ResponseEntity.ok(new GlobalResDto("사용가능한 아이디입니다."));
        }
    }

    @Transactional
    public ResponseEntity<DuplicatedResDto> duplicatedEmailCheck(DuplicatedEmailReqDto dto) {
        return ResponseEntity.ok(new DuplicatedResDto(userRepository.existsUserByEmail(dto.getEmailId())));
    }

    @Transactional
    public ResponseEntity<DuplicatedResDto> duplicatedNicknameCheck(DuplicatedNickReqDto dto) {
        return ResponseEntity.ok(new DuplicatedResDto(userRepository.existsUserByNickname(dto.getNickname())));
    }

    @Transactional
    public ResponseEntity<FindUserIdResDto> findUserId(FindUserIdReqDto dto) {
        User user = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(() -> new ApiException(ErrorCode.USERID_NOT_FOUND));
        return ResponseEntity.ok(user.createFindUserIdResDto());
    }

    @Transactional
    public ResponseEntity<GlobalResDto> updateUserPassword(UpdateUserPasswordReqDto dto) {
        User user = userRepository.findUserByUserId(dto.getUserId())
                .orElseThrow(() -> new ApiException(ErrorCode.USERID_NOT_FOUND));
        user.updatePassword(dto);
        return ResponseEntity.ok(new GlobalResDto("비밀번호 변경 완료"));
    }
}
