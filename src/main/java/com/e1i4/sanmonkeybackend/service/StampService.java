package com.e1i4.sanmonkeybackend.service;

import com.e1i4.sanmonkeybackend.domain.Stamp;
import com.e1i4.sanmonkeybackend.exception.ErrorCode;
import com.e1i4.sanmonkeybackend.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StampService {
    private final StampRepository stampRepository;
    public void createStampToDB(String stampImageUrl) {
        stampRepository.save(new Stamp(stampImageUrl));
    }

    public String getStamp(Long id) {
        Stamp stamp = stampRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return stamp.getStampImageUrl();
    }
}
