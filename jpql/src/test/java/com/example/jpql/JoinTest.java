package com.example.jpql;

import com.example.jpql.domain.Member;
import com.example.jpql.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@SpringBootTest
public class JoinTest {
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("묵시적 내부조인이란? 경로표현식으로 인해 발생함")
    void impliedInnerJoin() {
        Member member = new Member();
        member.setUsername("관리자1");
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("관리자2");
        em.persist(member2);

        em.flush();
        em.clear();

//        String query = "select m.team from Member m";// 단일값 연관 경로때문에, 묵시적 내부 조인이 발생된다. 실무에서는 이렇게 짜면 튜닝이 어려워짐
//        List<Team> result = em.createQuery(query, Team.class).getResultList();
//        for (Team team : result) {
//            log.info("team: {}", team);
//        }
        String query = "select t.members from Team t";// 단일값 연관 경로때문에, 묵시적 내부 조인이 발생된다. 실무에서는 이렇게 짜면 튜닝이 어려워짐
        List resultList = em.createQuery(query, List.class).getResultList();
        for (Object o : resultList) {
            log.info("o = {}", o);
        }

    }

    @Test
    @DisplayName("fetch join")
    @Commit
    void fetchJoin() {
        Team teamA = new Team();
        teamA.setName("teamA");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("teamB");
        em.persist(teamB);

        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("member2");
        member2.setTeam(teamA);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("member3");
        member3.setTeam(teamB);
        em.persist(member3);

        em.flush();
        em.clear();

//        String query = "select m from Member m join m.team";
//
//        List<Member> result = em.createQuery(query, Member.class).getResultList();
//
//        for (Member member : result) {
//            log.info("member = {}, {}", member.getUsername(), member.getTeam().getName());
//        }
        String query = "select t from Team t join fetch t.members m";

        List<Team> result = em.createQuery(query, Team.class)
                .setFirstResult(0).setMaxResults(1).getResultList();

        for (Team team : result) {
            log.info("team : {}", team);
        }
    }


    @Test
    @DisplayName("fetch join page")
    @Commit
    void fetchJoinPage() {
        Team teamA = new Team();
        teamA.setName("teamA");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("teamB");
        em.persist(teamB);

        Team teamC = new Team();
        teamC.setName("teamC");
        em.persist(teamC);

        Team teamD = new Team();
        teamD.setName("teamD");
        em.persist(teamD);

        Team teamE = new Team();
        teamE.setName("teamE");
        em.persist(teamE);

        Team teamF = new Team();
        teamF.setName("teamF");
        em.persist(teamF);

        Team teamG = new Team();
        teamG.setName("teamG");
        em.persist(teamG);

        Team teamH = new Team();
        teamH.setName("teamH");
        em.persist(teamH);

        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("member2");
        member2.setTeam(teamA);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("member3");
        member3.setTeam(teamB);
        em.persist(member3);

        Member member4 = new Member();
        member4.setUsername("member4");
        member4.setTeam(teamC);
        em.persist(member4);

        Member member5 = new Member();
        member5.setUsername("member5");
        member5.setTeam(teamD);
        em.persist(member5);

        Member member6 = new Member();
        member6.setUsername("member6");
        member6.setTeam(teamE);
        em.persist(member6);

        Member member7 = new Member();
        member7.setUsername("member7");
        member7.setTeam(teamF);
        em.persist(member7);

        Member member8 = new Member();
        member8.setUsername("member8");
        member8.setTeam(teamG);
        em.persist(member8);

        Member member9 = new Member();
        member9.setUsername("member9");
        member9.setTeam(teamH);
        em.persist(member9);

        em.flush();
        em.clear();

        // case 1
//        String query = "select t from Team t";
//        List<Team> result = em.createQuery(query, Team.class)
//                .setFirstResult(0).setMaxResults(2).getResultList();
//        for (Team team : result) {
//            log.info("team : {}", team);
//        }

        // case 2 @BatchSize 어노테이션 추가, 또는 글로벌 세팅으로 하자
        String query = "select t from Team t";
        List<Team> result = em.createQuery(query, Team.class)
                .setFirstResult(2).setMaxResults(2).getResultList();
        log.info("result size: {}", result.size());
        for (Team team : result) {
            log.info("team : {}", team);
        }

        // case 3 엔티티가 가진 모양이 아닌 전혀 다른 결과를 내야 하면, 페치 조인 보다는 일반 조인을 사용하고
        // 필요한 데이터들만 조회해서 DTO로 반환하는 것이 효과적
    }
}
