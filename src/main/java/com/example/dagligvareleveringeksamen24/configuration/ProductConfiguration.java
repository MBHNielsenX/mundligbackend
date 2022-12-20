package com.example.dagligvareleveringeksamen24.configuration;

import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.example.dagligvareleveringeksamen24.entity.Product;
import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.example.dagligvareleveringeksamen24.entity.Van;
import com.example.dagligvareleveringeksamen24.repository.DeliveryRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductOrderRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductRepository;
import com.example.dagligvareleveringeksamen24.repository.VanRepository;
import com.example.dagligvareleveringeksamen24.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProductConfiguration implements ApplicationRunner {

    ProductRepository productRepository;
    ProductService productService;
    private final ProductOrderRepository productOrderRepository;
    private final DeliveryRepository deliveryRepository;
    private final VanRepository vanRepository;

    public ProductConfiguration(ProductRepository productRepository, ProductService productService,
                                ProductOrderRepository productOrderRepository,
                                DeliveryRepository deliveryRepository,
                                VanRepository vanRepository) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productOrderRepository = productOrderRepository;
        this.deliveryRepository = deliveryRepository;
        this.vanRepository = vanRepository;
    }

    @Override
    @SneakyThrows
    public void run(ApplicationArguments args) throws Exception {
        Van van1 = Van.builder()
                .brand("Ford")
                .model("V70")
                .capacity(1000)
                .build();
        Van van2 = Van.builder()
                .brand("Volvo")
                .model("V90")
                .capacity(1000)
                .build();
        Van van3 = Van.builder()
                .brand("Mercedes")
                .model("V60")
                .capacity(1000)
                .build();
        vanRepository.saveAll(List.of(van1, van2, van3));

        Product product1 = Product.builder()
                .name("Milk")
                .price(10)
                .weight(1)
                .build();
        Product product2 = Product.builder()
                .name("Bread")
                .price(20)
                .weight(2)
                .build();
        Product product3 = Product.builder()
                .name("Eggs")
                .price(30)
                .weight(3)
                .build();
        Product product4 = Product.builder()
                .name("Cheese")
                .price(40)
                .weight(4)
                .build();
        Product product5 = Product.builder()
                .name("Butter")
                .price(50)
                .weight(5)
                .build();
        Product product6 = Product.builder()
                .name("Yogurt")
                .price(60)
                .weight(6)
                .build();
        Product product7 = Product.builder()
                .name("Sausage")
                .price(70)
                .weight(7)
                .build();
        Product product8 = Product.builder()
                .name("Ham")
                .price(80)
                .weight(8)
                .build();
        Product product9 = Product.builder()
                .name("Chicken")
                .price(90)
                .weight(9)
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
                .product(product4)
                .quantity(4)
                .build();
        ProductOrder productOrder5 = ProductOrder.builder()
                .product(product5)
                .quantity(5)
                .build();
        ProductOrder productOrder6 = ProductOrder.builder()
                .product(product6)
                .quantity(6)
                .build();
        ProductOrder productOrder7 = ProductOrder.builder()
                .product(product7)
                .quantity(7)
                .build();
        ProductOrder productOrder8 = ProductOrder.builder()
                .product(product8)
                .quantity(8)
                .build();
        ProductOrder productOrder9 = ProductOrder.builder()
                .product(product9)
                .quantity(9)
                .build();
        ProductOrder productOrder10 = ProductOrder.builder()
                .product(product9)
                .quantity(10)
                .build();
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
                .fromWarehouse("Warehouse1")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder1, productOrder2, productOrder3))
                .build();
        Delivery delivery2 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 2))
                .fromWarehouse("Warehouse2")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder4, productOrder5))
                .build();
        Delivery delivery3 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 3))
                .fromWarehouse("Warehouse3")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder6, productOrder7))
                .build();
        Delivery delivery4 = Delivery.builder()
                .deliveryDate(LocalDate.of(2021, 1, 4))
                .fromWarehouse("Warehouse4")
                .destination("Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N")
                .productOrders(List.of(productOrder8))
                .build();
        deliveryRepository.save(delivery1);
        deliveryRepository.save(delivery2);
        deliveryRepository.save(delivery3);
        deliveryRepository.save(delivery4);

        productOrder1.setDelivery(delivery1);
        productOrder2.setDelivery(delivery1);
        productOrder3.setDelivery(delivery1);
        productOrder4.setDelivery(delivery2);
        productOrder5.setDelivery(delivery2);
        productOrder6.setDelivery(delivery3);
        productOrder7.setDelivery(delivery3);
        productOrder8.setDelivery(delivery4);
        productOrderRepository.save(productOrder1);
        productOrderRepository.save(productOrder2);
        productOrderRepository.save(productOrder3);
        productOrderRepository.save(productOrder4);
        productOrderRepository.save(productOrder5);
        productOrderRepository.save(productOrder6);
        productOrderRepository.save(productOrder7);
        productOrderRepository.save(productOrder8);

        van1.setDeliveries(List.of(delivery1, delivery2));
        van2.setDeliveries(List.of(delivery3));
        van3.setDeliveries(List.of(delivery4));
    }


}
