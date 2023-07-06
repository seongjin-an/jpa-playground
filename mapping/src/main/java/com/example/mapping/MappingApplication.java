package com.example.mapping;

import com.example.mapping.domain.v7.Post;
import com.example.mapping.domain.v7.Reply;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootApplication
public class MappingApplication {

    @PersistenceContext
    EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(MappingApplication.class, args);
    }

}
