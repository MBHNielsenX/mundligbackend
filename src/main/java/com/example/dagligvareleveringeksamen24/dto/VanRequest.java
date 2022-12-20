package com.example.dagligvareleveringeksamen24.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VanRequest {
    private Long id;

    private String brand;

    private String model;

    private int capacity;

    List<Long> deliveryIds;
}
