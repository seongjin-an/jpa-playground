package org.example.v2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

// oneToMany 단점
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team team = new Team();
//            team.setId(1L);
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            team.addMembers(member);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("member2");
            team.addMembers(member2);
            em.persist(member2);

            tx.commit();
        } catch (Exception err) {
            err.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
