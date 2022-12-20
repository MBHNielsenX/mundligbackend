package com.example.dagligvareleveringeksamen24.service;

import com.example.dagligvareleveringeksamen24.dto.DeliveryRequest;
import com.example.dagligvareleveringeksamen24.dto.DeliveryResponse;
import com.example.dagligvareleveringeksamen24.dto.ProductOrderResponse;
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
class DeliveryServiceTest {
    public static DeliveryService deliveryService;
    public static DeliveryRepository deliveryRepository;
    public static ProductRepository productRepository;
    public static ProductOrderRepository productOrderRepository;


    @BeforeAll
    public static void dataInitializer(@Autowired DeliveryRepository deliveryRepo,
                                       @Autowired ProductRepository productRepo,
                                       @Autowired ProductOrderRepository productOrderRepo) {
        deliveryRepository = deliveryRepo;
        productRepository = productRepo;
        productOrderRepository = productOrderRepo;
        productOrderRepository.deleteAll();
        productRepository.deleteAll();
        deliveryRepository.deleteAll();

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
        System.out.println(product1);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        ProductOrder productOrder1 = ProductOrder.builder()
                .product(product1)
                .quantity(1)
                .build();
        ProductOrder productOrder2 = ProductOrder.builder()
                .product(product2)
                .quantity(2)
                .build();
        ProductOrder productOrder3 = ProductOrder.builder()
                .product(product3)
                .quantity(3)
                .build();
        ProductOrder productOrder4 = ProductOrder.builder()
                .product(product1)
                .quantity(4)
                .build();
        ProductOrder productOrder5 = ProductOrder.builder()
                .product(product2)
                .quantity(5)
                .build();
        ProductOrder productOrder6 = ProductOrder.builder()
                .product(product3)
                .quantity(6)
                .build();
        ProductOrder productOrder7 = ProductOrder.builder()
                .product(product1)
                .quantity(7)
                .build();
        ProductOrder productOrder8 = ProductOrder.builder()
                .product(product2)
                .quantity(8)
                .build();
        ProductOrder productOrder9 = ProductOrder.builder()
                .product(product3)
                .quantity(9)
                .build();
        ProductOrder productOrder10 = ProductOrder.builder()
                .product(product1)
                .quantity(10)
                .build();

        System.out.println(productOrder1);
        productOrderRepository.save(productOrder1);
        productOrderRepository.save(productOrder2);
        productOrderRepository.save(productOrder3);
        productOrderRepository.save(productOrder4);
        productOrderRepository.save(productOrder5);
        productOrderRepository.save(productOrder6);
        productOrderRepository.save(productOrder7);
        productOrderRepository.save(productOrder8);
        productOrderRepository.save(productOrder9);
        productOrderRepository.save(productOrder10);

        Delivery delivery1 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 1))
                .fromWarehouse("w1")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder1, productOrder2))
                .build();
        Delivery delivery2 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 2))
                .fromWarehouse("w2")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder3, productOrder4))
                .build();
        Delivery delivery3 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 3))
                .fromWarehouse("w3")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder5, productOrder6))
                .build();
        Delivery delivery4 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 4))
                .fromWarehouse("w4")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder7, productOrder8))
                .build();
        System.out.println("Delivery 1: " + delivery1);
        deliveryRepository.save(delivery1);
        deliveryRepository.save(delivery2);
        deliveryRepository.save(delivery3);
        deliveryRepository.save(delivery4);

        productOrder1.setDelivery(delivery1);
        productOrder2.setDelivery(delivery1);
        productOrder3.setDelivery(delivery2);
        productOrder4.setDelivery(delivery2);
        productOrder5.setDelivery(delivery3);
        productOrder6.setDelivery(delivery3);
        productOrder7.setDelivery(delivery4);
        productOrder8.setDelivery(delivery4);
        productOrderRepository.save(productOrder1);
        productOrderRepository.save(productOrder2);
        productOrderRepository.save(productOrder3);
        productOrderRepository.save(productOrder4);
        productOrderRepository.save(productOrder5);
        productOrderRepository.save(productOrder6);
        productOrderRepository.save(productOrder7);
        productOrderRepository.save(productOrder8);

    }

    @BeforeEach
    public void setUp() {
        deliveryService = new DeliveryService(deliveryRepository, productRepository, productOrderRepository);
    }

    @Test
    void getAll() {
        List<DeliveryResponse> deliveries = deliveryService.getAll();
        assertEquals(4, deliveries.size());

        // Check that the first delivery is correct
        assertEquals(LocalDate.of(2021, 1, 1), deliveries.get(0).getDeliveryDate());
        assertEquals("w1", deliveries.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(0).getDestination());
        assertEquals(2, deliveries.get(0).getProductOrders().size());

        assertEquals("Milk", deliveries.get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(10.0, deliveries.get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1.0, deliveries.get(0).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Bread", deliveries.get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20.0, deliveries.get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2.0, deliveries.get(0).getProductOrders().get(1).getProduct().getWeight());


        // Check that the second delivery is correct
        assertEquals(LocalDate.of(2021, 1, 2), deliveries.get(1).getDeliveryDate());
        assertEquals("w2", deliveries.get(1).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(1).getDestination());
        assertEquals(2, deliveries.get(1).getProductOrders().size());

        assertEquals("Eggs", deliveries.get(1).getProductOrders().get(0).getProduct().getName());
        assertEquals(30.0, deliveries.get(1).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(3.0, deliveries.get(1).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Milk", deliveries.get(1).getProductOrders().get(1).getProduct().getName());
        assertEquals(10.0, deliveries.get(1).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(1.0, deliveries.get(1).getProductOrders().get(1).getProduct().getWeight());


        // Check that the third delivery is correct
        assertEquals(LocalDate.of(2021, 1, 3), deliveries.get(2).getDeliveryDate());
        assertEquals("w3", deliveries.get(2).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(2).getDestination());
        assertEquals(2, deliveries.get(2).getProductOrders().size());

        assertEquals("Bread", deliveries.get(2).getProductOrders().get(0).getProduct().getName());
        assertEquals(20.0, deliveries.get(2).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(2.0, deliveries.get(2).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Eggs", deliveries.get(2).getProductOrders().get(1).getProduct().getName());
        assertEquals(30.0, deliveries.get(2).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(3.0, deliveries.get(2).getProductOrders().get(1).getProduct().getWeight());


        // Check that the fourth delivery is correct
        assertEquals(LocalDate.of(2021, 1, 4), deliveries.get(3).getDeliveryDate());
        assertEquals("w4", deliveries.get(3).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(3).getDestination());
        assertEquals(2, deliveries.get(3).getProductOrders().size());

        assertEquals("Milk", deliveries.get(3).getProductOrders().get(0).getProduct().getName());
        assertEquals(10.0, deliveries.get(3).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1.0, deliveries.get(3).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Bread", deliveries.get(3).getProductOrders().get(1).getProduct().getName());
        assertEquals(20.0, deliveries.get(3).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2.0, deliveries.get(3).getProductOrders().get(1).getProduct().getWeight());




    }

    @Test
    void getById() {
        DeliveryResponse delivery = deliveryService.getById(1L);
        assertEquals(LocalDate.of(2021, 1, 1), delivery.getDeliveryDate());
        assertEquals("w1", delivery.getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", delivery.getDestination());
        assertEquals(2, delivery.getProductOrders().size());

        assertEquals("Milk", delivery.getProductOrders().get(0).getProduct().getName());
        assertEquals(10.0, delivery.getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1.0, delivery.getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Bread", delivery.getProductOrders().get(1).getProduct().getName());
        assertEquals(20.0, delivery.getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2.0, delivery.getProductOrders().get(1).getProduct().getWeight());

    }

    @Test
    void addDelivery() {
        List<DeliveryResponse> deliveries = deliveryService.getAll();
        assertEquals(4, deliveries.size());

        DeliveryRequest deliveryRequest = DeliveryRequest.builder()
                .deliveryDate(LocalDate.of(2021, 1, 5))
                .fromWarehouse("w5")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrderIds(List.of(9L, 10L))
                .build();
        deliveryService.addDelivery(deliveryRequest);

        List<DeliveryResponse> deliveriesAfterAdd = deliveryService.getAll();
        assertEquals(5, deliveriesAfterAdd.size());

        assertEquals(LocalDate.of(2021, 1, 5), deliveriesAfterAdd.get(4).getDeliveryDate());
        assertEquals("w5", deliveriesAfterAdd.get(4).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveriesAfterAdd.get(4).getDestination());
        assertEquals(2, deliveriesAfterAdd.get(4).getProductOrders().size());

        assertEquals("Eggs", deliveriesAfterAdd.get(4).getProductOrders().get(0).getProduct().getName());
        assertEquals(30.0, deliveriesAfterAdd.get(4).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(3.0, deliveriesAfterAdd.get(4).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Milk", deliveriesAfterAdd.get(4).getProductOrders().get(1).getProduct().getName());
        assertEquals(10.0, deliveriesAfterAdd.get(4).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(1.0, deliveriesAfterAdd.get(4).getProductOrders().get(1).getProduct().getWeight());

    }

    @Test
    void editDelivery() {
        List<DeliveryResponse> deliveries = deliveryService.getAll();
        assertEquals(4, deliveries.size());

        // Check that the first delivery is correct
        assertEquals(LocalDate.of(2021, 1, 1), deliveries.get(0).getDeliveryDate());
        assertEquals("w1", deliveries.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(0).getDestination());
        assertEquals(2, deliveries.get(0).getProductOrders().size());

        assertEquals("Milk", deliveries.get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(10.0, deliveries.get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1.0, deliveries.get(0).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Bread", deliveries.get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20.0, deliveries.get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2.0, deliveries.get(0).getProductOrders().get(1).getProduct().getWeight());


        // Edit the first delivery
        DeliveryRequest deliveryRequest = DeliveryRequest.builder()
                .id(1L)
                .deliveryDate(LocalDate.of(2021, 1, 5))
                .fromWarehouse("w5")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrderIds(List.of(9L, 10L))
                .build();
        deliveryService.editDelivery(deliveryRequest);

        List<DeliveryResponse> deliveriesAfterEdit = deliveryService.getAll();
        assertEquals(4, deliveriesAfterEdit.size());

        // Check that the first delivery has been edited
        assertEquals(LocalDate.of(2021, 1, 5), deliveriesAfterEdit.get(0).getDeliveryDate());
        assertEquals("w5", deliveriesAfterEdit.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveriesAfterEdit.get(0).getDestination());
        assertEquals(2, deliveriesAfterEdit.get(0).getProductOrders().size());

        assertEquals("Eggs", deliveriesAfterEdit.get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(30.0, deliveriesAfterEdit.get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(3.0, deliveriesAfterEdit.get(0).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Milk", deliveriesAfterEdit.get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(10.0, deliveriesAfterEdit.get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(1.0, deliveriesAfterEdit.get(0).getProductOrders().get(1).getProduct().getWeight());


    }

    @Test
    void deleteDelivery() {
        List<DeliveryResponse> deliveries = deliveryService.getAll();
        assertEquals(4, deliveries.size());

        // Check first delivery
        assertEquals(LocalDate.of(2021, 1, 1), deliveries.get(0).getDeliveryDate());
        assertEquals("w1", deliveries.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveries.get(0).getDestination());
        assertEquals(2, deliveries.get(0).getProductOrders().size());

        assertEquals("Milk", deliveries.get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(10.0, deliveries.get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1.0, deliveries.get(0).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Bread", deliveries.get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20.0, deliveries.get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2.0, deliveries.get(0).getProductOrders().get(1).getProduct().getWeight());


        deliveryService.deleteDelivery(1L);

        List<DeliveryResponse> deliveriesAfterDelete = deliveryService.getAll();
        assertEquals(3, deliveriesAfterDelete.size());

        // Check that the first delivery has been deleted
        assertEquals(LocalDate.of(2021, 1, 2), deliveriesAfterDelete.get(0).getDeliveryDate());
        assertEquals("w2", deliveriesAfterDelete.get(0).getFromWarehouse());
        assertEquals("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N", deliveriesAfterDelete.get(0).getDestination());
        assertEquals(2, deliveriesAfterDelete.get(0).getProductOrders().size());

        assertEquals("Eggs", deliveriesAfterDelete.get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(30.0, deliveriesAfterDelete.get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(3.0, deliveriesAfterDelete.get(0).getProductOrders().get(0).getProduct().getWeight());

        assertEquals("Milk", deliveriesAfterDelete.get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(10.0, deliveriesAfterDelete.get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(1.0, deliveriesAfterDelete.get(0).getProductOrders().get(1).getProduct().getWeight());

        assertEquals("Bread", deliveriesAfterDelete.get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20.0, deliveriesAfterDelete.get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2.0, deliveriesAfterDelete.get(0).getProductOrders().get(1).getProduct().getWeight());

    }
}