package com.example.easyshop.repository;

import com.example.easyshop.entity.Admin;
import com.example.easyshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    interface UserRepository extends JpaRepository<User, Long> {
    }
}
