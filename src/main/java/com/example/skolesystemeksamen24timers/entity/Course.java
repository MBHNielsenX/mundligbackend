package com.example.skolesystemeksamen24timers.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"students"})

@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private LocalDate startDate;

    @Column(length = 100, nullable = false)
    private LocalDate endDate;

    @Column(length = 100, nullable = false)
    private int ectsPoints;

    @Column(length = 100, nullable = false)
    private int maxStudents;

    @ManyToMany()
    @JsonIgnoreProperties("courses")
    private List<Student> students;

    @ManyToOne()
    @JsonIgnoreProperties("courses")
    private Teacher teacher;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}

