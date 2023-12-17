package com.memorybox.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
public class CertificationController {

    private final ConcurrentLinkedDeque<Integer> userIdQueue = new ConcurrentLinkedDeque<>();
    private static final long maxAgeSeconds = 60 * 60 * 24 * 30L;

    @GetMapping("/cert")
    public ResponseEntity<?> getCert(@CookieValue("memorybox-cert-cookie") String userId) {
        if (StringUtils.isBlank(userId)) {
            userId = getUserId();
        }
        ResponseCookie userIdCookie = makeUserIdCookie(userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userIdCookie.toString())
                .build();
    }

    private String getUserId() {
        return userIdQueue.pop().toString();
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

/*
* 1. 프론트 특정 URL 접속 -> 기념일 발생 유저 번호의 쿠키를 발급해주는 페이지
* 2. 인증 서버에 인증 요청 시 해당 쿠키 확인
*  - 있으면 Special 유저 번호를 memorybox-user-id 쿠키에 담아주기
*  - 없으면 Queue에서 꺼낸 유저 번호를 쿠키에 담아주기
*
* 쿠키가 있으면 그 쿠키 ID리턴 -> 이미 들어온 사람들 재활용
* 없으면 쿠키생성해주고 리턴 -> 첫 발급
*
* */