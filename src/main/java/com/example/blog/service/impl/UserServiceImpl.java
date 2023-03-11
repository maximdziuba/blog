package com.example.blog.service.impl;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User findById(Long id) {
        var user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        var user = userRepository.findUserByUsername(username);
        return user.orElse(null);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
