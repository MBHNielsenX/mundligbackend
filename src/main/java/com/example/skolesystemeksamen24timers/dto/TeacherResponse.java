package com.example.skolesystemeksamen24timers.dto;

import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.example.skolesystemeksamen24timers.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherResponse {

        private Long id;

        private String name;

        private String emailAddress;

        private List<CourseResponse> courses;

        public TeacherResponse(Teacher teacher) {
            this.id = teacher.getId();
            this.name = teacher.getName();
            this.emailAddress = teacher.getEmailAddress();
            if (teacher.getCourses() != null) {
                this.courses = teacher.getCourses().stream().map(CourseResponse::new).collect(Collectors.toList());
            }
        }

}
//