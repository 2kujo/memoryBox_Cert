package com.memorybox.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class UserIdService {
    private final ConcurrentLinkedQueue<Integer> userIdQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Integer> specialIdQueue = new ConcurrentLinkedQueue<>();

    @PostConstruct
    private void init() {
        for (int userId = 1; userId <= 10; userId++) {
            userIdQueue.offer(userId);
        }
        specialIdQueue.addAll(List.of(111, 112, 113));
    }

    public String getUserId() {
        Integer userId = userIdQueue.poll();
        userIdQueue.offer(userId);
        return userId.toString();
    }

    public String getSpecialUserId() {
        Integer userId = specialIdQueue.poll();
        specialIdQueue.offer(userId);
        return userId.toString();
    }

}
