package com.e1i4.sanmonkeybackend.controller;

import com.e1i4.sanmonkeybackend.dto.*;
import com.e1i4.sanmonkeybackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "회원 관련 기능 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<GlobalResDto> signup(@RequestBody SignupReqDto signupReqDto) {
        return userService.signUp(signupReqDto);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        return userService.login(loginReqDto);
    }

    @Operation(summary = "아이디 사용 가능 여부 체크")
    @GetMapping("/id/check")
    public ResponseEntity<GlobalResDto> availableIdCheck(IdAvailableReqDto idAvailableReqDto) {
        return userService.availableIdCheck(idAvailableReqDto);
    }

    @Operation(summary = "이메일계정 중복확인")
    @GetMapping("/email/check")
    public ResponseEntity<DuplicatedResDto> isDuplicatedEmail(DuplicatedEmailReqDto duplicatedEmailReqDto) {
        return userService.duplicatedEmailCheck(duplicatedEmailReqDto);
    }

    @Operation(summary = "닉네임 중복확인")
    @GetMapping("/nickname/check")
    public ResponseEntity<DuplicatedResDto> isDuplicatedNickname(DuplicatedNickReqDto duplicatedNickReqDto) {
        return userService.duplicatedNicknameCheck(duplicatedNickReqDto);
    }

    @Operation(summary = "아이디 찾기")
    @GetMapping("/id/find")
    public ResponseEntity<FindUserIdResDto> findUserId(FindUserIdReqDto findUserIdReqDto) {
        return userService.findUserId(findUserIdReqDto);
    }

    @Operation(summary = "유저 비밀번호 변경")
    @GetMapping("/id/password/update")
    public ResponseEntity<GlobalResDto> updateUserPassword(UpdateUserPasswordReqDto updateUserPasswordReqDto) {
        return userService.updateUserPassword(updateUserPasswordReqDto);
    }
}
