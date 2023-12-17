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

    private static final long maxAgeSeconds = 60 * 60 * 24 * 30L;
    public static final String MEMORYBOX_SPECIAL_USER_COOKIE = "memorybox-special-user";
    public static final String MEMORYBOX_USER_ID_COOKIE = "memorybox-user-id";

    private final UserIdService userIdService;

    @GetMapping("/cert")
    public ResponseEntity<?> getCert(@CookieValue(MEMORYBOX_SPECIAL_USER_COOKIE) String userId) {
        if (StringUtils.isBlank(userId)) {
            userId = userIdService.getUserId();
        }
        ResponseCookie userIdCookie = makeUserIdCookie(userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .build();
    }

    @GetMapping("/special-cert")
    public ResponseEntity<?> getSpecialCert() {
        String userId = userIdService.getSpecialUserId();
        ResponseCookie userIdCookie = makeSpecialUserIdCookie(userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .build();
    }

    private ResponseCookie makeUserIdCookie(String userId) {
        return ResponseCookie.from(MEMORYBOX_USER_ID_COOKIE, userId)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();
    }

    private ResponseCookie makeSpecialUserIdCookie(String specialUserId) {
        return ResponseCookie.from(MEMORYBOX_SPECIAL_USER_COOKIE, specialUserId)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();
    }
}
