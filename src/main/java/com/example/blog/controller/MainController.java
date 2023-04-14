package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.security.utils.UserUtil;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final PostService postService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm yyyy/MM/dd");


    // todo: change to the start page
    @GetMapping
    public String startPage(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            Authentication authentication,
                            Model model) {
        boolean isLoggedIn = authentication != null;
        Page<Post> page = postService.findAllPaged(pageable);
        String currentUserUsername = UserUtil.getUsernameFromAuthentication(authentication);

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("currentUserUsername", currentUserUsername);
        model.addAttribute("dateFormatter", DATE_FORMATTER);
        model.addAttribute("page", page);
        model.addAttribute("url", "/");
        return "allPosts";
    }
}
