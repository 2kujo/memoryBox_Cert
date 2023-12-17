package com.memorybox.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class UserIdCookieUtil {
    public static final String MEMORYBOX_SPECIAL_USER_COOKIE = "memorybox-special-user";
    public static final String MEMORYBOX_USER_ID_COOKIE = "memorybox-user-id";
    private static final long maxAgeSeconds = 60 * 60 * 24 * 30L;

    public ResponseCookie makeUserIdCookie(String userId, boolean isSpecialUser) {
        String cookieName = isSpecialUser ? MEMORYBOX_SPECIAL_USER_COOKIE : MEMORYBOX_USER_ID_COOKIE;

        return ResponseCookie.from(cookieName, userId)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();
    }
}
