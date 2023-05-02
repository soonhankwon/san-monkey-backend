package com.e1i4.sanmonkeybackend.controller;

import com.e1i4.sanmonkeybackend.dto.GlobalResDto;
import com.e1i4.sanmonkeybackend.service.EmailCertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "이메일 인증 관련 API")
public class EmailCertController {
    private final EmailCertService emailCertService;

    @PostMapping("login/certMail")
    public ResponseEntity<GlobalResDto> certMail(@RequestParam String email) throws Exception {
        return emailCertService.sendSimpleMessage(email);
    }
}
