package com.example.pokemoncopy5.repository;

import com.example.pokemoncopy5.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
}
