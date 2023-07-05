package com.example.mapping;

import com.example.mapping.domain.v2.Member;
import com.example.mapping.domain.v2.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MappingV2Test {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("one to many test")
    @Commit
    void oneToManyTest() {
        Member member = new Member();
        member.setUsername("member");
        em.persist(member);

        Team team = new Team();
        team.setTeamName("team");
        team.getMembers().add(member);
        em.persist(team);
    }

}
