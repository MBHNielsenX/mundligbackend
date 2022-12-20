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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {
    DeliveryRepository deliveryRepository;
    ProductRepository productRepository;
    ProductOrderRepository productOrderRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, ProductRepository productRepository, ProductOrderRepository productOrderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.productRepository = productRepository;
        this.productOrderRepository = productOrderRepository;
    }

    public List<DeliveryResponse> getAll() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries.stream().map(delivery -> new DeliveryResponse(delivery)).toList();
    }

    public DeliveryResponse getById(Long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
        return new DeliveryResponse(delivery);
    }

    public DeliveryResponse addDelivery(DeliveryRequest deliveryRequest) {

        List<ProductOrder> productOrders = new ArrayList<>();
        for (int i = 0; i < deliveryRequest.getProductOrderIds().size(); i++) {
            ProductOrder temp = productOrderRepository.findById(deliveryRequest.getProductOrderIds().get(i)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ProductOrder not found"));
            if (temp.getDelivery() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ProductOrder already has a delivery");
            }
            productOrders.add(temp);
        }
        Delivery tempDelivery = Delivery.builder()
                .deliveryDate(deliveryRequest.getDeliveryDate())
                .fromWarehouse(deliveryRequest.getFromWarehouse())
                .destination(deliveryRequest.getDestination())
                .productOrders(productOrders)
                .build();
        deliveryRepository.save(tempDelivery);

        for (int i = 0; i < productOrders.size(); i++) {
            ProductOrder temp = productOrders.get(i);
            temp.setDelivery(tempDelivery);
            productOrderRepository.save(temp);
        }

        return new DeliveryResponse(tempDelivery);
    }

    public void editDelivery(DeliveryRequest deliveryRequest) {
        Delivery foundDelivery = deliveryRepository.findById(deliveryRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));

        List<ProductOrder> productOrders = new ArrayList<>();
        for (int i = 0; i < deliveryRequest.getProductOrderIds().size(); i++) {
            ProductOrder temp = productOrderRepository.findById(deliveryRequest.getProductOrderIds().get(i)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ProductOrder not found"));
            if (temp.getDelivery() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ProductOrder already has a delivery");
            }
            productOrders.add(temp);
        }
        if (deliveryRequest.getDeliveryDate() != null) {
            foundDelivery.setDeliveryDate(deliveryRequest.getDeliveryDate());
        }
        if (deliveryRequest.getFromWarehouse() != null) {
            foundDelivery.setFromWarehouse(deliveryRequest.getFromWarehouse());
        }
        if (deliveryRequest.getDestination() != null) {
            foundDelivery.setDestination(deliveryRequest.getDestination());
        }
        if (deliveryRequest.getProductOrderIds() != null) {
            foundDelivery.setProductOrders(productOrders);
        }
        deliveryRepository.save(foundDelivery);

        for (int i = 0; i < productOrders.size(); i++) {
            ProductOrder temp = productOrders.get(i);
            temp.setDelivery(foundDelivery);
            productOrderRepository.save(temp);
        }
    }

    public void deleteDelivery(Long id) {
        Delivery foundDelivery = deliveryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
        List<ProductOrder> productOrders = foundDelivery.getProductOrders();
        for (int i = 0; i < productOrders.size(); i++) {
            ProductOrder temp = productOrders.get(i);
            temp.setDelivery(null);
            productOrderRepository.save(temp);
        }
        deliveryRepository.delete(foundDelivery);
    }
}
