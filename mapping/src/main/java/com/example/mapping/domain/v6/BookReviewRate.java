package com.example.mapping.domain.v6;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookReviewRate {
    @Id
    @Column(name = "BOOK_REVIEW_RATE_ID")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private Float averageReviewSource;

    private int reviewCount;
}
