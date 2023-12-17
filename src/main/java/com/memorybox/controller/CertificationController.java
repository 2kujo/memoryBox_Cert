package com.memorybox.controller;

import com.memorybox.service.UserIdService;
import com.memorybox.util.UserIdCookieUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.memorybox.util.UserIdCookieUtil.MEMORYBOX_SPECIAL_USER_COOKIE;

@RequiredArgsConstructor
@RestController
public class CertificationController {

    private final UserIdService userIdService;
    private final UserIdCookieUtil cookieUtil;

    @GetMapping("/cert")
    public ResponseEntity<?> getCert(@CookieValue(MEMORYBOX_SPECIAL_USER_COOKIE) String userId) {
        if (StringUtils.isBlank(userId)) {
            userId = userIdService.getUserId();
        }
        ResponseCookie userIdCookie = cookieUtil.makeUserIdCookie(userId, false);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .build();
    }

    @GetMapping("/special-cert")
    public ResponseEntity<?> getSpecialCert() {
        String userId = userIdService.getSpecialUserId();
        ResponseCookie userIdCookie = cookieUtil.makeUserIdCookie(userId, true);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .build();
    }

}
