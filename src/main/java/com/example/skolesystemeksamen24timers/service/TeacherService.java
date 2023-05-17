package com.example.skolesystemeksamen24timers.service;

import com.example.skolesystemeksamen24timers.dto.TeacherRequest;
import com.example.skolesystemeksamen24timers.dto.TeacherResponse;
import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.repository.TeacherRepository;
import com.example.skolesystemeksamen24timers.repository.CourseRepository;
import com.example.skolesystemeksamen24timers.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    TeacherRepository teacherRepository;
    StudentRepository studentRepository;
    CourseRepository courseRepository;


    public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository, CourseRepository courseRepository ) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<TeacherResponse> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(teacher -> new TeacherResponse(teacher)).toList();
    }

    public TeacherResponse getById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"teacher not found"));
        return new TeacherResponse(teacher);
    }

    public TeacherResponse getByName(String name) {
        System.out.println(name);
        Teacher teacher = teacherRepository.findByName(name);
        System.out.println(teacher);

        return new TeacherResponse(teacher);
    }


    public TeacherResponse addTeacher(TeacherRequest teacherRequest) {

        Teacher tempTeacher = Teacher.builder()
                .name(teacherRequest.getName())
                .emailAddress(teacherRequest.getEmailAddress())
                .build();
        teacherRepository.save(tempTeacher);

        return new TeacherResponse(tempTeacher);
    }

    public void editTeacher(TeacherRequest teacherRequest) {
        Teacher foundTeacher = teacherRepository.findById(teacherRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"teacher not found"));

    if (teacherRequest.getName() != null) {
        foundTeacher.setName(teacherRequest.getName());
    }
    if (teacherRequest.getEmailAddress() != null) {
        foundTeacher.setEmailAddress(teacherRequest.getEmailAddress());
    }
    if (teacherRequest.getCourseIds() != null) {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < teacherRequest.getCourseIds().size(); i++) {
            Course temp = courseRepository.findById(teacherRequest.getCourseIds().get(i)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
            courses.add(temp);
        }
        foundTeacher.setCourses(courses);
    }
        teacherRepository.save(foundTeacher);
    }

    public void deleteTeacher(Long id) {
        Teacher foundTeacher = teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Teacher not found"));
        List<Course> listOfCourses = courseRepository.findByTeacher_Id(id);
        for (int i = 0; i < listOfCourses.size(); i++) {
            Course foundCourse = courseRepository.findById(listOfCourses.get(i).getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
            foundCourse.setTeacher(null);
            courseRepository.save(foundCourse);
        }
        teacherRepository.delete(foundTeacher);
    }

}
//