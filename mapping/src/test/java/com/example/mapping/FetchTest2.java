package com.example.mapping;

import com.example.mapping.domain.v7.Post;
import com.example.mapping.domain.v7.PostRepository;
import com.example.mapping.domain.v7.Reply;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
public class FetchTest2 {

    @Autowired
    EntityManager em;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void beforeEach() {
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

        Post post5 = Post.builder().title("post5").content("post5").build();
        em.persist(post5);
        Reply reply10 = Reply.builder().content("reply10").post(post5).build();
        post5.getReplies().add(reply10);
        em.persist(reply10);

        Post post6 = Post.builder().title("post6").content("post6").build();
        em.persist(post6);
        Reply reply11 = Reply.builder().content("reply11").post(post6).build();
        post6.getReplies().add(reply11);
        em.persist(reply11);

        Post post7 = Post.builder().title("post7").content("post7").build();
        em.persist(post7);
        Reply reply12 = Reply.builder().content("reply12").post(post7).build();
        post7.getReplies().add(reply12);
        em.persist(reply12);

        Post post8 = Post.builder().title("post8").content("post8").build();
        em.persist(post8);
        Reply reply13 = Reply.builder().content("reply13").post(post8).build();
        post8.getReplies().add(reply13);
        em.persist(reply13);

        Post post9 = Post.builder().title("post9").content("post9").build();
        em.persist(post9);
        Reply reply14 = Reply.builder().content("reply14").post(post9).build();
        post9.getReplies().add(reply14);
        em.persist(reply14);

        Post post10 = Post.builder().title("post10").content("post10").build();
        em.persist(post10);
        Reply reply15 = Reply.builder().content("reply15").post(post10).build();
        post10.getReplies().add(reply15);
        em.persist(reply15);

        em.flush();
        em.clear();
        log.info("end initialize");
    }

    @Test
    @DisplayName("즉시로딩 쿼리 확인용")
    @Rollback(false)
    void eagerTest() {
//        Post post = em.find(Post.class, 1L);
//        Reply reply = em.find(Reply.class, 1L);
//        List<Post> list = em.createQuery("SELECT P FROM Post P JOIN FETCH P.replies", Post.class).getResultList();
//        log.info("list: {}", list);
        em.clear();
        List<Post> posts = em.createQuery("SELECT P FROM Post P", Post.class).getResultList();
        for (Post post : posts) {
            List<Reply> replies = post.getReplies();
            replies.size();
            for (Reply reply : replies) {
                log.info("reply: {}", reply.getClass().getName());
            }
        }
//        log.info("post[0] getClass(): {}", posts.get(0).getClass());
//        List<Reply> replies = em.createQuery("SELECT R FROM Reply R", Reply.class).getResultList();


    }

    @Test
    @DisplayName("n+1")
    void troubleTest() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            List<Reply> replies = post.getReplies();
            replies.size();
        }
    }

    @Test
    @DisplayName("fetch join")
    void fetchJoin() {
        List<Post> posts = em.createQuery("SELECT P FROM Post P join fetch P.replies", Post.class).getResultList();
        for (Post post : posts) {
            List<Reply> replies = post.getReplies();
            replies.size();
            for (Reply reply : replies) {
                log.info("post[{}], replices: {}", post.getId(), replies);
            }
        }
    }

    @Test
    @DisplayName("entityGraph")
    void entityGraph() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            List<Reply> replies = post.getReplies();
            replies.size();
            for (Reply reply : replies) {
                log.info("post[{}], replices: {}", post.getId(), replies);
            }
        }
    }

    @Test
    @DisplayName("distinct fetch join")
    void distinctFetchJoin() {
        List<Post> posts = em.createQuery("SELECT DISTINCT P FROM Post P join fetch P.replies", Post.class).getResultList();
        for (Post post : posts) {
            log.info("post: {}", post);
        }
    }

    @Test
    @DisplayName("just fetch join")
    void justFetchJoin() {
        List<Post> posts = em.createQuery("SELECT P FROM Post P join fetch P.replies", Post.class).getResultList();
        for (Post post : posts) {
            log.info("post: {}", post);
        }
    }


    @Test
    @DisplayName("paging test")
    void pagingTest() {
        List list = em.createQuery("SELECT P FROM Post P join fetch P.replies", Post.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();
        log.info("list: {}", list);
    }

    @Test
    @DisplayName("paging test")
    void paingTest2() {
        List<Reply> list = em.createQuery("SELECT R FROM Reply R join fetch R.post", Reply.class)
                .setFirstResult(1)
                .setMaxResults(3)
                .getResultList();

    }
}
