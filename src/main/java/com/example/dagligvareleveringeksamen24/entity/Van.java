package com.example.dagligvareleveringeksamen24.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity(name = "van")
public class Van {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private int capacity;

    @OneToMany(mappedBy = "van")
    @ToString.Exclude
    private List<Delivery> deliveries;
}
