package com.example.pokemoncopy5.repository;

import com.example.pokemoncopy5.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

}
