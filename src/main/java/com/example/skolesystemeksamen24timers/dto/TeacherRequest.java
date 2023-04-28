package com.example.skolesystemeksamen24timers.dto;

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
public class TeacherRequest {
    private Long id;

    private String name;

    private String emailAddress;

    private List<Long> courseIds;
}
//