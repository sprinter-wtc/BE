package com.example.studyspot.user.service;

import com.example.studyspot.user.domain.model.User;
import com.example.studyspot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createOrFindGuestUser(String ssaid) {
        return userRepository.findBySsaid(ssaid)
                .orElseGet(() -> createUserOfRandomName(ssaid));
    }

    public User createUserOfRandomName(String ssaid) {
        return userRepository.save(User.createUserOfRandomName(ssaid));
    }
}
