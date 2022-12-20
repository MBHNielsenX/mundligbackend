package com.example.dagligvareleveringeksamen24.dto;

import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryRequest {
    private Long id;

    private LocalDate deliveryDate;

    private String fromWarehouse;

    private String destination;

    private List<Long> productOrderIds;


}
