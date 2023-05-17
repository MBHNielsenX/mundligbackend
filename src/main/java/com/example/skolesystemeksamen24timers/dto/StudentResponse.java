package com.example.skolesystemeksamen24timers.dto;

import com.example.skolesystemeksamen24timers.entity.Student;
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
public class StudentResponse {
    private Long id;

    private String name;

    private String emailAddress;

    private List<CourseResponse> courses;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.emailAddress = student.getEmailAddress();
        if (student.getCourses() != null) {
           this.courses = student.getCourses().stream().map(CourseResponse::new).collect(Collectors.toList());
       }

    }
}
//