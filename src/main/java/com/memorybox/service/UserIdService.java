package com.memorybox.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class UserIdService {
    private static final long START_REGISTERED_USER_NUM = 1L;
    private static final long END_REGISTERED_USER_NUM = 10L;

    private final ConcurrentLinkedQueue<Long> userIdQueue = new ConcurrentLinkedQueue<>();

    @PostConstruct
    private void init() {
        for (long userId = START_REGISTERED_USER_NUM; userId <= END_REGISTERED_USER_NUM; userId++) {
            userIdQueue.offer(userId);
        }
    }

    public String getUserId() {
        Long userId;
        if (userIdQueue.isEmpty()) {
            //TODO 새로운 User 및 더미데이터 생성 후 새로운 UserId 리턴 로직 필요
            userId = 0L;

        } else {
            userId = userIdQueue.poll();
        }
        return userId.toString();
    }

}
