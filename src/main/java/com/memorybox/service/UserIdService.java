package com.memorybox.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class UserIdService {
    private final ConcurrentLinkedDeque<Integer> userIdQueue = new ConcurrentLinkedDeque<>();

    public String getUserId() {
        Integer userId = userIdQueue.pop();
        userIdQueue.offer(userId);
        return userId.toString();
    }

    public void addUserId(int userId) {
        userIdQueue.offer(userId);
    }
}
