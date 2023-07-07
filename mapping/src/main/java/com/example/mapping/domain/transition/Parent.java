package com.example.mapping.domain.transition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parent {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();
}
