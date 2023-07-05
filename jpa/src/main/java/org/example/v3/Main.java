package org.example.v3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

// ManyToOne 으로 보완
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("team");
            em.persist(team);

            Member member1 = new Member();
            member1.setName("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setTeam(team);
            em.persist(member2);

            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
