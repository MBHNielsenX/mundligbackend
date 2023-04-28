package com.example.skolesystemeksamen24timers.service;

import com.example.skolesystemeksamen24timers.dto.StudentRequest;
import com.example.skolesystemeksamen24timers.dto.StudentResponse;
import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.entity.Student;
import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.example.skolesystemeksamen24timers.repository.CourseRepository;
import com.example.skolesystemeksamen24timers.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepository;
    CourseRepository courseRepository;
    CourseService courseService;



    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository) {
        this.studentRepository = studentRepository;

        this.courseRepository = courseRepository;
    }

    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> new StudentResponse(student)).toList();
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "student Not Found"));
        return new StudentResponse(student);
    }

    public StudentResponse getStudentByName(String name) {
        if (studentRepository.existsByName(name)) {
            Student student = studentRepository.findByName(name);
            return new StudentResponse(student);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student Not Found");
        }
    }


    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student tempStudent = Student.builder()
                        .name(studentRequest.getName())
                        .emailAddress(studentRequest.getEmailAddress())
                        .build();
        studentRepository.save(tempStudent);
        return new StudentResponse(tempStudent);
    }

    public void editStudent(StudentRequest studentRequest) {
        Student foundStudent = studentRepository.findById(studentRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "student Not Found"));
        if(studentRequest.getName() != null) {
            foundStudent.setName(studentRequest.getName());
        }
        if (studentRequest.getEmailAddress() != null) {
            foundStudent.setEmailAddress(studentRequest.getEmailAddress());
        }
        if (studentRequest.getCourses() != null) {
            List<Course> foundCourses = courseRepository.findAll();
            List<Course> acceptedCourses = null;
            for (int i = 0; i < foundCourses.size(); i++) {
                if (studentRequest.getCourses().contains(foundCourses.get(i).getId())) {
                    acceptedCourses.add(foundCourses.get(i));
                }
            }
            foundStudent.setCourses(acceptedCourses);
        }
        studentRepository.save(foundStudent);


    }

    public void deleteStudent(Long id) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));
        List<Course> courses = foundStudent.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course temp = courses.get(i);
            temp.setStudents(null);
            courseRepository.save(temp);
        }
        studentRepository.delete(foundStudent);
    }


}
//