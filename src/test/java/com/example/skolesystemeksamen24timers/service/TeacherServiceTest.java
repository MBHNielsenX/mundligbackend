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


    /*
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

        Student student1 = Student.builder()
                .id(1L)
                .name("Milk")
                .price(10.0)
                .weight(1.0)
                .build();
        Student student2 = Student.builder()
                .id(2L)
                .name("Bread")
                .price(20.0)
                .weight(2.0)
                .build();
        Student student3 = Student.builder()
                .id(3L)
                .name("Eggs")
                .price(30.0)
                .weight(3.0)
                .build();
        System.out.println(student1);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        Course course1 = Course.builder()
                .student(student1)
                .quantity(1)
                .build();
        Course course2 = Course.builder()
                .student(student2)
                .quantity(2)
                .build();
        Course course3 = Course.builder()
                .student(student3)
                .quantity(3)
                .build();
        Course course4 = Course.builder()
                .student(student1)
                .quantity(4)
                .build();
        Course course5 = Course.builder()
                .student(student2)
                .quantity(5)
                .build();
        Course course6 = Course.builder()
                .student(student3)
                .quantity(6)
                .build();
        Course course7 = Course.builder()
                .student(student1)
                .quantity(7)
                .build();
        Course course8 = Course.builder()
                .student(student2)
                .quantity(8)
                .build();
        Course course9 = Course.builder()
                .student(student3)
                .quantity(9)
                .build();
        Course course10 = Course.builder()
                .student(student1)
                .quantity(10)
                .build();

        System.out.println(course1);
        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
        courseRepository.save(course6);
        courseRepository.save(course7);
        courseRepository.save(course8);
        courseRepository.save(course9);
        courseRepository.save(course10);

        Teacher teacher1 = Teacher.builder()
                .deliveryDate(LocalDate.of(2021, 1, 1))
                .fromWarehouse("w1")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .courses(List.of(course1, course2))
                .build();
        Teacher teacher2 = Teacher.builder()
                .deliveryDate(LocalDate.of(2021, 1, 2))
                .fromWarehouse("w2")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .courses(List.of(course3, course4))
                .build();
        Teacher teacher3 = Teacher.builder()
                .deliveryDate(LocalDate.of(2021, 1, 3))
                .fromWarehouse("w3")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .courses(List.of(course5, course6))
                .build();
        Teacher teacher4 = Teacher.builder()
                .deliveryDate(LocalDate.of(2021, 1, 4))
                .fromWarehouse("w4")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .courses(List.of(course7, course8))
                .build();
        System.out.println("Delivery 1: " + teacher1);
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
        teacherRepository.save(teacher3);
        teacherRepository.save(teacher4);

        course1.setTeacher(teacher1);
        course2.setTeacher(teacher1);
        course3.setTeacher(teacher2);
        course4.setTeacher(teacher2);
        course5.setTeacher(teacher3);
        course6.setTeacher(teacher3);
        course7.setTeacher(teacher4);
        course8.setTeacher(teacher4);
        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
        courseRepository.save(course6);
        courseRepository.save(course7);
        courseRepository.save(course8);

    }

    @BeforeEach
    public void setUp() {
        teacherService = new TeacherService(teacherRepository, studentRepository, courseRepository);
    }

    @Test
    void getAll() {
        List<TeacherResponse> deliveries = teacherService.getAll();
        assertEquals(4, deliveries.size());

        // Check that the first delivery is correct
        assertEquals(LocalDate.of(2021, 1, 1), deliveries.get(0).getDeliveryDate());
        assertEquals("w1", deliveries.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(0).getDestination());
        assertEquals(2, deliveries.get(0).getCourses().size());

        assertEquals("Milk", deliveries.get(0).getCourses().get(0).getStudent().getName());
        assertEquals(10.0, deliveries.get(0).getCourses().get(0).getStudent().getPrice());
        assertEquals(1.0, deliveries.get(0).getCourses().get(0).getStudent().getWeight());

        assertEquals("Bread", deliveries.get(0).getCourses().get(1).getStudent().getName());
        assertEquals(20.0, deliveries.get(0).getCourses().get(1).getStudent().getPrice());
        assertEquals(2.0, deliveries.get(0).getCourses().get(1).getStudent().getWeight());


        // Check that the second delivery is correct
        assertEquals(LocalDate.of(2021, 1, 2), deliveries.get(1).getDeliveryDate());
        assertEquals("w2", deliveries.get(1).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(1).getDestination());
        assertEquals(2, deliveries.get(1).getCourses().size());

        assertEquals("Eggs", deliveries.get(1).getCourses().get(0).getStudent().getName());
        assertEquals(30.0, deliveries.get(1).getCourses().get(0).getStudent().getPrice());
        assertEquals(3.0, deliveries.get(1).getCourses().get(0).getStudent().getWeight());

        assertEquals("Milk", deliveries.get(1).getCourses().get(1).getStudent().getName());
        assertEquals(10.0, deliveries.get(1).getCourses().get(1).getStudent().getPrice());
        assertEquals(1.0, deliveries.get(1).getCourses().get(1).getStudent().getWeight());


        // Check that the third delivery is correct
        assertEquals(LocalDate.of(2021, 1, 3), deliveries.get(2).getDeliveryDate());
        assertEquals("w3", deliveries.get(2).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(2).getDestination());
        assertEquals(2, deliveries.get(2).getCourses().size());

        assertEquals("Bread", deliveries.get(2).getCourses().get(0).getStudent().getName());
        assertEquals(20.0, deliveries.get(2).getCourses().get(0).getStudent().getPrice());
        assertEquals(2.0, deliveries.get(2).getCourses().get(0).getStudent().getWeight());

        assertEquals("Eggs", deliveries.get(2).getCourses().get(1).getStudent().getName());
        assertEquals(30.0, deliveries.get(2).getCourses().get(1).getStudent().getPrice());
        assertEquals(3.0, deliveries.get(2).getCourses().get(1).getStudent().getWeight());


        // Check that the fourth delivery is correct
        assertEquals(LocalDate.of(2021, 1, 4), deliveries.get(3).getDeliveryDate());
        assertEquals("w4", deliveries.get(3).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(3).getDestination());
        assertEquals(2, deliveries.get(3).getCourses().size());

        assertEquals("Milk", deliveries.get(3).getCourses().get(0).getStudent().getName());
        assertEquals(10.0, deliveries.get(3).getCourses().get(0).getStudent().getPrice());
        assertEquals(1.0, deliveries.get(3).getCourses().get(0).getStudent().getWeight());

        assertEquals("Bread", deliveries.get(3).getCourses().get(1).getStudent().getName());
        assertEquals(20.0, deliveries.get(3).getCourses().get(1).getStudent().getPrice());
        assertEquals(2.0, deliveries.get(3).getCourses().get(1).getStudent().getWeight());




    }

    @Test
    void getById() {
        TeacherResponse delivery = teacherService.getById(1L);
        assertEquals(LocalDate.of(2021, 1, 1), delivery.getDeliveryDate());
        assertEquals("w1", delivery.getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", delivery.getDestination());
        assertEquals(2, delivery.getCourses().size());

        assertEquals("Milk", delivery.getCourses().get(0).getStudent().getName());
        assertEquals(10.0, delivery.getCourses().get(0).getStudent().getPrice());
        assertEquals(1.0, delivery.getCourses().get(0).getStudent().getWeight());

        assertEquals("Bread", delivery.getCourses().get(1).getStudent().getName());
        assertEquals(20.0, delivery.getCourses().get(1).getStudent().getPrice());
        assertEquals(2.0, delivery.getCourses().get(1).getStudent().getWeight());

    }

    @Test
    void addDelivery() {
        List<TeacherResponse> deliveries = teacherService.getAll();
        assertEquals(4, deliveries.size());

        TeacherRequest teacherRequest = TeacherRequest.builder()
                .deliveryDate(LocalDate.of(2021, 1, 5))
                .fromWarehouse("w5")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrderIds(List.of(9L, 10L))
                .build();
        teacherService.addDelivery(teacherRequest);

        List<TeacherResponse> deliveriesAfterAdd = teacherService.getAll();
        assertEquals(5, deliveriesAfterAdd.size());

        assertEquals(LocalDate.of(2021, 1, 5), deliveriesAfterAdd.get(4).getDeliveryDate());
        assertEquals("w5", deliveriesAfterAdd.get(4).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveriesAfterAdd.get(4).getDestination());
        assertEquals(2, deliveriesAfterAdd.get(4).getCourses().size());

        assertEquals("Eggs", deliveriesAfterAdd.get(4).getCourses().get(0).getStudent().getName());
        assertEquals(30.0, deliveriesAfterAdd.get(4).getCourses().get(0).getStudent().getPrice());
        assertEquals(3.0, deliveriesAfterAdd.get(4).getCourses().get(0).getStudent().getWeight());

        assertEquals("Milk", deliveriesAfterAdd.get(4).getCourses().get(1).getStudent().getName());
        assertEquals(10.0, deliveriesAfterAdd.get(4).getCourses().get(1).getStudent().getPrice());
        assertEquals(1.0, deliveriesAfterAdd.get(4).getCourses().get(1).getStudent().getWeight());

    }

    @Test
    void editDelivery() {
        List<TeacherResponse> deliveries = teacherService.getAll();
        assertEquals(4, deliveries.size());

        // Check that the first delivery is correct
        assertEquals(LocalDate.of(2021, 1, 1), deliveries.get(0).getDeliveryDate());
        assertEquals("w1", deliveries.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(0).getDestination());
        assertEquals(2, deliveries.get(0).getCourses().size());

        assertEquals("Milk", deliveries.get(0).getCourses().get(0).getStudent().getName());
        assertEquals(10.0, deliveries.get(0).getCourses().get(0).getStudent().getPrice());
        assertEquals(1.0, deliveries.get(0).getCourses().get(0).getStudent().getWeight());

        assertEquals("Bread", deliveries.get(0).getCourses().get(1).getStudent().getName());
        assertEquals(20.0, deliveries.get(0).getCourses().get(1).getStudent().getPrice());
        assertEquals(2.0, deliveries.get(0).getCourses().get(1).getStudent().getWeight());


        // Edit the first delivery
        TeacherRequest teacherRequest = TeacherRequest.builder()
                .id(1L)
                .deliveryDate(LocalDate.of(2021, 1, 5))
                .fromWarehouse("w5")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrderIds(List.of(9L, 10L))
                .build();
        teacherService.editDelivery(teacherRequest);

        List<TeacherResponse> deliveriesAfterEdit = teacherService.getAll();
        assertEquals(4, deliveriesAfterEdit.size());

        // Check that the first delivery has been edited
        assertEquals(LocalDate.of(2021, 1, 5), deliveriesAfterEdit.get(0).getDeliveryDate());
        assertEquals("w5", deliveriesAfterEdit.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveriesAfterEdit.get(0).getDestination());
        assertEquals(2, deliveriesAfterEdit.get(0).getCourses().size());

        assertEquals("Eggs", deliveriesAfterEdit.get(0).getCourses().get(0).getStudent().getName());
        assertEquals(30.0, deliveriesAfterEdit.get(0).getCourses().get(0).getStudent().getPrice());
        assertEquals(3.0, deliveriesAfterEdit.get(0).getCourses().get(0).getStudent().getWeight());

        assertEquals("Milk", deliveriesAfterEdit.get(0).getCourses().get(1).getStudent().getName());
        assertEquals(10.0, deliveriesAfterEdit.get(0).getCourses().get(1).getStudent().getPrice());
        assertEquals(1.0, deliveriesAfterEdit.get(0).getCourses().get(1).getStudent().getWeight());


    }

    @Test
    void deleteDelivery() {
        List<TeacherResponse> deliveries = teacherService.getAll();
        assertEquals(4, deliveries.size());

        // Check first delivery
        assertEquals(LocalDate.of(2021, 1, 1), deliveries.get(0).getDeliveryDate());
        assertEquals("w1", deliveries.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(0).getDestination());
        assertEquals(2, deliveries.get(0).getCourses().size());

        assertEquals("Milk", deliveries.get(0).getCourses().get(0).getStudent().getName());
        assertEquals(10.0, deliveries.get(0).getCourses().get(0).getStudent().getPrice());
        assertEquals(1.0, deliveries.get(0).getCourses().get(0).getStudent().getWeight());

        assertEquals("Bread", deliveries.get(0).getCourses().get(1).getStudent().getName());
        assertEquals(20.0, deliveries.get(0).getCourses().get(1).getStudent().getPrice());
        assertEquals(2.0, deliveries.get(0).getCourses().get(1).getStudent().getWeight());


        teacherService.deleteDelivery(1L);

        List<TeacherResponse> deliveriesAfterDelete = teacherService.getAll();
        assertEquals(3, deliveriesAfterDelete.size());

        // Check that the first delivery has been deleted
        assertEquals(LocalDate.of(2021, 1, 2), deliveriesAfterDelete.get(0).getDeliveryDate());
        assertEquals("w2", deliveriesAfterDelete.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveriesAfterDelete.get(0).getDestination());
        assertEquals(2, deliveriesAfterDelete.get(0).getCourses().size());

        assertEquals("Eggs", deliveriesAfterDelete.get(0).getCourses().get(0).getStudent().getName());
        assertEquals(30.0, deliveriesAfterDelete.get(0).getCourses().get(0).getStudent().getPrice());
        assertEquals(3.0, deliveriesAfterDelete.get(0).getCourses().get(0).getStudent().getWeight());

        assertEquals("Milk", deliveriesAfterDelete.get(0).getCourses().get(1).getStudent().getName());
        assertEquals(10.0, deliveriesAfterDelete.get(0).getCourses().get(1).getStudent().getPrice());
        assertEquals(1.0, deliveriesAfterDelete.get(0).getCourses().get(1).getStudent().getWeight());

        assertEquals("Bread", deliveriesAfterDelete.get(0).getCourses().get(1).getStudent().getName());
        assertEquals(20.0, deliveriesAfterDelete.get(0).getCourses().get(1).getStudent().getPrice());
        assertEquals(2.0, deliveriesAfterDelete.get(0).getCourses().get(1).getStudent().getWeight());

    }

     */
}