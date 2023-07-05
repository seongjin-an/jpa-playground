package org.example.v1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        System.out.println("begin transaction");
        try {
            Member member1 = new Member();
            member1.setId(1L);
            member1.setName("ansj");
            System.out.println("member1: " + member1);
            em.persist(member1);
            System.out.println("after persist");
            Member foundMember = em.find(Member.class, 1L);
            System.out.println("foundMember = " + foundMember);
//            System.out.println("==================LAZY INSERT=====================");
            Member member2 = new Member(2L, "ansj2");
            Member member3 = new Member(3L, "ansj3");
            em.persist(member2);
            em.persist(member3);
            Member foundMember3 = em.find(Member.class, 3L);
            foundMember3.setName("ansj3333333"); // dirty checking
//            List<Member> list = em.createQuery("SELECT M FROM Member M", Member.class).getResultList();
            System.out.println("===================BEFORE COMMIT==================");
            tx.commit();
            System.out.println("===================AFTER COMMIT===================");
        } catch (Exception error) {
            System.out.println("exception!" + error);
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}