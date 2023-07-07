package com.example.mapping.domain.transition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "parent")
public class Child {
    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;
}
