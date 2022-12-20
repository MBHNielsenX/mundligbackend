package com.example.dagligvareleveringeksamen24.api;

import com.example.dagligvareleveringeksamen24.dto.DeliveryRequest;
import com.example.dagligvareleveringeksamen24.dto.DeliveryResponse;
import com.example.dagligvareleveringeksamen24.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping()
    List<DeliveryResponse> getAllDeliveries() {
        return deliveryService.getAll();
    }

    @GetMapping(path = "/{id}")
    DeliveryResponse getDeliveryById(@PathVariable Long id) {
        return deliveryService.getById(id);
    }

    @PostMapping()
    DeliveryResponse addDelivery(@RequestBody DeliveryRequest body) {
        return deliveryService.addDelivery(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editDelivery(@RequestBody DeliveryRequest body) {
        deliveryService.editDelivery(body);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok(true);
    }

}
