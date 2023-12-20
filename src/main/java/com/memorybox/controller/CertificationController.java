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

import static com.memorybox.util.UserIdCookieUtil.MEMORYBOX_USER_ID_COOKIE;

@RequiredArgsConstructor
@RestController
public class CertificationController {

    private final UserIdService userIdService;
    private final UserIdCookieUtil cookieUtil;

    @GetMapping("/cert")
    public ResponseEntity<?> createCert() {
        String userId = userIdService.getUserId();
        ResponseCookie userIdCookie = cookieUtil.makeUserIdCookie(userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .build();
    }

    @GetMapping("/special-cert")
    public ResponseEntity<?> createSpecialCert(@CookieValue(value = MEMORYBOX_USER_ID_COOKIE, required = false) String userIdCookieString) {
        ResponseCookie userIdCookie = null;
        if (StringUtils.isBlank(userIdCookieString)) {
            String userId = userIdService.getUserId();
            userIdCookie = cookieUtil.makeUserIdCookie(userId);
        }
        ResponseCookie specialCookie = cookieUtil.makeSpecialCookie();

        return makeResponseEntity(specialCookie, userIdCookie);
    }

    private ResponseEntity<?> makeResponseEntity(ResponseCookie specialCookie, ResponseCookie userIdCookie) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, specialCookie.toString());
        if (userIdCookie != null) {
            builder.header(HttpHeaders.SET_COOKIE, userIdCookie.toString());
        }
        return builder.build();
    }

}
