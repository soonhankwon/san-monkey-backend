package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.domain.Stamp;
import com.e1i4.sanmonkeybackend.domain.User;
import com.e1i4.sanmonkeybackend.domain.UserStamp;
import com.e1i4.sanmonkeybackend.dto.*;
import com.e1i4.sanmonkeybackend.repository.StampRepository;
import com.e1i4.sanmonkeybackend.repository.UserRepository;
import com.e1i4.sanmonkeybackend.repository.UserStampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StampService {
    private final StampRepository stampRepository;
    private final UserRepository userRepository;
    private final UserStampRepository userStampRepository;

    public void addStampToDB(StampReqDto dto) {
        stampRepository.save(new Stamp(dto.getStampImageUrl()));
    }

    public ResponseEntity<UserStampResDto> getUserStamp(UserStampReqDto dto) {
        User user = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(IllegalArgumentException::new);
        List<UserStamp> userStampList = userStampRepository.findAllByUser(user);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        UserStampResDto userStampResDto = new UserStampResDto(
                userStampList.stream()
                        .map(userStamp -> new UserStampDto(userStamp.getStamp().getStampImageUrl(), userStamp.getCreatedAt().format(formatter)))
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(userStampResDto);
    }

    @Transactional
    public void giveStampToUser(GiveStampToUserReqDto dto) {
        User user = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(IllegalArgumentException::new);
        Stamp stamp = stampRepository.findById(dto.getStampId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateStamp(new UserStamp(user, stamp));
    }
}
