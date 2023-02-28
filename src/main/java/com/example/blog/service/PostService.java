package com.example.blog.service;

import com.example.blog.dto.RowDto;
import com.example.blog.model.Post;

import java.util.List;

public interface PostService {
    List<RowDto> findAllPostsAndOrderIntoRows();

    void savePost(Post post);

    Post findPostById(Long id);

    void deletePostById(Long id);
}
