package com.example.dagligvareleveringeksamen24.repository;

import com.example.dagligvareleveringeksamen24.entity.Van;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanRepository extends JpaRepository<Van, Long> {
}

