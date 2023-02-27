package com.example.blog.service.impl;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
