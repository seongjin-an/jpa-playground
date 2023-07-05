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
public class BookAndAuthor {
    @Id
    @Column(name = "BOOK_AND_AUTHOR_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;
}
