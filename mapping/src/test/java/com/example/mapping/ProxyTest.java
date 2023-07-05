package com.example.mapping;

import com.example.mapping.domain.v5.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
public class ProxyTest {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("proxy test")
    void proxyTest() {
        Member member = new Member();
        member.setUsername("member");

        em.persist(member);
        em.flush();
        em.clear();

        log.info("CHECK POINT1");
        Member findMember = em.getReference(Member.class, member.getMemberId()); // proxy
        log.info("findMember.getClass() = {}", findMember.getClass());
        log.info("findMember.getMemberId() = {}", findMember.getMemberId());
        log.info("findMember.getUsername() = {}", findMember.getUsername());

        log.info("CHECK POINT2");
        em.clear();
        Member m1 = em.find(Member.class, member.getMemberId());
        log.info("m1.getClass() = {}", m1.getClass());
        log.info("m1 = {}", m1);
        Member reference = em.getReference(Member.class, member.getMemberId());
        log.info("reference = {}", reference.getClass());
        // 프록시가 아닌 실제 엔티티 반환 // 영속성 컨텍스트에 이미 적재되었다면 굳이 프록시를 사용하지 않음

        // 영속성 컨텍스트에 이미 적재되었던 것을 찾음
        log.info("m1 == reference : {}", m1 == reference);

        em.clear();
        log.info("CHECK POINT3");
        Member refMember = em.getReference(Member.class, member.getMemberId()); // Proxy
        log.info("refMember = {}", refMember.getClass());

        log.info("isLoaded = {}", emf.getPersistenceUnitUtil().isLoaded(refMember));
        Member findMem = em.find(Member.class, member.getMemberId()); // proxy
        // 실제 엔티티가 아닌 프록시를 반환해버림, 이미 조회된 프록시가 있어서
        log.info("findMem = {}", findMem.getClass());

        log.info("refMember == findMem : {}", refMember == findMem);
    }

}
