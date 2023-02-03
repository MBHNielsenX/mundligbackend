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

    public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<TeacherResponse> getAll() {
        List<Teacher> deliveries = teacherRepository.findAll();
        return deliveries.stream().map(delivery -> new TeacherResponse(delivery)).toList();
    }

    public TeacherResponse getById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
        return new TeacherResponse(teacher);
    }

    /*
    public TeacherResponse addDelivery(TeacherRequest teacherRequest) {

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < teacherRequest.getProductOrderIds().size(); i++) {
            Course temp = courseRepository.findById(teacherRequest.getProductOrderIds().get(i)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ProductOrder not found"));
            if (temp.getTeacher() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ProductOrder already has a delivery");
            }
            courses.add(temp);
        }
        Teacher tempTeacher = Teacher.builder()
                .deliveryDate(teacherRequest.getDeliveryDate())
                .fromWarehouse(teacherRequest.getFromWarehouse())
                .destination(teacherRequest.getDestination())
                .courses(courses)
                .build();
        teacherRepository.save(tempTeacher);

        for (int i = 0; i < courses.size(); i++) {
            Course temp = courses.get(i);
            temp.setTeacher(tempTeacher);
            courseRepository.save(temp);
        }

        return new TeacherResponse(tempTeacher);
    }

    public void editDelivery(TeacherRequest teacherRequest) {
        Teacher foundTeacher = teacherRepository.findById(teacherRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < teacherRequest.getProductOrderIds().size(); i++) {
            Course temp = courseRepository.findById(teacherRequest.getProductOrderIds().get(i)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ProductOrder not found"));
            if (temp.getTeacher() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ProductOrder already has a delivery");
            }
            courses.add(temp);
        }
        if (teacherRequest.getDeliveryDate() != null) {
            foundTeacher.setDeliveryDate(teacherRequest.getDeliveryDate());
        }
        if (teacherRequest.getFromWarehouse() != null) {
            foundTeacher.setFromWarehouse(teacherRequest.getFromWarehouse());
        }
        if (teacherRequest.getDestination() != null) {
            foundTeacher.setDestination(teacherRequest.getDestination());
        }
        if (teacherRequest.getProductOrderIds() != null) {
            foundTeacher.setCourses(courses);
        }
        teacherRepository.save(foundTeacher);

        for (int i = 0; i < courses.size(); i++) {
            Course temp = courses.get(i);
            temp.setTeacher(foundTeacher);
            courseRepository.save(temp);
        }
    }

    public void deleteDelivery(Long id) {
        Teacher foundTeacher = teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
        List<Course> courses = foundTeacher.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course temp = courses.get(i);
            temp.setTeacher(null);
            courseRepository.save(temp);
        }
        teacherRepository.delete(foundTeacher);
    }

     */
}
