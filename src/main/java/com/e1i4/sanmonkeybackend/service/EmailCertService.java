package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.dto.GlobalResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailCertService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String id;

    public ResponseEntity<GlobalResDto> sendSimpleMessage(String to) throws Exception {
        String key = createKey();
        MimeMessage message = createMessage(to, key);
        try {
            javaMailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        log.info("인증코드 : " + key);
        return ResponseEntity.ok(new GlobalResDto("인증메일 발송이 완료되었습니다."));
    }

    private MimeMessage createMessage(String to, String key) throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 대상 : " + to);
        log.info("인증 번호 : " + key);
        javax.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject("산숭이 회원가입 인증 코드: "); //메일 제목

        // 메일 내용 메일의 subtype 을 html 로 지정하여 html 문법 사용
        String msg = "";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += key;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용, charset 타입, subtype
        message.setFrom(new InternetAddress(id, "SAN-MONKEY-ADMIN")); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    private String createKey() {
        return IntStream.range(0, 6)
                .mapToObj(i -> String.valueOf((new Random().nextInt(10))))
                .collect(Collectors.joining());
    }
}
