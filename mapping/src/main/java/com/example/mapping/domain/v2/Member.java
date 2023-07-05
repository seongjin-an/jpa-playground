package com.example.mapping.domain.v2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "USERNAME")
    private String username;

    //    @Column(name = "TEAM_ID")
//    private Long teamId;
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
}
