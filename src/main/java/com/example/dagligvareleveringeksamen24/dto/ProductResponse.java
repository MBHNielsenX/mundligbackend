package com.example.dagligvareleveringeksamen24.dto;

import com.example.dagligvareleveringeksamen24.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private Long id;

    private String name;

    private double price;

    private double weight;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.weight = product.getWeight();
    }
}
