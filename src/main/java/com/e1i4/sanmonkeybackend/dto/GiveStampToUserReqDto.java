package com.e1i4.sanmonkeybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GiveStampToUserReqDto {
    private String email;
    private Long stampId;
}
