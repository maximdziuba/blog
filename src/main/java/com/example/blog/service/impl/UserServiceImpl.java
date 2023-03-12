package com.example.blog.service.impl;

import com.example.blog.model.Provider;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void processOAuthPostLogin(String username) {
        Optional<User> userFromDb = userRepository.findUserByUsername(username);
        if (userFromDb.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setProvider(Provider.GOOGLE);
            userRepository.save(newUser);
        }
    }
}
