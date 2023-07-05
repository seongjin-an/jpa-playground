package com.example.mapping;

import com.example.mapping.domain.v4.Member;
import com.example.mapping.domain.v4.Team;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
public class MappingV4Test {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("many to one test")
    @Commit
    void manyToOneTest() {
        Team team = new Team();
        team.setTeamName("team");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member");
        member.setTeam(team);
        team.getMembers().add(member);

        em.persist(member);

        em.flush();
        em.clear();



    }


    @Test
    @DisplayName("lazy vs eager")
    @Commit
    void lazyEager() {
        Team team1 = new Team();
        team1.setTeamName("teamA");
        em.persist(team1);

        Team team2 = new Team();
        team2.setTeamName("teamB");
        em.persist(team2);

        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("member2");
        member2.setTeam(team2);
        em.persist(member2);

        em.flush();
        em.clear();

        Member foundMember = em.find(Member.class, member1.getMemberId());
        log.info("foundMember = {}", foundMember);


    }

}
