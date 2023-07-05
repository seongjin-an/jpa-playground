package com.example.mapping.domain.v5;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long teamId;

    private String teamName;

    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();
}
