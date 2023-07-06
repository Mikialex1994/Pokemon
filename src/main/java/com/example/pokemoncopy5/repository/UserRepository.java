package com.example.pokemoncopy5.repository;

import com.example.pokemoncopy5.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
