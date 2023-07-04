package com.example.pokemoncopy5.repository;

import com.example.pokemoncopy5.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {


    List<Review> findByPokemonId(int pokemonId);
}
