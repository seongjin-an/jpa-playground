package com.example.mapping;

import com.example.mapping.domain.embeddedCollection.Address;
import com.example.mapping.domain.embeddedCollection.Unit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
public class EmbeddedCollectionTest {

    @PersistenceContext
    EntityManager em;

    @DisplayName("embedded collection test")
    @Test
    @Commit
    public void embeddedCollectionTest() throws Exception{
        Unit member = new Unit();
        member.setHomeAddress(new Address("city", "street", "zipcode"));

        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        // 임베디드 값 타입 컬렉션
        member.getAddressHistory().add(new Address("서울", "강남", "123-123"));
        member.getAddressHistory().add(new Address("서울", "강북", "000-000"));

        em.persist(member);

        em.flush();
        em.clear();

        Unit foundMember = em.find(Unit.class, 1L);
        log.info("foundMember: {}", foundMember);
    }

}
