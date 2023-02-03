package com.example.skolesystemeksamen24timers.dto;

import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseRequest {
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private int ectsPoints;

    private int maxStudents;

    private Long teacherId;

    private List<Long> studentIds;

}
