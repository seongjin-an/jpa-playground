package com.example.mapping.domain.v3;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locker {
    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long lockerId;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
