package com.example.mapping.domain.v7;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = {"replies"})
    List<Post> findAll();
}
