package com.example.skolesystemeksamen24timers.service;

import com.example.skolesystemeksamen24timers.dto.CourseRequest;
import com.example.skolesystemeksamen24timers.dto.CourseResponse;
import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.entity.Student;
import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.example.skolesystemeksamen24timers.repository.CourseRepository;
import com.example.skolesystemeksamen24timers.repository.StudentRepository;
import com.example.skolesystemeksamen24timers.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseServiceTest {

    public static CourseService courseService;
    public static CourseRepository courseRepository;
    public static StudentRepository studentRepository;
    public static StudentService studentService;
    public static TeacherRepository teacherRepository;

    @BeforeAll
    public static void dataInitializer(@Autowired StudentRepository studentRepo,
                                       @Autowired CourseRepository courseRepo,
                                       @Autowired TeacherRepository teacherRepo) {
        studentRepository = studentRepo;
        courseRepository = courseRepo;
        teacherRepository = teacherRepo;
        //courseRepository.deleteAll();
        //studentRepository.deleteAll();
        //teacherRepository.deleteAll();

        Student student1 = Student.builder()
                .name("Student1")
                .emailAddress("Student1@gmail.com")
                .build();
        Student student2 = Student.builder()
                .name("Student2")
                .emailAddress("Student2@gmail.com")
                .build();
        Student student3 = Student.builder()
                .name("Student3")
                .emailAddress("Student3@gmail.com")
                .build();
        Student student4 = Student.builder()
                .name("Student4")
                .emailAddress("Student4@gmail.com")
                .build();
        Student student5 = Student.builder()
                .name("Student5")
                .emailAddress("Student5@gmail.com")
                .build();
        Student student6 = Student.builder()
                .name("Student6")
                .emailAddress("Student6@gmail.com")
                .build();
        Student student7 = Student.builder()
                .name("Student7")
                .emailAddress("Student7@gmail.com")
                .build();
        Student student8 = Student.builder()
                .name("Student8")
                .emailAddress("Student8@gmail.com")
                .build();
        Student student9 = Student.builder()
                .name("Student9")
                .emailAddress("Student9@gmail.com")
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        studentRepository.save(student7);
        studentRepository.save(student8);
        studentRepository.save(student9);

        Teacher teacher1 = Teacher.builder()
                .name("Teacher1")
                .emailAddress("Teacher1@gmail.com")
                .build();
        Teacher teacher2 = Teacher.builder()
                .name("Teacher2")
                .emailAddress("Teacher2@gmail.com")
                .build();
        Teacher teacher3 = Teacher.builder()
                .name("Teacher3")
                .emailAddress("Teacher3@gmail.com")
                .build();

        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
        teacherRepository.save(teacher3);

        Course course1 = Course.builder()
                .name("Course1")
                .startDate(LocalDate.of(2021, 1, 1))
                .endDate(LocalDate.of(2021, 1, 31))
                .ectsPoints(1)
                .maxStudents(5)
                .teacher(teacher1)
                .students(List.of(student1, student2, student3))
                .build();
        Course course2 = Course.builder()
                .name("Course2")
                .startDate(LocalDate.of(2021, 2, 1))
                .endDate(LocalDate.of(2021, 2, 28))
                .ectsPoints(2)
                .maxStudents(5)
                .teacher(teacher2)
                .students(List.of(student4, student5, student6))
                .build();
        Course course3 = Course.builder()
                .name("Course3")
                .startDate(LocalDate.of(2021, 3, 1))
                .endDate(LocalDate.of(2021, 3, 31))
                .ectsPoints(3)
                .maxStudents(5)
                .teacher(teacher3)
                .students(List.of(student7, student8, student9))
                .build();
        Course course4 = Course.builder()
                .name("Course4")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 4, 30))
                .ectsPoints(4)
                .maxStudents(5)
                .teacher(teacher1)
                .students(List.of(student1, student2, student3))
                .build();
        Course course5 = Course.builder()
                .name("Course5")
                .startDate(LocalDate.of(2021, 5, 1))
                .endDate(LocalDate.of(2021, 5, 31))
                .ectsPoints(5)
                .maxStudents(5)
                .teacher(teacher2)
                .students(List.of(student4, student5, student6))
                .build();
        Course course6 = Course.builder()
                .name("Course6")
                .startDate(LocalDate.of(2021, 6, 1))
                .endDate(LocalDate.of(2021, 6, 30))
                .ectsPoints(6)
                .maxStudents(5)
                .teacher(teacher3)
                .students(List.of(student7, student8, student9))
                .build();


        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
        courseRepository.save(course6);

        teacher1.setCourses(List.of(course1, course4));
        teacher2.setCourses(List.of(course2, course5));
        teacher3.setCourses(List.of(course3, course6));
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
        teacherRepository.save(teacher3);

        student1.setCourses(List.of(course1, course4));
        student2.setCourses(List.of(course1, course4));
        student3.setCourses(List.of(course1, course4));
        student4.setCourses(List.of(course2, course5));
        student5.setCourses(List.of(course2, course5));
        student6.setCourses(List.of(course2, course5));
        student7.setCourses(List.of(course3, course6));
        student8.setCourses(List.of(course3, course6));
        student9.setCourses(List.of(course3, course6));
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        studentRepository.save(student7);
        studentRepository.save(student8);
        studentRepository.save(student9);



    }

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseRepository, studentRepository, teacherRepository);
        studentService = new StudentService(studentRepository, courseRepository);
    }



    @Test
    void getAll() {
        List<CourseResponse> courses = courseService.getAll();
        assertEquals(6, courses.size());

        assertEquals("Course1", courses.get(0).getName());
        assertEquals(LocalDate.of(2021, 1, 1), courses.get(0).getStartDate());
        assertEquals(LocalDate.of(2021, 1, 31), courses.get(0).getEndDate());
        assertEquals(1, courses.get(0).getEctsPoints());
        assertEquals(5, courses.get(0).getMaxStudents());
        assertEquals("Teacher1", courses.get(0).getTeacher().getName());
        assertEquals(3, courses.get(0).getStudents().size());
        assertEquals("Student1", courses.get(0).getStudents().get(0).getName());
        assertEquals("Student2", courses.get(0).getStudents().get(1).getName());
        assertEquals("Student3", courses.get(0).getStudents().get(2).getName());

        assertEquals("Course2", courses.get(1).getName());
        assertEquals(LocalDate.of(2021, 2, 1), courses.get(1).getStartDate());
        assertEquals(LocalDate.of(2021, 2, 28), courses.get(1).getEndDate());
        assertEquals(2, courses.get(1).getEctsPoints());
        assertEquals(5, courses.get(1).getMaxStudents());
        assertEquals("Teacher2", courses.get(1).getTeacher().getName());
        assertEquals(3, courses.get(1).getStudents().size());
        assertEquals("Student4", courses.get(1).getStudents().get(0).getName());
        assertEquals("Student5", courses.get(1).getStudents().get(1).getName());
        assertEquals("Student6", courses.get(1).getStudents().get(2).getName());

        assertEquals("Course3", courses.get(2).getName());
        assertEquals(LocalDate.of(2021, 3, 1), courses.get(2).getStartDate());
        assertEquals(LocalDate.of(2021, 3, 31), courses.get(2).getEndDate());
        assertEquals(3, courses.get(2).getEctsPoints());
        assertEquals(5, courses.get(2).getMaxStudents());
        assertEquals("Teacher3", courses.get(2).getTeacher().getName());
        assertEquals(3, courses.get(2).getStudents().size());
        assertEquals("Student7", courses.get(2).getStudents().get(0).getName());
        assertEquals("Student8", courses.get(2).getStudents().get(1).getName());
        assertEquals("Student9", courses.get(2).getStudents().get(2).getName());

        assertEquals("Course4", courses.get(3).getName());
        assertEquals(LocalDate.of(2021, 4, 1), courses.get(3).getStartDate());
        assertEquals(LocalDate.of(2021, 4, 30), courses.get(3).getEndDate());
        assertEquals(4, courses.get(3).getEctsPoints());
        assertEquals(5, courses.get(3).getMaxStudents());
        assertEquals("Teacher1", courses.get(3).getTeacher().getName());
        assertEquals(3, courses.get(3).getStudents().size());
        assertEquals("Student1", courses.get(3).getStudents().get(0).getName());
        assertEquals("Student2", courses.get(3).getStudents().get(1).getName());
        assertEquals("Student3", courses.get(3).getStudents().get(2).getName());

        assertEquals("Course5", courses.get(4).getName());
        assertEquals(LocalDate.of(2021, 5, 1), courses.get(4).getStartDate());
        assertEquals(LocalDate.of(2021, 5, 31), courses.get(4).getEndDate());
        assertEquals(5, courses.get(4).getEctsPoints());
        assertEquals(5, courses.get(4).getMaxStudents());
        assertEquals("Teacher2", courses.get(4).getTeacher().getName());
        assertEquals(3, courses.get(4).getStudents().size());
        assertEquals("Student4", courses.get(4).getStudents().get(0).getName());
        assertEquals("Student5", courses.get(4).getStudents().get(1).getName());
        assertEquals("Student6", courses.get(4).getStudents().get(2).getName());

        assertEquals("Course6", courses.get(5).getName());
        assertEquals(LocalDate.of(2021, 6, 1), courses.get(5).getStartDate());
        assertEquals(LocalDate.of(2021, 6, 30), courses.get(5).getEndDate());
        assertEquals(6, courses.get(5).getEctsPoints());
        assertEquals(5, courses.get(5).getMaxStudents());
        assertEquals("Teacher3", courses.get(5).getTeacher().getName());
        assertEquals(3, courses.get(5).getStudents().size());
        assertEquals("Student7", courses.get(5).getStudents().get(0).getName());
        assertEquals("Student8", courses.get(5).getStudents().get(1).getName());
        assertEquals("Student9", courses.get(5).getStudents().get(2).getName());


    }


    @Test
    void getCourseById() {
        CourseResponse course = courseService.getCourseById(1L);
        assertEquals("Course1", course.getName());
        assertEquals(LocalDate.of(2021, 1, 1), course.getStartDate());
        assertEquals(LocalDate.of(2021, 1, 31), course.getEndDate());
        assertEquals(1, course.getEctsPoints());
        assertEquals(5, course.getMaxStudents());
        assertEquals("Teacher1", course.getTeacher().getName());
        assertEquals(3, course.getStudents().size());
        assertEquals("Student1", course.getStudents().get(0).getName());
        assertEquals("Student2", course.getStudents().get(1).getName());
        assertEquals("Student3", course.getStudents().get(2).getName());


    }

    @Test
    void addCourse() {
        List<CourseResponse> course = courseService.getAll();
        assertEquals(6, course.size());

        CourseRequest courseRequest = CourseRequest.builder()
                        .name("Danish")
                        .startDate(LocalDate.of(2021, 1, 1))
                        .endDate(LocalDate.of(2021, 1, 31))
                        .ectsPoints(1)
                        .maxStudents(5)
                        .teacherId(1L)
                        .studentIds(List.of(1L, 2L, 3L))
                        .build();
        courseService.addCourse(courseRequest);

        List<CourseResponse> course2 = courseService.getAll();
        assertEquals(7, course2.size());

        assertEquals("Danish", course2.get(6).getName());
        assertEquals(LocalDate.of(2021, 1, 1), course2.get(6).getStartDate());
        assertEquals(LocalDate.of(2021, 1, 31), course2.get(6).getEndDate());
        assertEquals(1, course2.get(6).getEctsPoints());
        assertEquals(5, course2.get(6).getMaxStudents());
        assertEquals("Teacher1", course2.get(6).getTeacher().getName());
        assertEquals(3, course2.get(6).getStudents().size());
        assertEquals("Student1", course2.get(6).getStudents().get(0).getName());
        assertEquals("Student2", course2.get(6).getStudents().get(1).getName());
        assertEquals("Student3", course2.get(6).getStudents().get(2).getName());

    }

    @Test
    void editCourse() {
        List<CourseResponse> course = courseService.getAll();
        assertEquals(6, course.size());

        assertEquals("Course1", course.get(0).getName());
        assertEquals(LocalDate.of(2021, 1, 1), course.get(0).getStartDate());
        assertEquals(LocalDate.of(2021, 1, 31), course.get(0).getEndDate());
        assertEquals(1, course.get(0).getEctsPoints());
        assertEquals(5, course.get(0).getMaxStudents());
        assertEquals("Teacher1", course.get(0).getTeacher().getName());
        assertEquals(3, course.get(0).getStudents().size());
        assertEquals("Student1", course.get(0).getStudents().get(0).getName());
        assertEquals("Student2", course.get(0).getStudents().get(1).getName());
        assertEquals("Student3", course.get(0).getStudents().get(2).getName());

        CourseRequest courseRequest = CourseRequest.builder()
                .id(1L)
                .name("English")
                .startDate(LocalDate.of(2021, 2, 2))
                .endDate(LocalDate.of(2021, 2, 28))
                .ectsPoints(2)
                .maxStudents(10)
                .teacherId(2L)
                .studentIds(List.of(4L, 5L, 6L))
                .build();
        courseService.editCourse(courseRequest);

        List<CourseResponse> course2 = courseService.getAll();
        assertEquals(6, course2.size());

        assertEquals("English", course2.get(0).getName());
        assertEquals(LocalDate.of(2021, 2, 2), course2.get(0).getStartDate());
        assertEquals(LocalDate.of(2021, 2, 28), course2.get(0).getEndDate());
        assertEquals(2, course2.get(0).getEctsPoints());
        assertEquals(10, course2.get(0).getMaxStudents());
        assertEquals("Teacher2", course2.get(0).getTeacher().getName());
        assertEquals(3, course2.get(0).getStudents().size());
        assertEquals("Student4", course2.get(0).getStudents().get(0).getName());
        assertEquals("Student5", course2.get(0).getStudents().get(1).getName());
        assertEquals("Student6", course2.get(0).getStudents().get(2).getName());

    }
