package com.example.skolesystemeksamen24timers.dto;

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
public class StudentRequest {
    private Long id;

    private String name;

    private String emailAddress;

    private List<Long> courses;

}
