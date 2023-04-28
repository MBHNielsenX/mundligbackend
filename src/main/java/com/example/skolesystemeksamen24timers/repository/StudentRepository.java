package com.example.skolesystemeksamen24timers.repository;

import com.example.skolesystemeksamen24timers.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);
    Boolean existsByName(String name);

}
//
