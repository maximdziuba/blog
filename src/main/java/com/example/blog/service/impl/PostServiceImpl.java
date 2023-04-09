package com.example.blog.service.impl;

import com.example.blog.dto.PostDto;
import com.example.blog.dto.RowDto;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import com.example.blog.service.mapper.PostDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostDtoMapper postDtoMapper;
    private final UserService userService;
    @Value("${app.view.number-of-elements-in-row}")
    private Integer numberOfElementsInRow;


    @Override
    public List<PostDto> findAll() {
        List<PostDto> all = postRepository.findAll().stream().map(postDtoMapper).toList();
        return all;
    }

    @Override
    public Page<Post> findAllPaged(Pageable pageable) {
//        List<PostDto> all = postRepository.findAll(pageable).stream().map(postDtoMapper).toList();
        Page<Post> all = postRepository.findAll(pageable);
//        List<RowDto> rows = convertToRows(all);
//        Page<PostDto> rowsPage = new PageImpl<>(all);
        return all;
    }

    @Override
    public List<PostDto> findAllPostsAndOrderIntoRows() {
        List<PostDto> all = findAll();
        return all;
    }

    @Override
    @Transactional
    public void savePost(PostDto postDto) {
        String username = postDto.getAuthorUsername();
        User author = userService.findByUsername(username);
        postDto.setCreationDate(LocalDateTime.now());
        Post post = postDtoMapper.convertDtoToEntity(postDto);
        post.setAuthor(author);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        PostDto postDto = postDtoMapper.convertEntityToDto(post);
        return postDto;
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

}
