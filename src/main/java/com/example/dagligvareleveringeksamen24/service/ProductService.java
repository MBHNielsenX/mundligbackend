package com.example.dagligvareleveringeksamen24.service;

import com.example.dagligvareleveringeksamen24.dto.ProductRequest;
import com.example.dagligvareleveringeksamen24.dto.ProductResponse;
import com.example.dagligvareleveringeksamen24.entity.Product;
import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.example.dagligvareleveringeksamen24.repository.ProductOrderRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;
    ProductOrderRepository productOrderRepository;
    ProductOrderService productOrderService;


    public ProductService(ProductRepository productRepository,
                          ProductOrderRepository productOrderRepository) {
        this.productRepository = productRepository;

        this.productOrderRepository = productOrderRepository;
    }

    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ProductResponse(product)).toList();
    }

    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        return new ProductResponse(product);
    }

    public ProductResponse getProductByName(String name) {
        if (productRepository.existsByName(name)) {
            Product product = productRepository.findByName(name);
            return new ProductResponse(product);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product tempProduct = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .weight(productRequest.getWeight())
                .build();
        productRepository.save(tempProduct);
        return new ProductResponse(tempProduct);
    }

    public void editProduct(ProductRequest productRequest) {
        Product foundProduct = productRepository.findById(productRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        if(productRequest.getName() != null) {
            foundProduct.setName(productRequest.getName());
        }
        if (productRequest.getPrice() >= 0 || Double.isNaN(productRequest.getPrice())) {
            foundProduct.setPrice(productRequest.getPrice());
        }
        if (productRequest.getWeight() >= 0 || Double.isNaN(productRequest.getWeight())) {
            foundProduct.setWeight(productRequest.getWeight());
        }
        productRepository.save(foundProduct);


    }

    public void deleteProduct(Long id) {
        Product foundProduct = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        List<ProductOrder> foundProductOrders = foundProduct.getProductOrders();
        for (int i = 0; i < foundProductOrders.size(); i++) {
            ProductOrder temp = foundProductOrders.get(i);
            temp.setProduct(null);
            productOrderRepository.save(temp);
        }
        productRepository.delete(foundProduct);
    }
}
