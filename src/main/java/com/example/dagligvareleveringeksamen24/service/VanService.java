package com.example.dagligvareleveringeksamen24.service;

import com.example.dagligvareleveringeksamen24.dto.VanRequest;
import com.example.dagligvareleveringeksamen24.dto.VanResponse;
import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.example.dagligvareleveringeksamen24.entity.Van;
import com.example.dagligvareleveringeksamen24.repository.DeliveryRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductOrderRepository;
import com.example.dagligvareleveringeksamen24.repository.ProductRepository;
import com.example.dagligvareleveringeksamen24.repository.VanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VanService {

    VanRepository vanRepository;
    DeliveryRepository deliveryRepository;
    ProductRepository productRepository;
    ProductOrderRepository productOrderRepository;

    public VanService(VanRepository vanRepository, DeliveryRepository deliveryRepository, ProductRepository productRepository, ProductOrderRepository productOrderRepository) {
        this.vanRepository = vanRepository;
        this.deliveryRepository = deliveryRepository;
        this.productRepository = productRepository;
        this.productOrderRepository = productOrderRepository;

    }

    public List<VanResponse> getAll() {
        return vanRepository.findAll().stream().map(van -> new VanResponse(van)).toList();
    }

    public VanResponse getById(Long id) {
        Van van = vanRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        return new VanResponse(van);
    }

    public VanResponse addVan(VanRequest vanRequest) {
        Van tempVan = Van.builder()
                .brand(vanRequest.getBrand())
                .model(vanRequest.getModel())
                .capacity(vanRequest.getCapacity())
                .build();
        vanRepository.save(tempVan);
        return new VanResponse(tempVan);
    }

    public void editVan(VanRequest vanRequest) {
        Van foundVan = vanRepository.findById(vanRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Van Not Found"));
        if(vanRequest.getBrand() != null) {
            foundVan.setBrand(vanRequest.getBrand());
        }
        if(vanRequest.getModel() != null) {
            foundVan.setModel(vanRequest.getModel());
        }
        if(vanRequest.getCapacity() != 0) {
            foundVan.setCapacity(vanRequest.getCapacity());
        }
        if (vanRequest.getDeliveryIds() != null) {
            List<Delivery> deliveries = deliveryRepository.findAllById(vanRequest.getDeliveryIds());
            foundVan.setDeliveries(deliveries);
        }
        vanRepository.save(foundVan);
    }

    public void deleteVan(Long id) {
        Van foundVan = vanRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Van Not Found"));
        List<Delivery> deliveries = foundVan.getDeliveries();
        for (Delivery delivery : deliveries) {
            delivery.setVan(null);
            deliveryRepository.save(delivery);
        }
        vanRepository.deleteById(id);
    }

}
