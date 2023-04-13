package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public String userProfile(@PathVariable(name = "username") String username, Model model) {
        User userFromDb = userService.findByUsername(username);
        model.addAttribute("user", userFromDb);
        return "userProfile";
    }
}
