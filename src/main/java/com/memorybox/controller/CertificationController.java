package com.memorybox.controller;

import com.memorybox.service.UserIdService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CertificationController {

    private final UserIdService userIdService;
    private static final long maxAgeSeconds = 60 * 60 * 24 * 30L;

    @GetMapping("/cert")
    public ResponseEntity<?> getCert(@CookieValue("memorybox-cert-cookie") String userId) {
        if (StringUtils.isBlank(userId)) {
            userId = userIdService.getUserId();
        }
        ResponseCookie userIdCookie = makeUserIdCookie(userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .build();
    }

    private ResponseCookie makeUserIdCookie(String userId) {
        return ResponseCookie.from("memorybox-userId", userId)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();
    }
}
