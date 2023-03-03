package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // todo: return DTO in the page
    @GetMapping("/")
    public String findAllPostsPaged(Model model,
                                    @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> page = postService.findAllPaged(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/");
        return "main";
    }

    @GetMapping("/{id}")
    public String findPostById(@PathVariable(name = "id") Long id, Model model) {
        PostDto postDto = postService.findPostById(id);
        model.addAttribute("post", postDto);
        return "post";
    }

    @GetMapping("/create")
    public String createPostPage() {
        return "createPost";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDto postDto) {
        postService.savePost(postDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return "redirect:/";
    }
}
