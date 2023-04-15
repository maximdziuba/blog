package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<PostDto> findAll();

    Page<Post> findAllPaged(Pageable pageable);

    Page<Post> findAllByTitlePaged(String title, Pageable pageable);

    Page<Post> findAllByTextPaged(String text, Pageable pageable);

    void savePost(PostDto postDto);

    PostDto findPostById(Long id);

    boolean deletePostById(Long id, String currentUserUsername);


}
