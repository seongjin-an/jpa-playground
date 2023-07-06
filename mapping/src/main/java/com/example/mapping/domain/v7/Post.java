package com.example.mapping.domain.v7;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = "replies")
public class Post {
    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    private String content;

    @Builder.Default
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Reply> replies = new ArrayList<>();
}
