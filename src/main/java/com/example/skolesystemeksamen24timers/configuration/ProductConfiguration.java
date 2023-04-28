package com.example.skolesystemeksamen24timers.configuration;

import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.example.skolesystemeksamen24timers.entity.Student;
import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.repository.TeacherRepository;
import com.example.skolesystemeksamen24timers.repository.CourseRepository;
import com.example.skolesystemeksamen24timers.repository.StudentRepository;
import com.example.skolesystemeksamen24timers.service.StudentService;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProductConfiguration implements ApplicationRunner {

    StudentRepository studentRepository;
    StudentService studentService;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public ProductConfiguration(StudentRepository studentRepository, StudentService studentService,
                                CourseRepository courseRepository,
                                TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;

    }

    @Override
    @SneakyThrows
    public void run(ApplicationArguments args) throws Exception {


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
                .maxStudents(30)
                .teacher(teacher1)
                .students(List.of(student1, student2, student3))
                .build();
        Course course2 = Course.builder()
                .name("Course2")
                .startDate(LocalDate.of(2021, 2, 1))
                .endDate(LocalDate.of(2021, 2, 28))
                .ectsPoints(2)
                .maxStudents(30)
                .teacher(teacher2)
                .students(List.of(student4, student5, student6))
                .build();
        Course course3 = Course.builder()
                .name("Course3")
                .startDate(LocalDate.of(2021, 3, 1))
                .endDate(LocalDate.of(2021, 3, 31))
                .ectsPoints(3)
                .maxStudents(30)
                .teacher(teacher3)
                .students(List.of(student7, student8, student9))
                .build();
        Course course4 = Course.builder()
                .name("Course4")
                .startDate(LocalDate.of(2021, 4, 1))
                .endDate(LocalDate.of(2021, 4, 30))
                .ectsPoints(4)
                .maxStudents(30)
                .teacher(teacher1)
                .students(List.of(student1, student2, student3))
                .build();
        Course course5 = Course.builder()
                .name("Course5")
                .startDate(LocalDate.of(2021, 5, 1))
                .endDate(LocalDate.of(2021, 5, 31))
                .ectsPoints(5)
                .maxStudents(30)
                .teacher(teacher2)
                .students(List.of(student4, student5, student6))
                .build();
        Course course6 = Course.builder()
                .name("Course6")
                .startDate(LocalDate.of(2021, 6, 1))
                .endDate(LocalDate.of(2021, 6, 30))
                .ectsPoints(6)
                .maxStudents(30)
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


}
//