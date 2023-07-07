package com.example.mapping;

import com.example.mapping.domain.embedded.*;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootTest
@Transactional
public class EmbeddedTest {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("embedded")
    @Commit
    void embeddedTest() {
        Address address = new Address("city", "street", "state", new Zipcode("zip", "code"));

        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setHomeAddress(address);
        em.persist(member1);

//        Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//        Member member2 = new Member();
//        member2.setUsername("member2");
//        member2.setHomeAddress(copyAddress);
//        em.persist(member2);
//
//        member1.getHomeAddress().setCity("newCity");
    }

    @Test
    @DisplayName("embedded test")
    @Commit
    void embedded() {
        Address address = new Address("city", "street", "state", new Zipcode("zip", "code"));
        LocalDateTime startDate = LocalDateTime.parse("202010260900", DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        Period period = new Period(startDate, LocalDateTime.now());
        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setHomeAddress(address);
        member1.setWorkPeriod(period);

        PhoneNumberProvider provider1 = new PhoneNumberProvider();
        provider1.setName("provider1");
        em.persist(provider1);

        member1.setPhoneNumber(new PhoneNumber("area1", "local1", provider1));
        em.persist(member1);
    }

}
