package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.domain.User;
import com.e1i4.sanmonkeybackend.dto.SignupReqDto;
import com.e1i4.sanmonkeybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public String signUp(SignupReqDto dto) {
        if(!userRepository.existsUserByEmail(dto.getEmail())) {
            userRepository.save(new User(dto.getNickname(), dto.getPassword(), dto.getEmail()));
            return "Success";
        } else {
            throw new RuntimeException();
        }
    }
}
