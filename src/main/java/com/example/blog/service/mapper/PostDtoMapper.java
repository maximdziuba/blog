package com.example.blog.service.mapper;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDtoMapper implements Function<Post, PostDto> {
    @Override
    public PostDto apply(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getText(), post.getAuthor().getUsername());
    }

    public Post convertDtoToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        return post;
    }

    public PostDto convertEntityToDto(Post post) {
        PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getText(),
                post.getAuthor().getUsername()
        );
        return postDto;
    }
}
