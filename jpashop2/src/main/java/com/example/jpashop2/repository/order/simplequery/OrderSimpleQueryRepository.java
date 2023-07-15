package com.example.jpashop2.repository.order.simplequery;

import com.example.jpashop2.repository.OrderSimpleQueryDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new com.example.jpashop2.repository.OrderSimpleQueryDto(" +
                "o.id, m.name, o.orderDate, o.status, d.address) from Order o " +
                "join o.member m " +
                "join o.delivery d", OrderSimpleQueryDto.class).getResultList();
    }

}
