package com.example.dagligvareleveringeksamen24.dto;

import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.example.dagligvareleveringeksamen24.entity.Van;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VanResponse {

    private Long id;

    private String brand;

    private String model;

    private int capacity;

    private List<Delivery> deliveries;

    public VanResponse(Van van) {
        this.id = van.getId();
        this.brand = van.getBrand();
        this.model = van.getModel();
        this.capacity = van.getCapacity();
        if (van.getDeliveries() != null) {
            this.deliveries = van.getDeliveries();
        }
    }
}
