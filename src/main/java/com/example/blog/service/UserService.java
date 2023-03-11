package com.example.blog.service;

import com.example.blog.model.User;

public interface UserService {

    User findById(Long id);
    User findByUsername(String username);
    void saveUser(User user);
}
