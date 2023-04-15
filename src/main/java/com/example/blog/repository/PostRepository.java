package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Post> findAllByTextContainingIgnoreCase(String text, Pageable pageable);
}
