package com.example.easyshop.repository;

import com.example.easyshop.entity.ProductInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInstanceRepository extends JpaRepository<ProductInstance, Long> {
}
