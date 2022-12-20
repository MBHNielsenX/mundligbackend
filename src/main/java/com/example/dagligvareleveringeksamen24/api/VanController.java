package com.example.dagligvareleveringeksamen24.api;

import com.example.dagligvareleveringeksamen24.dto.VanRequest;
import com.example.dagligvareleveringeksamen24.dto.VanResponse;
import com.example.dagligvareleveringeksamen24.service.VanService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/van")
public class VanController {

    private final VanService vanService;

    public VanController(VanService vanService) {
        this.vanService = vanService;
    }

    @GetMapping()
    List<VanResponse> getAllVans() {
        return vanService.getAll();
    }

    @GetMapping(path = "/{id}")
    VanResponse getVanById(@PathVariable Long id) {
        return vanService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    VanResponse addVan(@RequestBody VanRequest body) {
        return vanService.addVan(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editVan(@RequestBody VanRequest body) {
        vanService.editVan(body);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteVan(@PathVariable Long id) {
        vanService.deleteVan(id);
        return ResponseEntity.ok(true);
    }

}
