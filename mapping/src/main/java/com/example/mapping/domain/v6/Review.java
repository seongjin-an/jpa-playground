package com.example.mapping.domain.v6;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long id;

    private String title;

    private String content;

    private Float score;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}
