package com.example.dagligvareleveringeksamen24.service;

import com.example.dagligvareleveringeksamen24.dto.ProductOrderRequest;
import com.example.dagligvareleveringeksamen24.dto.ProductOrderResponse;
import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.example.dagligvareleveringeksamen24.entity.Product;
import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.example.dagligvareleveringeksamen24.repository.DeliveryRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductOrderRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductOrderService {

    ProductOrderRepository productOrderRepository;
    ProductRepository productRepository;
    DeliveryRepository deliveryRepository;

    public ProductOrderService(ProductOrderRepository productOrderRepository, ProductRepository productRepository, DeliveryRepository deliveryRepository) {
        this.productOrderRepository = productOrderRepository;
        this.productRepository = productRepository;
        this.deliveryRepository = deliveryRepository;
    }

    public List<ProductOrderResponse> getAll() {
        List<ProductOrder> productOrders = productOrderRepository.findAll();
        return productOrders.stream().map(productOrder -> new ProductOrderResponse(productOrder)).toList();
    }

    public ProductOrderResponse getProductOrderById(Long id) {
        ProductOrder productOrder = productOrderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ProductOrder not found"));
        return new ProductOrderResponse(productOrder);
    }

    public ProductOrderResponse addProductOrder(ProductOrderRequest productOrderRequest) {
        Delivery delivery = deliveryRepository.findById(productOrderRequest.getDeliveryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
        Product product = productRepository.findById(productOrderRequest.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
        ProductOrder tempProductOrder = ProductOrder.builder()
                .quantity(productOrderRequest.getQuantity())
                .product(product)
                .delivery(delivery)
                .build();
        productOrderRepository.save(tempProductOrder);
        return new ProductOrderResponse(tempProductOrder);
    }

    public void editProductOrder(ProductOrderRequest productOrderRequest) {
        ProductOrder foundProductOrder = productOrderRepository.findById(productOrderRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ProductOrder not found"));

        Product product = productRepository.findById(productOrderRequest.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
        Delivery delivery = deliveryRepository.findById(productOrderRequest.getDeliveryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
        if (productOrderRequest.getQuantity() > 0) {
            foundProductOrder.setQuantity(productOrderRequest.getQuantity());
        }
        foundProductOrder.setProduct(product);
        foundProductOrder.setDelivery(delivery);
        productOrderRepository.save(foundProductOrder);
    }

    public void deleteProductOrder(Long id) {
        ProductOrder foundProductOrder = productOrderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductOrder not found"));
        productOrderRepository.delete(foundProductOrder);
    }

}
