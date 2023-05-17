package com.example.skolesystemeksamen24timers.repository;

import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStudents(Student student);
    List<Course> findByTeacher_Id(Long id);

}
//
