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
    /*

    @BeforeAll
    public static void dataInitializer(@Autowired StudentRepository productRepo,
                                       @Autowired CourseRepository productOrderRepo) {
        studentRepository = productRepo;
        courseRepository = productOrderRepo;
        courseRepository.deleteAll();
        studentRepository.deleteAll();

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
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

    }

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository, courseRepository);
    }

    @Test
    void getAll() {
        List<StudentResponse> products = studentService.getAll();
        assertEquals(3, products.size());

        assertEquals(  "Milk", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(1.0, products.get(0).getWeight());

        assertEquals(  "Bread", products.get(1).getName());
        assertEquals(20.0, products.get(1).getPrice());
        assertEquals(2.0, products.get(1).getWeight());

        assertEquals(  "Eggs", products.get(2).getName());
        assertEquals(30.0, products.get(2).getPrice());
        assertEquals(3.0, products.get(2).getWeight());

    }

    @Test
    void getById() {
        StudentResponse product = studentService.getById(1L);
        assertEquals(  "Milk", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(1.0, product.getWeight());

        assertNotEquals(  "Bread", product.getName());
        assertNotEquals(20.0, product.getPrice());
        assertNotEquals(2.0, product.getWeight());
    }

    @Test
    void addProduct() {
        StudentRequest studentRequest = StudentRequest.builder()
                .id(4L)
                .name("Cheese")
                .price(40.0)
                .weight(4.0)
                .build();
        studentService.addProduct(studentRequest);

        List<StudentResponse> products = studentService.getAll();
        assertEquals(4, products.size());

        assertEquals(  "Milk", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(1.0, products.get(0).getWeight());

        assertEquals(  "Bread", products.get(1).getName());
        assertEquals(20.0, products.get(1).getPrice());
        assertEquals(2.0, products.get(1).getWeight());

        assertEquals(  "Eggs", products.get(2).getName());
        assertEquals(30.0, products.get(2).getPrice());
        assertEquals(3.0, products.get(2).getWeight());

        assertEquals(  "Cheese", products.get(3).getName());
        assertEquals(40.0, products.get(3).getPrice());
        assertEquals(4.0, products.get(3).getWeight());

        assertNotEquals("Bread", products.get(3).getName());
        assertNotEquals("Milk", products.get(3).getName());
        assertNotEquals("Eggs", products.get(3).getName());
    }

    @Test
    void getProductByName() {
        StudentResponse product = studentService.getProductByName("Milk");
        assertEquals(  "Milk", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(1.0, product.getWeight());

        assertNotEquals(  "Bread", product.getName());
        assertNotEquals(20.0, product.getPrice());
        assertNotEquals(2.0, product.getWeight());
    }

    @Test
    void editProduct() {
        List<StudentResponse> products = studentService.getAll();
        assertEquals(3, products.size());
        assertEquals("Eggs", products.get(2).getName());
        assertEquals(30.0, products.get(2).getPrice());
        assertEquals(3.0, products.get(2).getWeight());

        StudentRequest studentRequest = StudentRequest.builder()
                .id(3L)
                .name("Rise")
                .price(50.0)
                .weight(5.0)
                .build();
        studentService.editProduct(studentRequest);

        List<StudentResponse> products2 = studentService.getAll();
        assertEquals(3, products2.size());
        assertEquals("Rise", products2.get(2).getName());
        assertEquals(50.0, products2.get(2).getPrice());
        assertEquals(5.0, products2.get(2).getWeight());

    }

    @Test
    void deleteProduct() {
        List<StudentResponse> products = studentService.getAll();
        assertEquals(3, products.size());

        assertEquals("Milk", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(1.0, products.get(0).getWeight());

        studentService.deleteProduct(1L);



        List<StudentResponse> products2 = studentService.getAll();
        assertEquals(2, products2.size());

        assertNotEquals("Milk", products2.get(0).getName());
        assertNotEquals(10.0, products2.get(0).getPrice());
        assertNotEquals(1.0, products2.get(0).getWeight());

    }

     */
}