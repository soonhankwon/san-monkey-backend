package com.e1i4.sanmonkeybackend.controller;

import com.e1i4.sanmonkeybackend.service.StampService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "스탬프 관련 기능 API")
public class StampController {
    private final StampService stampService;

    @Operation(summary = "DB 스탬프 저장")
    @PostMapping("/create/stamp")
    public void createStamp(@RequestParam String stampImageUrl) {
        stampService.createStampToDB(stampImageUrl);
    }

    @Operation(summary = "스탬프 조회")
    @PostMapping("/stamp")
    public String getStamp(@RequestParam Long id) {
        return stampService.getStamp(id);
    }
}
