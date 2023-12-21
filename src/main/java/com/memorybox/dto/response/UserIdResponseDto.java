package com.memorybox.dto.response;

public record UserIdResponseDto(long userId) {
    public UserIdResponseDto(String userId) {
        this(Long.parseLong(userId));
    }
}
