package com.example.mapping.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhoneNumberProvider {
    @Id @GeneratedValue
    @Column(name = "PROVIDER_ID")
    private Long id;
    private String name;
}
