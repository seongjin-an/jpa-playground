package com.example.mapping;

import com.example.mapping.domain.v1.Member;
import com.example.mapping.domain.v1.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MappingV1Test {

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
        em.persist(member);
    }

}
