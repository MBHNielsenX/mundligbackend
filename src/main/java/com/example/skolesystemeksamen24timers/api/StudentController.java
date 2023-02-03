package com.example.skolesystemeksamen24timers.api;

import com.example.skolesystemeksamen24timers.dto.StudentRequest;
import com.example.skolesystemeksamen24timers.dto.StudentResponse;
import com.example.skolesystemeksamen24timers.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    List<StudentResponse> getAllStudents() {
        return studentService.getAll();
    }



    @GetMapping(path = "/{id}")
    StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping(path = "/name")
    @ResponseBody
    StudentResponse getStudentByName(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    StudentResponse addStudent(@RequestBody StudentRequest body) {
        return studentService.addStudent(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editStudent(@RequestBody StudentRequest body) {
        studentService.editStudent(body);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }




}
