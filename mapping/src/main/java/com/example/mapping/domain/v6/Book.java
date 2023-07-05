package com.example.mapping.domain.v6;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"reviews", "bookReviewRate", "bookAndAuthors"})
public class Book {
    @Id
    @Column(name = "BOOK_ID")
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    @OneToOne(mappedBy = "book")
    private BookReviewRate bookReviewRate;

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();
}
