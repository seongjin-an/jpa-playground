package com.example.jpashop2.repository;

import com.example.jpashop2.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        String jpql = "SELECT o FROM Order o join o.member m ";
        boolean isFirstCondition = true;

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " WHERE ";
                isFirstCondition = false;
            } else {
                jpql += " AND ";
            }
            jpql += " o.status = :status ";
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " WHERE ";
                isFirstCondition = false;
            } else {
                jpql += " AND ";
            }
            jpql += " m.name LIKE :name ";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery("select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d", Order.class).getResultList();
    }

//    public List<OrderSimpleQueryDto> findOrderDtos() {
//        return em.createQuery("select new com.example.jpashop2.repository.OrderSimpleQueryDto(" +
//                "o.id, m.name, o.orderDate, o.status, d.address) from Order o " +
//                "join o.member m " +
//                "join o.delivery d", OrderSimpleQueryDto.class).getResultList();
//    }
}