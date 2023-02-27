package com.example.blog.service;

import com.example.blog.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAllPosts();

    void savePost(Post post);

    Post findPostById(Long id);

    void deletePostById(Long id);
}
