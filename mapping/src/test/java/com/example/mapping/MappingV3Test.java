package com.example.mapping;

import com.example.mapping.domain.v3.Locker;
import com.example.mapping.domain.v3.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MappingV3Test {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("one to one test")
    @Commit
    void oneToOneTest() {
        Member member = new Member();
        member.setUsername("member");

        Locker locker = new Locker();
        locker.setName("locker");
        locker.setMember(member);
        member.setLocker(locker);

        em.persist(member);
        em.persist(locker);
    }

}
