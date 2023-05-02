package com.e1i4.sanmonkeybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FindUserIdResDto {
    private String userId;
    private LocalDateTime createdAt;
}