/*
    @Test
    void deleteCourse() {
        List<CourseResponse> course = courseService.getAll();
        assertEquals(6, course.size());

        assertEquals("Course1", course.get(0).getName());
        assertEquals(LocalDate.of(2021, 1, 1), course.get(0).getStartDate());
        assertEquals(LocalDate.of(2021, 1, 31), course.get(0).getEndDate());
        assertEquals(1, course.get(0).getEctsPoints());
        assertEquals(5, course.get(0).getMaxStudents());
        assertEquals("Teacher1", course.get(0).getTeacher().getName());
        assertEquals(3, course.get(0).getStudents().size());
        assertEquals("Student1", course.get(0).getStudents().get(0).getName());
        assertEquals("Student2", course.get(0).getStudents().get(1).getName());
        assertEquals("Student3", course.get(0).getStudents().get(2).getName());

        courseService.deleteProductOrder(1L);

        List<CourseResponse> course2 = courseService.getAll();
        assertEquals(5, course2.size());

        assertEquals("Course2", course2.get(0).getName());
        assertEquals(LocalDate.of(2021, 2, 1), course2.get(0).getStartDate());
        assertEquals(LocalDate.of(2021, 2, 28), course2.get(0).getEndDate());
        assertEquals(2, course2.get(0).getEctsPoints());
        assertEquals(5, course2.get(0).getMaxStudents());
        assertEquals("Teacher2", course2.get(0).getTeacher().getName());
        assertEquals(3, course2.get(0).getStudents().size());
        assertEquals("Student4", course2.get(0).getStudents().get(0).getName());
        assertEquals("Student5", course2.get(0).getStudents().get(1).getName());
        assertEquals("Student6", course2.get(0).getStudents().get(2).getName());

        assertEquals("Course3", course2.get(1).getName());
        assertEquals(LocalDate.of(2021, 3, 1), course2.get(1).getStartDate());
        assertEquals(LocalDate.of(2021, 3, 31), course2.get(1).getEndDate());
        assertEquals(3, course2.get(1).getEctsPoints());
        assertEquals(5, course2.get(1).getMaxStudents());
        assertEquals("Teacher3", course2.get(1).getTeacher().getName());
        assertEquals(3, course2.get(1).getStudents().size());
        assertEquals("Student7", course2.get(1).getStudents().get(0).getName());
        assertEquals("Student8", course2.get(1).getStudents().get(1).getName());
        assertEquals("Student9", course2.get(1).getStudents().get(2).getName());

    }
    
 */

}