package com.example.mapping.controller;

import com.example.mapping.domain.v7.Post;
import com.example.mapping.domain.v7.Reply;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MappingController {

    @Autowired
    EntityManager em;

    @GetMapping("/map")
    public ResponseEntity<List<Post>> map() {
        List<Post> posts = em.createQuery("select p from Post p join fetch p.replies", Post.class).getResultList();
        log.info("posts: {}", posts);
        return ResponseEntity.ok(posts);
    }



}
