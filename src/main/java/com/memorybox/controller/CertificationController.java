package com.memorybox.controller;

import com.memorybox.common.resolver.CertUserId;
import com.memorybox.dto.response.UserIdResponseDto;
import com.memorybox.service.UserIdService;
import com.memorybox.util.UserIdCookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CertificationController {

    private final UserIdService userIdService;
    private final UserIdCookieUtil cookieUtil;

    @GetMapping("/cert")
    public ResponseEntity<?> createCert() {
        log.info(" [REQUEST] createCert Call");
        String userId = userIdService.getUserId();
        ResponseCookie userIdCookie = cookieUtil.makeUserIdCookie(userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .body(new UserIdResponseDto(userId));
    }

    @GetMapping("/special-cert")
    public ResponseEntity<?> createSpecialCert(@CertUserId(required = false) long userId) {
        ResponseCookie userIdCookie = null;
        if (userId == 0L) {
            String userIdString = userIdService.getUserId();
            userIdCookie = cookieUtil.makeUserIdCookie(userIdString);
            userId = Long.parseLong(userIdString);
        }
        ResponseCookie specialCookie = cookieUtil.makeSpecialCookie();
        return makeResponseEntity(userId, specialCookie, userIdCookie);
    }

    private ResponseEntity<?> makeResponseEntity(long userId, ResponseCookie specialCookie, ResponseCookie userIdCookie) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, specialCookie.toString());
        if (userIdCookie != null) {
            builder.header(HttpHeaders.SET_COOKIE, userIdCookie.toString());
        }
        return builder.body(new UserIdResponseDto(userId));
    }

}
