package com.memorybox.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class UserIdService {
    private final ConcurrentLinkedDeque<Integer> userIdQueue = new ConcurrentLinkedDeque<>();

    @PostConstruct
    private void init() {
        for (int userId = 1; userId <= 10; userId++) {
            userIdQueue.offer(userId);
        }
    }

    public String getUserId() {
        Integer userId = userIdQueue.pop();
        userIdQueue.offer(userId);
        return userId.toString();
    }

    public void addUserId(int userId) {
        userIdQueue.offer(userId);
    }
}
