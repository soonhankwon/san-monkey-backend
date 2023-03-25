package com.e1i4.sanmonkeybackend.controller;

import com.e1i4.sanmonkeybackend.dto.SignupReqDto;
import com.e1i4.sanmonkeybackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "회원 관련 기능 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원 가입")
    @PostMapping("/signup")
    public String signup(SignupReqDto signupReqDto) {
        return userService.signUp(signupReqDto);
    }
}
