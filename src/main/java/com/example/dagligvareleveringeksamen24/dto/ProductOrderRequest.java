package com.example.dagligvareleveringeksamen24.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOrderRequest {
    private Long id;

    private int quantity;

    private Long productId;

    private Long deliveryId;

}
