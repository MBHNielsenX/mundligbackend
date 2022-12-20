package com.example.dagligvareleveringeksamen24.repository;

import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findAllByDeliveryId(Long id);
}

