package com.memorybox.controller;

import com.memorybox.dto.response.UserIdResponseDto;
import com.memorybox.service.UserIdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CertificationController {

    private final UserIdService userIdService;

    @GetMapping("/cert")
    public ResponseEntity<?> createCert() {
        log.info(" [REQUEST] createCert Call");
        String userId = userIdService.getUserId();

        return ResponseEntity.ok()
                .body(new UserIdResponseDto(userId));
    }

    @PostMapping("/dummy")
    public ResponseEntity<?> createDummyData(@RequestBody int n) {
        if (n <= 50) {
            userIdService.makeUserAndDummy(n);
        }
        return ResponseEntity.ok().build();
    }
}
