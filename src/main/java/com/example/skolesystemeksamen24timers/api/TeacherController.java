package com.example.skolesystemeksamen24timers.api;

import com.example.skolesystemeksamen24timers.dto.TeacherRequest;
import com.example.skolesystemeksamen24timers.dto.TeacherResponse;
import com.example.skolesystemeksamen24timers.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/delivery")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    List<TeacherResponse> getAllDeliveries() {
        return teacherService.getAll();
    }

    @GetMapping(path = "/{id}")
    TeacherResponse getDeliveryById(@PathVariable Long id) {
        return teacherService.getById(id);
    }
    /*

    @PostMapping()
    TeacherResponse addDelivery(@RequestBody TeacherRequest body) {
        return teacherService.addDelivery(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editDelivery(@RequestBody TeacherRequest body) {
        teacherService.editDelivery(body);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteDelivery(@PathVariable Long id) {
        teacherService.deleteDelivery(id);
        return ResponseEntity.ok(true);
    }

     */

}
