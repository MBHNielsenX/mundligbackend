package com.example.skolesystemeksamen24timers.service;

import com.example.skolesystemeksamen24timers.dto.StudentRequest;
import com.example.skolesystemeksamen24timers.dto.StudentResponse;
import com.example.skolesystemeksamen24timers.entity.Student;
import com.example.skolesystemeksamen24timers.repository.CourseRepository;
import com.example.skolesystemeksamen24timers.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentServiceTest {

    public static StudentService studentService;
    public static StudentRepository studentRepository;

    public static CourseRepository courseRepository;


    @BeforeAll
    public static void dataInitializer(@Autowired StudentRepository productRepo,
                                       @Autowired CourseRepository productOrderRepo) {
        studentRepository = productRepo;
        courseRepository = productOrderRepo;
        courseRepository.deleteAll();
        studentRepository.deleteAll();


    }

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository, courseRepository);
    }



}