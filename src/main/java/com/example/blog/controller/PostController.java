package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String findAllPosts(Model model) {
        List<Post> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "main";
    }

    @GetMapping("/{id}")
    public String findPostById(@PathVariable(name = "id") Long id, Model model) {
        Post post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/")
    public String createPost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return "redirect:/";
    }
}
