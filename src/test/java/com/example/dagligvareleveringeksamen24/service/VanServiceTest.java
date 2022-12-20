package com.example.dagligvareleveringeksamen24.service;

import com.example.dagligvareleveringeksamen24.dto.VanRequest;
import com.example.dagligvareleveringeksamen24.dto.VanResponse;
import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.example.dagligvareleveringeksamen24.entity.Product;
import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.example.dagligvareleveringeksamen24.entity.Van;
import com.example.dagligvareleveringeksamen24.repository.DeliveryRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductOrderRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductRepository;
import com.example.dagligvareleveringeksamen24.repository.VanRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VanServiceTest {

    public static VanService vanService;
    public static VanRepository vanRepository;
    public static DeliveryRepository deliveryRepository;
    public static ProductOrderRepository productOrderRepository;
    public static ProductRepository productRepository;

    @BeforeAll
    public static void dataInitializer(@Autowired VanRepository vanRepo,
                                       @Autowired DeliveryRepository deliveryRepo,
                                       @Autowired ProductOrderRepository productOrderRepo,
                                       @Autowired ProductRepository productRepo) {
        vanRepository = vanRepo;
        deliveryRepository = deliveryRepo;
        productOrderRepository = productOrderRepo;
        productRepository = productRepo;
        vanRepository.deleteAll();
        deliveryRepository.deleteAll();
        productOrderRepository.deleteAll();
        productRepository.deleteAll();

        Van van1 = Van.builder()
                .brand("Ford")
                .model("Transit")
                .capacity(1000)
                .build();
        Van van2 = Van.builder()
                .brand("Volkswagen")
                .model("Transporter")
                .capacity(1000)
                .build();
        Van van3 = Van.builder()
                .brand("Mercedes")
                .model("Sprinter")
                .capacity(1000)
                .build();
        Van van4 = Van.builder()
                .brand("Renault")
                .model("Trafic")
                .capacity(1000)
                .build();
        vanRepository.save(van1);
        vanRepository.save(van2);
        vanRepository.save(van3);
        vanRepository.save(van4);

        Product product1 = Product.builder()
                .name("Milk")
                .price(10)
                .build();
        Product product2 = Product.builder()
                .name("Bread")
                .price(20)
                .build();
        Product product3 = Product.builder()
                .name("Eggs")
                .price(30)
                .build();
        Product product4 = Product.builder()
                .name("Cheese")
                .price(40)
                .build();
        Product product5 = Product.builder()
                .name("Butter")
                .price(50)
                .build();
        Product product6 = Product.builder()
                .name("Sausage")
                .price(60)
                .build();
        Product product7 = Product.builder()
                .name("Ham")
                .price(70)
                .build();
        Product product8 = Product.builder()
                .name("Chicken")
                .price(80)
                .build();
        Product product9 = Product.builder()
                .name("Beef")
                .price(90)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);
        productRepository.save(product9);

        Delivery delivery1 = Delivery.builder()
                .build();
        Delivery delivery2 = Delivery.builder()
                .build();
        Delivery delivery3 = Delivery.builder()
                .build();
        Delivery delivery4 = Delivery.builder()
                .build();
        deliveryRepository.save(delivery1);
        deliveryRepository.save(delivery2);
        deliveryRepository.save(delivery3);
        deliveryRepository.save(delivery4);

        ProductOrder productOrder1 = ProductOrder.builder()
                .product(product1)
                .delivery(delivery1)
                .quantity(1)
                .build();
        ProductOrder productOrder2 = ProductOrder.builder()
                .product(product2)
                .delivery(delivery1)
                .quantity(2)
                .build();
        ProductOrder productOrder3 = ProductOrder.builder()
                .product(product3)
                .delivery(delivery1)
                .quantity(3)
                .build();
        ProductOrder productOrder4 = ProductOrder.builder()
                .product(product4)
                .delivery(delivery2)
                .quantity(4)
                .build();
        ProductOrder productOrder5 = ProductOrder.builder()
                .product(product5)
                .delivery(delivery2)
                .quantity(5)
                .build();
        ProductOrder productOrder6 = ProductOrder.builder()
                .product(product6)
                .delivery(delivery2)
                .quantity(6)
                .build();
        ProductOrder productOrder7 = ProductOrder.builder()
                .product(product7)
                .delivery(delivery3)
                .quantity(7)
                .build();
        ProductOrder productOrder8 = ProductOrder.builder()
                .product(product8)
                .delivery(delivery3)
                .quantity(8)
                .build();
        productOrderRepository.save(productOrder1);
        productOrderRepository.save(productOrder2);
        productOrderRepository.save(productOrder3);
        productOrderRepository.save(productOrder4);
        productOrderRepository.save(productOrder5);
        productOrderRepository.save(productOrder6);
        productOrderRepository.save(productOrder7);
        productOrderRepository.save(productOrder8);

        delivery1.setVan(van1);
        delivery1.setDeliveryDate(LocalDate.of(2021, 1, 1));
        delivery1.setFromWarehouse("Warehouse 1");
        delivery1.setDestination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N");
        delivery1.setProductOrders(List.of(productOrder1, productOrder2, productOrder3));
        deliveryRepository.save(delivery1);

        if (van1.getDeliveries() == null) {
            van1.setDeliveries(List.of(delivery1));
        } else {
            van1.getDeliveries().add(delivery1);
        }

        delivery2.setVan(van2);
        delivery2.setDeliveryDate(LocalDate.of(2021, 1, 2));
        delivery2.setFromWarehouse("Warehouse 2");
        delivery2.setDestination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N");
        delivery2.setProductOrders(List.of(productOrder4, productOrder5, productOrder6));
        deliveryRepository.save(delivery2);

        if (van2.getDeliveries() == null) {
            van2.setDeliveries(List.of(delivery2));
        } else {
            van2.getDeliveries().add(delivery2);
        }

        delivery3.setVan(van3);
        delivery3.setDeliveryDate(LocalDate.of(2021, 1, 3));
        delivery3.setFromWarehouse("Warehouse 3");
        delivery3.setDestination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N");
        delivery3.setProductOrders(List.of(productOrder7, productOrder8));
        deliveryRepository.save(delivery3);

        if (van3.getDeliveries() == null) {
            van3.setDeliveries(List.of(delivery3));
        } else {
            van3.getDeliveries().add(delivery3);
        }

    }

    @BeforeEach
    void setUp() {
        vanService = new VanService(vanRepository, deliveryRepository, productRepository, productOrderRepository);
    }

    @Test
    void getAll() {
        List<VanResponse> vans = vanService.getAll();
        assertEquals(4, vans.size());

        // Check van that has deliveries
        assertEquals("Ford" , vans.get(0).getBrand());
        assertEquals("Transit" , vans.get(0).getModel());
        assertEquals(1000, vans.get(0).getCapacity());

        assertEquals(1, vans.get(0).getDeliveries().size());
        assertEquals(3, vans.get(0).getDeliveries().get(0).getProductOrders().size());
        assertEquals("Milk", vans.get(0).getDeliveries().get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(10, vans.get(0).getDeliveries().get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1, vans.get(0).getDeliveries().get(0).getProductOrders().get(0).getQuantity());
        assertEquals("Bread", vans.get(0).getDeliveries().get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20, vans.get(0).getDeliveries().get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2, vans.get(0).getDeliveries().get(0).getProductOrders().get(1).getQuantity());
        assertEquals("Eggs", vans.get(0).getDeliveries().get(0).getProductOrders().get(2).getProduct().getName());
        assertEquals(30, vans.get(0).getDeliveries().get(0).getProductOrders().get(2).getProduct().getPrice());
        assertEquals(3, vans.get(0).getDeliveries().get(0).getProductOrders().get(2).getQuantity());

        // Check van that has no deliveries
        assertEquals("Renault" , vans.get(3).getBrand());
        assertEquals("Trafic" , vans.get(3).getModel());
        assertEquals(1000, vans.get(3).getCapacity());

        assertEquals(0, vans.get(3).getDeliveries().size());
    }

    @Test
    void getById() {
        VanResponse van = vanService.getById(1L);
        assertEquals("Ford" , van.getBrand());
        assertEquals("Transit" , van.getModel());
        assertEquals(1000, van.getCapacity());

        assertEquals(1, van.getDeliveries().size());
        assertEquals(3, van.getDeliveries().get(0).getProductOrders().size());
        assertEquals("Milk", van.getDeliveries().get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(10, van.getDeliveries().get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1, van.getDeliveries().get(0).getProductOrders().get(0).getQuantity());
        assertEquals("Bread", van.getDeliveries().get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20, van.getDeliveries().get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2, van.getDeliveries().get(0).getProductOrders().get(1).getQuantity());
        assertEquals("Eggs", van.getDeliveries().get(0).getProductOrders().get(2).getProduct().getName());
        assertEquals(30, van.getDeliveries().get(0).getProductOrders().get(2).getProduct().getPrice());
        assertEquals(3, van.getDeliveries().get(0).getProductOrders().get(2).getQuantity());

        assertThrows(ResponseStatusException.class, () -> vanService.getById(5L));
    }

    @Test
    void addVan() {
        List<VanResponse> vans = vanService.getAll();
        assertEquals(4, vans.size());

        VanRequest vanRequest = VanRequest.builder()
                .brand("Volkswagen")
                .model("Transporter")
                .capacity(1000)
                .build();
        vanService.addVan(vanRequest);

        List<VanResponse> vansAfterAdd = vanService.getAll();
        assertEquals(5, vansAfterAdd.size());
    }

    /*
    @Test
    void editVan() {
        List<VanResponse> vans = vanService.getAll();
        assertEquals(4, vans.size());

        // Check that the first van is correct
        assertEquals("Ford" , vans.get(0).getBrand());
        assertEquals("Transit" , vans.get(0).getModel());
        assertEquals(1000, vans.get(0).getCapacity());

        assertEquals(1, vans.get(0).getDeliveries().size());
        assertEquals(3, vans.get(0).getDeliveries().get(0).getProductOrders().size());
        assertEquals("Milk", vans.get(0).getDeliveries().get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(10, vans.get(0).getDeliveries().get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1, vans.get(0).getDeliveries().get(0).getProductOrders().get(0).getQuantity());
        assertEquals("Bread", vans.get(0).getDeliveries().get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20, vans.get(0).getDeliveries().get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2, vans.get(0).getDeliveries().get(0).getProductOrders().get(1).getQuantity());
        assertEquals("Eggs", vans.get(0).getDeliveries().get(0).getProductOrders().get(2).getProduct().getName());
        assertEquals(30, vans.get(0).getDeliveries().get(0).getProductOrders().get(2).getProduct().getPrice());
        assertEquals(3, vans.get(0).getDeliveries().get(0).getProductOrders().get(2).getQuantity());

        // Edit the first van
        VanRequest vanRequest = VanRequest.builder()
                .id(1L)
                .brand("Volkswagen")
                .model("Transporter")
                .capacity(1000)
                .deliveryIds(List.of(4L))
                .build();
        vanService.editVan(vanRequest);

        // Check that the first van is correct
        List<VanResponse> vansAfterEdit = vanService.getAll();
        assertEquals(4, vansAfterEdit.size());

        assertEquals("Volkswagen" , vansAfterEdit.get(0).getBrand());
        assertEquals("Transporter" , vansAfterEdit.get(0).getModel());
        assertEquals(1000, vansAfterEdit.get(0).getCapacity());

        assertEquals(1, vansAfterEdit.get(0).getDeliveries().size());
        assertEquals(3, vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().size());
        assertEquals("Milk", vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(0).getProduct().getName());
        assertEquals(10, vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(0).getProduct().getPrice());
        assertEquals(1, vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(0).getQuantity());
        assertEquals("Bread", vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(1).getProduct().getName());
        assertEquals(20, vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(1).getProduct().getPrice());
        assertEquals(2, vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(1).getQuantity());
        assertEquals("Eggs", vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(2).getProduct().getName());
        assertEquals(30, vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(2).getProduct().getPrice());
        assertEquals(3, vansAfterEdit.get(0).getDeliveries().get(0).getProductOrders().get(2).getQuantity());

    }

     */

    @Test
    void deleteVan() {
    }
}