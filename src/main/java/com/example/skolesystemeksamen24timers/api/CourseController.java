package com.example.skolesystemeksamen24timers.api;

import com.example.skolesystemeksamen24timers.dto.CourseRequest;
import com.example.skolesystemeksamen24timers.dto.CourseResponse;
import com.example.skolesystemeksamen24timers.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    List<CourseResponse> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping(path = "/{id}")
    CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping()
    CourseResponse addCourse(@RequestBody CourseRequest body) {
        return courseService.addCourse(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editCourse(@RequestBody CourseRequest body) {
        courseService.editCourse(body);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteCourse(@PathVariable Long id) {
        courseService.deleteProductOrder(id);
        return ResponseEntity.ok(true);
    }


}
//