package com.example.dagligvareleveringeksamen24.service;

import com.example.dagligvareleveringeksamen24.dto.DeliveryResponse;
import com.example.dagligvareleveringeksamen24.dto.ProductOrderRequest;
import com.example.dagligvareleveringeksamen24.dto.ProductOrderResponse;
import com.example.dagligvareleveringeksamen24.dto.ProductResponse;
import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.example.dagligvareleveringeksamen24.entity.Product;
import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.example.dagligvareleveringeksamen24.repository.DeliveryRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductOrderRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductOrderServiceTest {

    public static ProductOrderService productOrderService;
    public static ProductOrderRepository productOrderRepository;
    public static ProductRepository productRepository;
    public static DeliveryRepository deliveryRepository;

    @BeforeAll
    public static void dataInitializer(@Autowired ProductRepository productRepo,
                                       @Autowired ProductOrderRepository productOrderRepo,
                                       @Autowired DeliveryRepository deliveryRepo) {
        productRepository = productRepo;
        productOrderRepository = productOrderRepo;
        deliveryRepository = deliveryRepo;
        productOrderRepository.deleteAll();
        productRepository.deleteAll();
        deliveryRepository.deleteAll();

        Product product1 = Product.builder()
                .name("Milk")
                .price(10.0)
                .weight(1.0)
                .build();
        Product product2 = Product.builder()
                .name("Bread")
                .price(20.0)
                .weight(2.0)
                .build();
        Product product3 = Product.builder()
                .name("Eggs")
                .price(30.0)
                .weight(3.0)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Delivery delivery1 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 1))
                .fromWarehouse("Warehouse1")
                .destination("Destination1")
                .build();
        Delivery delivery2 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 2))
                .fromWarehouse("Warehouse2")
                .destination("Destination2")
                .build();
        deliveryRepository.save(delivery1);
        deliveryRepository.save(delivery2);

        ProductOrder productOrder1 = ProductOrder.builder()
                .product(product1)
                .delivery(delivery1)
                .quantity(1)
                .build();
        ProductOrder productOrder2 = ProductOrder.builder()
                .product(product2)
                .delivery(delivery2)
                .quantity(2)
                .build();
        ProductOrder productOrder3 = ProductOrder.builder()
                .product(product3)
                .delivery(delivery1)
                .quantity(3)
                .build();
        productOrderRepository.save(productOrder1);
        productOrderRepository.save(productOrder2);
        productOrderRepository.save(productOrder3);






    }

    @BeforeEach
    void setUp() {
        productOrderService = new ProductOrderService(productOrderRepository, productRepository, deliveryRepository);
    }

    @Test
    void getAll() {
        List<ProductOrderResponse> productOrders = productOrderService.getAll();
        assertEquals(3, productOrders.size());

        assertEquals("Milk", productOrders.get(0).getProduct().getName());
        assertEquals(10.0, productOrders.get(0).getProduct().getPrice());
        assertEquals(1.0, productOrders.get(0).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 1), productOrders.get(0).getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrders.get(0).getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrders.get(0).getDelivery().getDestination());
        assertEquals(1, productOrders.get(0).getQuantity());

        assertEquals("Bread", productOrders.get(1).getProduct().getName());
        assertEquals(20.0, productOrders.get(1).getProduct().getPrice());
        assertEquals(2.0, productOrders.get(1).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 2), productOrders.get(1).getDelivery().getDeliveryDate());
        assertEquals("Warehouse2", productOrders.get(1).getDelivery().getFromWarehouse());
        assertEquals("Destination2", productOrders.get(1).getDelivery().getDestination());
        assertEquals(2, productOrders.get(1).getQuantity());

        assertEquals("Eggs", productOrders.get(2).getProduct().getName());
        assertEquals(30.0, productOrders.get(2).getProduct().getPrice());
        assertEquals(3.0, productOrders.get(2).getProduct().getWeight());
        assertEquals(LocalDate.of(2021,1,1), productOrders.get(2).getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrders.get(2).getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrders.get(2).getDelivery().getDestination());
        assertEquals(3, productOrders.get(2).getQuantity());



    }

    @Test
    void getProductOrderById() {
        ProductOrderResponse productOrder = productOrderService.getProductOrderById(1L);
        assertEquals("Milk", productOrder.getProduct().getName());
        assertEquals(10.0, productOrder.getProduct().getPrice());
        assertEquals(1.0, productOrder.getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 1), productOrder.getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrder.getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrder.getDelivery().getDestination());
        assertEquals(1, productOrder.getQuantity());

        ProductOrderResponse productOrder2 = productOrderService.getProductOrderById(2L);
        assertEquals("Bread", productOrder2.getProduct().getName());
        assertEquals(20.0, productOrder2.getProduct().getPrice());
        assertEquals(2.0, productOrder2.getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 2), productOrder2.getDelivery().getDeliveryDate());
        assertEquals("Warehouse2", productOrder2.getDelivery().getFromWarehouse());
        assertEquals("Destination2", productOrder2.getDelivery().getDestination());
        assertEquals(2, productOrder2.getQuantity());

        ProductOrderResponse productOrder3 = productOrderService.getProductOrderById(3L);
        assertEquals("Eggs", productOrder3.getProduct().getName());
        assertEquals(30.0, productOrder3.getProduct().getPrice());
        assertEquals(3.0, productOrder3.getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 1), productOrder3.getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrder3.getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrder3.getDelivery().getDestination());
        assertEquals(3, productOrder3.getQuantity());
    }

    @Test
    void addProductOrder() {
        List<ProductOrderResponse> productOrders = productOrderService.getAll();
        assertEquals(3, productOrders.size());

        ProductOrderRequest productOrderRequest = ProductOrderRequest.builder()
                .productId(1L)
                .deliveryId(1L)
                .quantity(1)
                .build();
        productOrderService.addProductOrder(productOrderRequest);

        List<ProductOrderResponse> productOrders2 = productOrderService.getAll();
        assertEquals(4, productOrders2.size());

        assertEquals("Milk", productOrders2.get(3).getProduct().getName());
        assertEquals(10.0, productOrders2.get(3).getProduct().getPrice());
        assertEquals(1.0, productOrders2.get(3).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 1), productOrders2.get(3).getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrders2.get(3).getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrders2.get(3).getDelivery().getDestination());
        assertEquals(1, productOrders2.get(3).getQuantity());
    }

    @Test
    void editProductOrder() {
        List<ProductOrderResponse> productOrders = productOrderService.getAll();
        assertEquals(3, productOrders.size());

        assertEquals("Milk", productOrders.get(0).getProduct().getName());
        assertEquals(10.0, productOrders.get(0).getProduct().getPrice());
        assertEquals(1.0, productOrders.get(0).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 1), productOrders.get(0).getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrders.get(0).getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrders.get(0).getDelivery().getDestination());
        assertEquals(1, productOrders.get(0).getQuantity());

        ProductOrderRequest productOrderRequest = ProductOrderRequest.builder()
                .id(1L)
                .productId(3L)
                .deliveryId(2L)
                .quantity(1)
                .build();
        productOrderService.editProductOrder(productOrderRequest);

        List<ProductOrderResponse> productOrders2 = productOrderService.getAll();
        assertEquals(3, productOrders2.size());

        assertEquals("Eggs", productOrders2.get(0).getProduct().getName());
        assertEquals(30.0, productOrders2.get(0).getProduct().getPrice());
        assertEquals(3.0, productOrders2.get(0).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 2), productOrders2.get(0).getDelivery().getDeliveryDate());
        assertEquals("Warehouse2", productOrders2.get(0).getDelivery().getFromWarehouse());
        assertEquals("Destination2", productOrders2.get(0).getDelivery().getDestination());
        assertEquals(1, productOrders2.get(0).getQuantity());

    }

    @Test
    void deleteProductOrder() {
        List<ProductOrderResponse> productOrders = productOrderService.getAll();
        assertEquals(3, productOrders.size());

        assertEquals("Milk", productOrders.get(0).getProduct().getName());
        assertEquals(10.0, productOrders.get(0).getProduct().getPrice());
        assertEquals(1.0, productOrders.get(0).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 1), productOrders.get(0).getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrders.get(0).getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrders.get(0).getDelivery().getDestination());
        assertEquals(1, productOrders.get(0).getQuantity());

        productOrderService.deleteProductOrder(1L);

        List<ProductOrderResponse> productOrders2 = productOrderService.getAll();
        assertEquals(2, productOrders2.size());

        assertEquals("Bread", productOrders2.get(0).getProduct().getName());
        assertEquals(20.0, productOrders2.get(0).getProduct().getPrice());
        assertEquals(2.0, productOrders2.get(0).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 2), productOrders2.get(0).getDelivery().getDeliveryDate());
        assertEquals("Warehouse2", productOrders2.get(0).getDelivery().getFromWarehouse());
        assertEquals("Destination2", productOrders2.get(0).getDelivery().getDestination());
        assertEquals(2, productOrders2.get(0).getQuantity());

        assertEquals("Eggs", productOrders2.get(1).getProduct().getName());
        assertEquals(30.0, productOrders2.get(1).getProduct().getPrice());
        assertEquals(3.0, productOrders2.get(1).getProduct().getWeight());
        assertEquals(LocalDate.of(2021, 1, 1), productOrders2.get(1).getDelivery().getDeliveryDate());
        assertEquals("Warehouse1", productOrders2.get(1).getDelivery().getFromWarehouse());
        assertEquals("Destination1", productOrders2.get(1).getDelivery().getDestination());
        assertEquals(3, productOrders2.get(1).getQuantity());

    }
}