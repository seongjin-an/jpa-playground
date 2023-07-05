package com.example.mapping.domain.v3;

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

    @OneToOne(mappedBy = "member")
    private Locker locker;
}
