package com.memorybox.service;

import com.memorybox.domain.user.entity.User;
import com.memorybox.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserIdService {
    private static final long START_REGISTERED_USER_NUM = 1L;
    private static final long END_REGISTERED_USER_NUM = 10L;

    private final ConcurrentLinkedQueue<Long> userIdQueue = new ConcurrentLinkedQueue<>();

    private final DummyDataService dummyDataService;
    private final UserRepository userRepository;


    @PostConstruct
    private void init() {
        for (long userId = START_REGISTERED_USER_NUM; userId <= END_REGISTERED_USER_NUM; userId++) {
            userIdQueue.offer(userId);
        }
    }

    @Transactional
    public String getUserId() {
        if (userIdQueue.isEmpty()) {
            log.info(" Make New User");
            Long userId = userRepository.save(new User()).getId();
            dummyDataService.saveDummyData(userId);
            return userId.toString();
        }
        return userIdQueue.poll().toString();
    }

    @Transactional
    public void makeUserAndDummy(int n) {
        for (int i = 0; i < n; i++) {
            Long userId = userRepository.save(new User()).getId();
            dummyDataService.saveDummyData(userId);
        }
    }
}
