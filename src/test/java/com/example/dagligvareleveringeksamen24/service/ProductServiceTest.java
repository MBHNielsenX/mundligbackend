package com.example.dagligvareleveringeksamen24.service;

import com.example.dagligvareleveringeksamen24.dto.ProductRequest;
import com.example.dagligvareleveringeksamen24.dto.ProductResponse;
import com.example.dagligvareleveringeksamen24.entity.Product;
import com.example.dagligvareleveringeksamen24.repository.ProductOrderRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductServiceTest {

    public static ProductService productService;
    public static ProductRepository productRepository;

    public static ProductOrderRepository productOrderRepository;

    @BeforeAll
    public static void dataInitializer(@Autowired ProductRepository productRepo,
                                       @Autowired ProductOrderRepository productOrderRepo) {
        productRepository = productRepo;
        productOrderRepository = productOrderRepo;
        productOrderRepository.deleteAll();
        productRepository.deleteAll();

        Product product1 = Product.builder()
                .id(1L)
                .name("Milk")
                .price(10.0)
                .weight(1.0)
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .name("Bread")
                .price(20.0)
                .weight(2.0)
                .build();
        Product product3 = Product.builder()
                .id(3L)
                .name("Eggs")
                .price(30.0)
                .weight(3.0)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

    }

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository, productOrderRepository);
    }

    @Test
    void getAll() {
        List<ProductResponse> products = productService.getAll();
        assertEquals(3, products.size());

        assertEquals(  "Milk", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(1.0, products.get(0).getWeight());

        assertEquals(  "Bread", products.get(1).getName());
        assertEquals(20.0, products.get(1).getPrice());
        assertEquals(2.0, products.get(1).getWeight());

        assertEquals(  "Eggs", products.get(2).getName());
        assertEquals(30.0, products.get(2).getPrice());
        assertEquals(3.0, products.get(2).getWeight());

    }

    @Test
    void getById() {
        ProductResponse product = productService.getById(1L);
        assertEquals(  "Milk", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(1.0, product.getWeight());

        assertNotEquals(  "Bread", product.getName());
        assertNotEquals(20.0, product.getPrice());
        assertNotEquals(2.0, product.getWeight());
    }

    @Test
    void addProduct() {
        ProductRequest productRequest = ProductRequest.builder()
                .id(4L)
                .name("Cheese")
                .price(40.0)
                .weight(4.0)
                .build();
        productService.addProduct(productRequest);

        List<ProductResponse> products = productService.getAll();
        assertEquals(4, products.size());

        assertEquals(  "Milk", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(1.0, products.get(0).getWeight());

        assertEquals(  "Bread", products.get(1).getName());
        assertEquals(20.0, products.get(1).getPrice());
        assertEquals(2.0, products.get(1).getWeight());

        assertEquals(  "Eggs", products.get(2).getName());
        assertEquals(30.0, products.get(2).getPrice());
        assertEquals(3.0, products.get(2).getWeight());

        assertEquals(  "Cheese", products.get(3).getName());
        assertEquals(40.0, products.get(3).getPrice());
        assertEquals(4.0, products.get(3).getWeight());

        assertNotEquals("Bread", products.get(3).getName());
        assertNotEquals("Milk", products.get(3).getName());
        assertNotEquals("Eggs", products.get(3).getName());
    }

    @Test
    void getProductByName() {
        ProductResponse product = productService.getProductByName("Milk");
        assertEquals(  "Milk", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(1.0, product.getWeight());

        assertNotEquals(  "Bread", product.getName());
        assertNotEquals(20.0, product.getPrice());
        assertNotEquals(2.0, product.getWeight());
    }

    @Test
    void editProduct() {
        List<ProductResponse> products = productService.getAll();
        assertEquals(3, products.size());
        assertEquals("Eggs", products.get(2).getName());
        assertEquals(30.0, products.get(2).getPrice());
        assertEquals(3.0, products.get(2).getWeight());

        ProductRequest productRequest = ProductRequest.builder()
                .id(3L)
                .name("Rise")
                .price(50.0)
                .weight(5.0)
                .build();
        productService.editProduct(productRequest);

        List<ProductResponse> products2 = productService.getAll();
        assertEquals(3, products2.size());
        assertEquals("Rise", products2.get(2).getName());
        assertEquals(50.0, products2.get(2).getPrice());
        assertEquals(5.0, products2.get(2).getWeight());

    }

    @Test
    void deleteProduct() {
        List<ProductResponse> products = productService.getAll();
        assertEquals(3, products.size());

        assertEquals("Milk", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(1.0, products.get(0).getWeight());

        productService.deleteProduct(1L);



        List<ProductResponse> products2 = productService.getAll();
        assertEquals(2, products2.size());

        assertNotEquals("Milk", products2.get(0).getName());
        assertNotEquals(10.0, products2.get(0).getPrice());
        assertNotEquals(1.0, products2.get(0).getWeight());

    }
}