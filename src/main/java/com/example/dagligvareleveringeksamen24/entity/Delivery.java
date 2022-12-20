package com.example.dagligvareleveringeksamen24.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private LocalDate deliveryDate;

    @Column(length = 100)
    private String fromWarehouse;

    @Column(length = 100)
    private String destination;

    @OneToMany(mappedBy = "delivery")
    @ToString.Exclude
    private List<ProductOrder> productOrders;

    @ManyToOne
    private Van van;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
