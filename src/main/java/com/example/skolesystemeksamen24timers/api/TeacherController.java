package com.example.skolesystemeksamen24timers.api;

import com.example.skolesystemeksamen24timers.dto.TeacherRequest;
import com.example.skolesystemeksamen24timers.dto.TeacherResponse;
import com.example.skolesystemeksamen24timers.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    List<TeacherResponse> getAllTeachers() {
        return teacherService.getAll();
    }

    @GetMapping(path = "/{id}")
    TeacherResponse getTeacherById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @GetMapping(path = "/name")
    TeacherResponse getTeacherByName(@RequestParam String name) {
        System.out.println(name);
        return teacherService.getByName(name);
    }

    @PostMapping()
    TeacherResponse addTeacher(@RequestBody TeacherRequest body) {
        return teacherService.addTeacher(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editTeacher(@RequestBody TeacherRequest body) {
        teacherService.editTeacher(body);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok(true);
    }


}
//