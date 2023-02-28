package com.example.blog.service.impl;

import com.example.blog.dto.RowDto;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Value("${app.view.number-of-elements-in-row}")
    private Integer numberOfElementsInRow;

    @Override
    public List<RowDto> findAllPostsAndOrderIntoRows() {
        List<Post> all = postRepository.findAll();
        List<RowDto> rows = new ArrayList<>();
        RowDto row = new RowDto();
        boolean isElementLast;
        for (int i = 0; i < all.size(); i++) {
            List<Post> rowsPosts = row.getPosts();
            rowsPosts.add(all.get(i));
            row.setPosts(rowsPosts);
            isElementLast = i + 1 == all.size();
            if ((i + 1) % numberOfElementsInRow == 0 || isElementLast) {
                rows.add(row);
                row = new RowDto();
            }
        }
        return rows;
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
