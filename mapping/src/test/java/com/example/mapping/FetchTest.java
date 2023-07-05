package com.example.mapping;

import com.example.mapping.domain.v6.*;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
public class FetchTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        User userA = User.builder().id(1L).name("userA").email("userA@abc.com").gender(Gender.MALE).build();
        User userB = User.builder().id(2L).name("userB").email("userB@abc.com").gender(Gender.MALE).build();
        User userC = User.builder().id(3L).name("userC").email("userC@abc.com").gender(Gender.MALE).build();
        em.persist(userA);
        em.persist(userB);
        em.persist(userC);

        Author authorA = Author.builder().id(1L).name("authorA").country("countryA").build();
        em.persist(authorA);

        Publisher publisherA = Publisher.builder().id(1L).name("publisherA").build();
        em.persist(publisherA);

        Book bookA = Book.builder().id(1L).name("bookA").category("bookA").authorId(111L).build();
        bookA.setPublisher(publisherA);
        publisherA.getBooks().add(bookA);
        em.persist(bookA);

        BookAndAuthor bookAndAuthor1 = BookAndAuthor.builder().id(1L).author(authorA).book(bookA).build();
        authorA.getBookAndAuthors().add(bookAndAuthor1);
        em.persist(bookAndAuthor1);

        BookReviewRate bookReviewRate = BookReviewRate.builder().id(1L).book(bookA).averageReviewSource(4.1f).reviewCount(100).build();
        bookA.setBookReviewRate(bookReviewRate);
        em.persist(bookReviewRate);


        Book bookB = Book.builder().id(2L).name("bookB").category("bookB").authorId(111L).build();
        em.persist(bookB);

        Review review = Review.builder().title("review1").content("review1").score(10f).
                user(userA).book(bookA).build();
        userA.getReviews().add(review);
        bookA.getReviews().add(review);
        em.persist(review);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("lazy vs eager")
    @Rollback(false)
    void lazyEagerTest() {

        Author authorA = Author.builder().name("authorA").country("korea").build();

        Publisher publisherA = Publisher.builder()
                .name("publisherA").build();

        BookReviewRate bookReviewRate1 = BookReviewRate.builder()
                .averageReviewSource(3.9f).reviewCount(100).build();


        // user와 리뷰를 먼저 저장
        User userA = User.builder()
                .id(1L).name("userA").email("userA@abc.com").gender(Gender.MALE).build();

        Review goodReview1 = Review.builder()
                .title("good1").content("good1").score(5f).build();
        goodReview1.setUser(userA);
        em.persist(goodReview1);
//        userA.getReviews().add(goodReview1);
        em.persist(userA);

        Book bookA = Book.builder()
                .name("bookA").category("science").authorId(1L)
                .publisher(publisherA)
                .build();

        // author와 bookAndAuthor를 그 다음으로 저장
        BookAndAuthor bookAndAuthor1 = BookAndAuthor.builder()
                .build();
        bookAndAuthor1.setBook(bookA);
        bookAndAuthor1.setAuthor(authorA);
        em.persist(bookAndAuthor1);
        authorA.getBookAndAuthors().add(bookAndAuthor1);
        em.persist(authorA);
        bookA.getBookAndAuthors().add(bookAndAuthor1);

        log.info("CHECKPOINT0");
        // publisher 저장
        bookA.setPublisher(publisherA);
        publisherA.getBooks().add(bookA);
        em.persist(publisherA);

        log.info("CHECKPOINT1");
        // review, book 관계 설정 및 book 저장
        bookA.setBookReviewRate(bookReviewRate1);
        goodReview1.setBook(bookA);
//        bookA.getReviews().add(goodReview1);
        em.persist(bookA);

        log.info("CHECKPOINT2");
        // bookReviewRate 저장
        bookReviewRate1.setBook(bookA);
        em.persist(bookReviewRate1);

    }

    @Test
    @DisplayName("test")
    @Commit
    void test() {
        log.info("=====================begin test=====================");
        User user = em.find(User.class, 1L);
        Book book = em.find(Book.class, 1L);
        log.info("book: {}", book);
    }


}
