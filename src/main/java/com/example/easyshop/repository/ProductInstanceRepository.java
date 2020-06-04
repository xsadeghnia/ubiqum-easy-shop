package com.example.easyshop.repository;

import com.example.easyshop.entity.ProductInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInstanceRepository extends JpaRepository<ProductInstance, Long> {
    List<ProductInstance> findByProductNameContains(String name);
}
