package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.domain.Stamp;
import com.e1i4.sanmonkeybackend.domain.User;
import com.e1i4.sanmonkeybackend.domain.UserStamp;
import com.e1i4.sanmonkeybackend.repository.StampRepository;
import com.e1i4.sanmonkeybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StampService {
    private final StampRepository stampRepository;
    private final UserRepository userRepository;

    public void addStampToDB(String stampImageUrl) {
        stampRepository.save(new Stamp(stampImageUrl));
    }

    public String getStamp(Long id) {
        Stamp stamp = stampRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return stamp.getStampImageUrl();
    }

    @Transactional
    public void giveStampToUser(String email, Long stampId) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
        Stamp stamp = stampRepository.findById(stampId)
                .orElseThrow(IllegalArgumentException::new);
        user.updateStamp(new UserStamp(user, stamp));
    }
}
