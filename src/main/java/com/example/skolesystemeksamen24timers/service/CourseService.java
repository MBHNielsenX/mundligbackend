package com.example.skolesystemeksamen24timers.service;

import com.example.skolesystemeksamen24timers.dto.CourseRequest;
import com.example.skolesystemeksamen24timers.dto.CourseResponse;
import com.example.skolesystemeksamen24timers.entity.Teacher;
import com.example.skolesystemeksamen24timers.entity.Student;
import com.example.skolesystemeksamen24timers.entity.Course;
import com.example.skolesystemeksamen24timers.repository.TeacherRepository;
import com.example.skolesystemeksamen24timers.repository.CourseRepository;
import com.example.skolesystemeksamen24timers.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    CourseRepository courseRepository;
    StudentRepository studentRepository;

    StudentService studentService;
    TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<CourseResponse> getAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(productOrder -> new CourseResponse(productOrder)).toList();
    }

    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
        return new CourseResponse(course);
    }

    public CourseResponse addCourse(CourseRequest courseRequest) {
        Teacher teacher = teacherRepository.findById(courseRequest.getTeacherId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Teacher not found"));
        List<Student> students = studentRepository.findAllById(courseRequest.getStudentIds());

        Course tempCourse = Course.builder()
                .name(courseRequest.getName())
                .startDate(courseRequest.getStartDate())
                .endDate(courseRequest.getEndDate())
                .ectsPoints(courseRequest.getEctsPoints())
                .maxStudents(courseRequest.getMaxStudents())
                .teacher(teacher)
                .students(students)
                .build();
        courseRepository.save(tempCourse);
        return new CourseResponse(tempCourse);
    }

    public void editCourse(CourseRequest courseRequest) {
        Course foundCourse = courseRepository.findById(courseRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
        List<Student> students = studentRepository.findAllById(courseRequest.getStudentIds());
        Teacher teacher = teacherRepository.findById(courseRequest.getTeacherId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Teacher not found"));
        if (courseRequest.getName() != null)
            foundCourse.setName(courseRequest.getName());
        if (courseRequest.getStartDate() != null)
            foundCourse.setStartDate(courseRequest.getStartDate());
        if (courseRequest.getEndDate() != null)
            foundCourse.setEndDate(courseRequest.getEndDate());
        if (courseRequest.getEctsPoints() != 0)
            foundCourse.setEctsPoints(courseRequest.getEctsPoints());
        if (courseRequest.getMaxStudents() != 0)
            foundCourse.setMaxStudents(courseRequest.getMaxStudents());
        if (courseRequest.getTeacherId() != 0)
            foundCourse.setTeacher(teacher);
        if (courseRequest.getStudentIds() != null)
            foundCourse.setStudents(students);
        courseRepository.save(foundCourse);
    }

    public void deleteCourse(Long id) {
        Course foundCourse = courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        courseRepository.delete(foundCourse);
    }

    public void removeTeacherFromCourse(Long courseId) {
        Course foundCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
        foundCourse.setTeacher(null);
        courseRepository.save(foundCourse);
    }


    public CourseResponse removeStudentFromCourse(Long courseId, Long studentId) {
        Course foundCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
        List<Student> students = foundCourse.getStudents();
        if (studentRepository.existsById(studentId)) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId() == studentId) {
                    students.remove(i);
                }
            }
            foundCourse.setStudents(students);
            courseRepository.save(foundCourse);
        }
        return new CourseResponse(foundCourse);
    }

    /*
    public void removeStudentFromAllCourses(Student student) {
        List<Course> listOfCourses = courseRepository.findByStudents(student);
        for (int i = 0; i < listOfCourses.size(); i++) {
            Course foundCourse = courseRepository.findById(student.getCourses().get(i).getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
            List<Student> students = foundCourse.getStudents();
            if (studentRepository.existsById(student.getId())) {
                for (int j = 0; j < students.size(); i++) {
                    if (students.get(j).getId() == student.getId()) {
                        students.remove(j);
                    }
                }
                foundCourse.setStudents(students);
                courseRepository.save(foundCourse);
            }
        }

    }

     */

}
//