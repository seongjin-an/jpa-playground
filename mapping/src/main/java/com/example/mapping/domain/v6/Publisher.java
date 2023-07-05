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
@ToString(exclude = "books")
public class Publisher {
    @Id
    @Column(name = "PUBLISHER_ID")
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();
}
