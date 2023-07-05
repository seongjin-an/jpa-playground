package com.example.mapping.domain.v4;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "members")
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long teamId;

    private String teamName;

    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();
}
