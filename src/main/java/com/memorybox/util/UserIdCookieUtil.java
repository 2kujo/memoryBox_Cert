package com.memorybox.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class UserIdCookieUtil {
    public static final String MEMORYBOX_SPECIAL_COOKIE = "memorybox-special-user";
    public static final String MEMORYBOX_USER_ID_COOKIE = "memorybox-user-id";
    private static final long maxAgeSeconds = 60 * 60 * 24 * 30L;

    public ResponseCookie makeUserIdCookie(String userId) {
        return ResponseCookie.from(MEMORYBOX_USER_ID_COOKIE, userId)
                .domain(".165.192.105.60.nip.io")
                .maxAge(maxAgeSeconds)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
    }

    public ResponseCookie makeSpecialCookie() {
        return ResponseCookie.from(MEMORYBOX_SPECIAL_COOKIE, "special-cookie")
                .maxAge(maxAgeSeconds)
                .domain(".165.192.105.60.nip.io")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
    }
}
