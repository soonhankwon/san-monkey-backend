package com.e1i4.sanmonkeybackend.dto;

import com.e1i4.sanmonkeybackend.domain.Stamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserStampResDto {
    private List<Stamp> userStampList;
}
