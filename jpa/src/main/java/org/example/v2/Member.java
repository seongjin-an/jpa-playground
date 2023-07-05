package org.example.v2;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String name;

    @Column(name = "TEAM_ID")
    private String teamId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    public Member() {
    }
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
