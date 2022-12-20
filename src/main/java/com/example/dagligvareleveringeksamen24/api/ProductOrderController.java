package com.example.dagligvareleveringeksamen24.api;

import com.example.dagligvareleveringeksamen24.dto.ProductOrderRequest;
import com.example.dagligvareleveringeksamen24.dto.ProductOrderResponse;
import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.example.dagligvareleveringeksamen24.service.ProductOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-order")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping()
    List<ProductOrderResponse> getAllProductOrders() {
        return productOrderService.getAll();
    }

    @GetMapping(path = "/{id}")
    ProductOrderResponse getProductOrderById(@PathVariable Long id) {
        return productOrderService.getProductOrderById(id);
    }

    @PostMapping()
    ProductOrderResponse addProductOrder(@RequestBody ProductOrderRequest body) {
        return productOrderService.addProductOrder(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editProductOrder(@RequestBody ProductOrderRequest body) {
        productOrderService.editProductOrder(body);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteProductOrder(@PathVariable Long id) {
        productOrderService.deleteProductOrder(id);
        return ResponseEntity.ok(true);
    }


}
