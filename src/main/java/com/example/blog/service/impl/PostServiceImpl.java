package com.example.blog.service.impl;

import com.example.blog.dto.PostDto;
import com.example.blog.dto.RowDto;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import com.example.blog.service.mapper.PostDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostDtoMapper postDtoMapper;
    @Value("${app.view.number-of-elements-in-row}")
    private Integer numberOfElementsInRow;


    @Override
    public List<PostDto> findAll() {
        List<PostDto> all = postRepository.findAll().stream().map(postDtoMapper).toList();
        return all;
    }

    @Override
    public Page<RowDto> findAllPaged(Pageable pageable) {
        List<PostDto> all = postRepository.findAll(pageable).stream().map(postDtoMapper).toList();
        List<RowDto> rows = convertToRows(all);
        Page<RowDto> rowsPage = new PageImpl<>(rows);
        return rowsPage;
    }

    @Override
    public List<RowDto> findAllPostsAndOrderIntoRows() {
        List<PostDto> all = findAll();
        List<RowDto> rows = convertToRows(all);
        return rows;
    }

    private List<RowDto> convertToRows(List<PostDto> all) {
        List<RowDto> rows = new ArrayList<>();
        RowDto row = new RowDto();
        boolean isElementLast;
        for (int i = 0; i < all.size(); i++) {
            List<PostDto> rowsPosts = row.getPosts();
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
    public void savePost(PostDto postDto) {
        Post post = postDtoMapper.convertDtoToEntity(postDto);
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
