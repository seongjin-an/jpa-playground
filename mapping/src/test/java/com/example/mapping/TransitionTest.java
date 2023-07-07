package com.example.mapping;

import com.example.mapping.domain.transition.Child;
import com.example.mapping.domain.transition.GrandChild;
import com.example.mapping.domain.transition.Parent;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
@SpringBootTest
@Transactional
public class TransitionTest {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("transition test")
    @Commit
    void transitionTest() {
        Parent parent = Parent.builder().name("parent").build();
        em.persist(parent);

        Child child1 = Child.builder().name("child1").build();

        Child child2 = Child.builder().name("child2").build();
        parent.getChildList().add(child1);
        parent.getChildList().add(child2);
        child1.setParent(parent);
        child2.setParent(parent);
        em.persist(child1);
        em.persist(child2);

        em.flush();
        em.clear();

        Parent foundParent = em.find(Parent.class, parent.getId());
        List<Child> childList = foundParent.getChildList();
        log.info("childList: {}", childList);
        childList.removeIf(child -> child.getName().equals("child2"));
//        Child foundC = childList.stream().filter(c -> c.getName().equals("child1")).findFirst().orElse(null);

//        childList.remove(0);
//        childList.remove(foundC);

//        em.detach(foundParent);
//        em.remove(foundParent);
//        em.clear();

        em.flush();


    }

    @Test
    @DisplayName("cascade test2")
    @Commit
    void cascade2() {
        Parent pr = Parent.builder().name("parent").build();
        em.persist(pr);

        em.flush();
        em.clear();

        Child child = Child.builder().name("child").build();
        Parent parent = em.find(Parent.class, pr.getId());
        child.setParent(parent);
        parent.getChildList().add(child);

        em.flush();

        em.remove(child);
    }

}
