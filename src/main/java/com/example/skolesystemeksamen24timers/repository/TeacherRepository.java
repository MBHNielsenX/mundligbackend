package com.example.skolesystemeksamen24timers.repository;

import com.example.skolesystemeksamen24timers.dto.TeacherResponse;
import com.example.skolesystemeksamen24timers.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByName(String name);


}
//