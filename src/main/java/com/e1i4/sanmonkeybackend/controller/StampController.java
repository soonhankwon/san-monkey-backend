package com.e1i4.sanmonkeybackend.controller;

import com.e1i4.sanmonkeybackend.dto.GiveStampToUserReqDto;
import com.e1i4.sanmonkeybackend.dto.StampReqDto;
import com.e1i4.sanmonkeybackend.dto.UserStampReqDto;
import com.e1i4.sanmonkeybackend.dto.UserStampResDto;
import com.e1i4.sanmonkeybackend.service.StampService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "스탬프 관련 기능 API")
public class StampController {
    private final StampService stampService;

    @Operation(summary = "DB 스탬프 저장")
    @PostMapping("/db/stamp")
    public void createStamp(@RequestBody StampReqDto stampReqDto) {
        stampService.addStampToDB(stampReqDto);
    }

    @Operation(summary = "유저 스탬프 조회")
    @GetMapping("/user/stamp")
    public ResponseEntity<UserStampResDto> getUserStamp(@RequestBody UserStampReqDto userStampReqDto) {
        return stampService.getUserStamp(userStampReqDto);
    }

    @Operation(summary = "유저 스탬프 수여")
    @PostMapping("/user/stamp/give")
    public void giveStampToUser(@RequestBody GiveStampToUserReqDto giveStampToUserReqDto) {
        stampService.giveStampToUser(giveStampToUserReqDto);
    }
}
