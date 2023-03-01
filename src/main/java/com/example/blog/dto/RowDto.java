package com.example.blog.dto;

import com.example.blog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RowDto {

    private List<PostDto> posts;

    public RowDto() {
        this.posts = new ArrayList<>();
    }

}
