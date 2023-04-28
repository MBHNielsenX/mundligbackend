package com.example.skolesystemeksamen24timers.service;

import com.example.skolesystemeksamen24timers.dto.TeacherRequest;
import com.example.skolesystemeksamen24timers.dto.TeacherResponse;
import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.example.skolesystemeksamen24timers.entity.Student;
import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.repository.TeacherRepository;
import com.example.skolesystemeksamen24timers.repository.CourseRepository;
import com.example.skolesystemeksamen24timers.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeacherServiceTest {
    public static TeacherService teacherService;
    public static TeacherRepository teacherRepository;
    public static StudentRepository studentRepository;
    public static CourseRepository courseRepository;



    @BeforeAll
    public static void dataInitializer(@Autowired TeacherRepository deliveryRepo,
                                       @Autowired StudentRepository productRepo,
                                       @Autowired CourseRepository productOrderRepo) {
        teacherRepository = deliveryRepo;
        studentRepository = productRepo;
        courseRepository = productOrderRepo;
        courseRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();



    }

    @BeforeEach
    public void setUp() {
        teacherService = new TeacherService(teacherRepository, studentRepository, courseRepository);
    }

    @Test
    void getAll() {

    }

    @Test
    void getById() {


    }

    @Test
    void addTeacher() {


    }

    @Test
    void editTeacher() {


    }

    @Test
    void deleteTeacher() {

    }


}