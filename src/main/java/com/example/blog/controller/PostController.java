package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import com.example.blog.security.utils.UserUtil;
import com.example.blog.service.PostService;
import com.example.blog.service.mapper.PostDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostDtoMapper postDtoMapper;
    private static final DateTimeFormatter DATE_FORMATTER =   DateTimeFormatter.ofPattern("HH:mm yyyy/MM/dd");

    // todo: return DTO in the page
    @GetMapping("/")
    public String findAllPostsPaged(Model model,
                                    @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                    Authentication authentication) {
        Page<Post> page = postService.findAllPaged(pageable);
        String currentUserUsername = UserUtil.getUsernameFromAuthentication(authentication);
        model.addAttribute("currentUserUsername", currentUserUsername);
        model.addAttribute("dateFormatter", DATE_FORMATTER);
        model.addAttribute("page", page);
        model.addAttribute("url", "/");
        return "main";
    }

    @GetMapping("/{id}")
    public String findPostById(@PathVariable(name = "id") Long id, Model model) {
        PostDto postDto = postService.findPostById(id);
        model.addAttribute("post", postDto);
        model.addAttribute("dateFormatter", DATE_FORMATTER);
        return "post";
    }

    @GetMapping("/create")
    public String createPostPage() {
        return "createPost";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDto postDto, Authentication authentication) {
        String currentUserUsername = UserUtil.getUsernameFromAuthentication(authentication);
        postDto.setAuthorUsername(currentUserUsername);
        postService.savePost(postDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id, Authentication authentication) throws Exception {
        String currentUserUsername = UserUtil.getUsernameFromAuthentication(authentication);
        boolean postDeleted = postService.deletePostById(id, currentUserUsername);
        if (!postDeleted) {
            throw new Exception("You are not allowed to delete this post");
        }
        return "redirect:/";
    }
}
