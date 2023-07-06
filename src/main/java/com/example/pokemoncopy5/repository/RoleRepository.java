package com.example.pokemoncopy5.repository;

import com.example.pokemoncopy5.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String user);
}
