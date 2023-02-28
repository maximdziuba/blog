package com.example.blog.dto;

import com.example.blog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RowDto {

    private List<Post> posts;

    public RowDto() {
        this.posts = new ArrayList<>();
    }

}
