package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.dto.RowDto;
import com.example.blog.model.Post;
import com.example.blog.service.mapper.PostDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<PostDto> findAll();

    Page<RowDto> findAllPaged(Pageable pageable);

    List<RowDto> findAllPostsAndOrderIntoRows();

    void savePost(PostDto postDto);

    PostDto findPostById(Long id);

    void deletePostById(Long id);
}
