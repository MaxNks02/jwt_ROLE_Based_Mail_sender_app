package com.example.jwtexample.repository;


import com.example.jwtexample.entity.Role;
import com.example.jwtexample.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
