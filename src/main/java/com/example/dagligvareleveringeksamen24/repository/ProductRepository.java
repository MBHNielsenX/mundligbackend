package com.example.dagligvareleveringeksamen24.repository;

import com.example.dagligvareleveringeksamen24.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Boolean existsByName(String name);

}

