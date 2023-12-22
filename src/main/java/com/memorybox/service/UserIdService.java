package com.memorybox.service;

import com.memorybox.domain.user.entity.User;
import com.memorybox.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentLinkedQueue;

@RequiredArgsConstructor
@Service
public class UserIdService {
    private static final long START_REGISTERED_USER_NUM = 1L;
    private static final long END_REGISTERED_USER_NUM = 2L;

    private final ConcurrentLinkedQueue<Long> userIdQueue = new ConcurrentLinkedQueue<>();

    private final DummyDataService dummyDataService;
    private final UserRepository userRepository;


    @PostConstruct
    private void init() {
        //더미 유저 ID값을 Queue에 넣어주기
        for (long userId = START_REGISTERED_USER_NUM; userId <= END_REGISTERED_USER_NUM; userId++) {
            userIdQueue.offer(userId);
        }
    }

    @Transactional
    public String getUserId() {
        if (userIdQueue.isEmpty()) {
            Long userId = userRepository.save(new User()).getId();
            dummyDataService.saveDummyData(userId);
            return userId.toString();
        }
        return userIdQueue.poll().toString();
    }

}
