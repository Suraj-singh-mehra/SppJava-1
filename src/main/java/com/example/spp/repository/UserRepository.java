package com.example.spp.repository;

import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findByEmail(String email);

    List<User> findByRole(UserRole role);
}
