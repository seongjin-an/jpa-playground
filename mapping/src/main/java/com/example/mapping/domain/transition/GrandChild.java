package com.example.mapping.domain.transition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class GrandChild {

    @Id @GeneratedValue
    @Column(name = "GRAND_CHILD_ID")
    private Long id;

    private String name;


}
