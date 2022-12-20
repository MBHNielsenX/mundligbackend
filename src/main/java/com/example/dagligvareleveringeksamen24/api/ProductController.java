package com.example.dagligvareleveringeksamen24.api;

import com.example.dagligvareleveringeksamen24.dto.ProductRequest;
import com.example.dagligvareleveringeksamen24.dto.ProductResponse;
import com.example.dagligvareleveringeksamen24.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }



    @GetMapping(path = "/{id}")
    ProductResponse getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping(path = "/name")
    @ResponseBody
    ProductResponse getProductByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ProductResponse addProduct(@RequestBody ProductRequest body) {
        return productService.addProduct(body);
    }

    @PutMapping()
    ResponseEntity<Boolean> editProduct(@RequestBody ProductRequest body) {
        productService.editProduct(body);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
