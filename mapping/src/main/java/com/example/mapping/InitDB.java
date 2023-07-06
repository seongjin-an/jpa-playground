package com.example.mapping;

import com.example.mapping.domain.v7.Post;
import com.example.mapping.domain.v7.Reply;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.save();
    }

//    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        @Transactional
        public void save() {
            Post post1 = Post.builder().title("post1").content("post1").build();
            em.persist(post1);

            Reply reply1 = Reply.builder().content("reply1").post(post1).build();
            post1.getReplies().add(reply1);
            em.persist(reply1);
            Reply reply2 = Reply.builder().content("reply2").post(post1).build();
            post1.getReplies().add(reply2);
            em.persist(reply2);
            Reply reply3 = Reply.builder().content("reply3").post(post1).build();
            post1.getReplies().add(reply3);
            em.persist(reply3);
            Reply reply4 = Reply.builder().content("reply4").post(post1).build();
            post1.getReplies().add(reply4);
            em.persist(reply4);

            Post post2 = Post.builder().title("post2").content("post2").build();
            em.persist(post2);
            Reply reply5 = Reply.builder().content("reply5").post(post2).build();
            post2.getReplies().add(reply5);
            em.persist(reply5);
            Reply reply6 = Reply.builder().content("reply6").post(post2).build();
            post2.getReplies().add(reply6);
            em.persist(reply6);
            Reply reply7 = Reply.builder().content("reply7").post(post2).build();
            post2.getReplies().add(reply7);
            em.persist(reply7);

            Post post3 = Post.builder().title("post3").content("post3").build();
            em.persist(post3);
            Reply reply8 = Reply.builder().content("reply8").post(post3).build();
            post3.getReplies().add(reply8);
            em.persist(reply8);

            Post post4 = Post.builder().title("post4").content("post4").build();
            em.persist(post4);
            Reply reply9 = Reply.builder().content("reply9").post(post4).build();
            post4.getReplies().add(reply9);
            em.persist(reply9);

            em.flush();
            em.clear();
        }
    }


}
