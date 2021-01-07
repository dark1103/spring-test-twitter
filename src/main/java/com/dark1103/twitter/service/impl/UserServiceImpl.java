package com.dark1103.twitter.service.impl;

import com.dark1103.twitter.dao.entity.User;
import com.dark1103.twitter.dao.repository.UserRepository;
import com.dark1103.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByLogin(username);
    }

}
