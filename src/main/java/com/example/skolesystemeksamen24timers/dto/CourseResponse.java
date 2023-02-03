package com.example.skolesystemeksamen24timers.dto;

import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private int ectsPoints;

    private int maxStudents;

    private Teacher teacher;

    private List<StudentResponse> students;

    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
        this.ectsPoints = course.getEctsPoints();
        this.maxStudents = course.getMaxStudents();
        if (course.getTeacher() != null) {
            this.teacher = course.getTeacher();
        }
        if (course.getStudents() != null) {
            this.students = course.getStudents().stream().map(StudentResponse::new).collect(Collectors.toList());
        }
    }
}
