package com.example.easyshop.repository;

import com.example.easyshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String string);
    Optional<Category> findByPrentName(String string);

}
