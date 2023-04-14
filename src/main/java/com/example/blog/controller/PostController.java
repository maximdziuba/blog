package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import com.example.blog.security.utils.UserUtil;
import com.example.blog.service.PostService;
import com.example.blog.service.mapper.PostDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostDtoMapper postDtoMapper;
    private static final DateTimeFormatter DATE_FORMATTER =   DateTimeFormatter.ofPattern("HH:mm yyyy/MM/dd");

    @Value("${app.upload.path}")
    private String uploadPath;

    // todo: return DTO in the page
    @GetMapping
    public String findAllPostsPaged(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
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

    @GetMapping("/{id}")
    public String findPostById(@PathVariable(name = "id") Long id,
                               Authentication authentication,
                               Model model) {
        boolean isLoggedIn = authentication != null;
        PostDto postDto = postService.findPostById(id);
        String currentUserUsername = UserUtil.getUsernameFromAuthentication(authentication);

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("currentUserUsername", currentUserUsername);
        model.addAttribute("post", postDto);
        model.addAttribute("dateFormatter", DATE_FORMATTER);
        return "post";
    }

    @GetMapping("/create")
    public String createPostPage() {
        return "createPost";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDto postDto,
                             @RequestParam("file") MultipartFile file,
                             Authentication authentication) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(Path.of(uploadPath + "/" + resultFilename));
            postDto.setFilename(resultFilename);
        }

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
