package com.example.mapping.domain.v6;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "MEMBER")
@ToString(exclude = "reviews")
public class User {
    @Id
    @Column(name = "USER_ID")
    private Long id;

    private String name;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

}
