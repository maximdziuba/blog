package com.example.blog;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeleteUnitTests {

    @Autowired
    private PostRepository repository;


    @BeforeEach
    public void setUp() {
        repository.saveAllAndFlush(List.of(
                new Post(1L, "Post 1", "Post 1", null, LocalDateTime.now(), ""),
                new Post(2L, "Post 1", "Post 1", null, LocalDateTime.now(), "")
        ));
    }

    @Test
    public void findAllShouldReturnAllPosts() {
        List<Post> all = repository.findAll();
        assertThat(all).hasSize(2);
    }
}
