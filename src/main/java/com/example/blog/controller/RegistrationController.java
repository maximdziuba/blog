package com.example.blog.controller;

import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user, Model model) {
        User userFromDatabase = userService.findByUsername(user.getUsername());
        if (userFromDatabase != null) {
            model.addAttribute("message", "User already exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
