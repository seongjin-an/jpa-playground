package com.example.mapping.domain.v6;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "bookAndAuthors")
public class Author {
    @Id
    @Column(name = "AUTHOR_ID")
    private Long id;

    private String name;

    private String country;

    @Builder.Default
    @OneToMany(mappedBy = "author")
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();
}
