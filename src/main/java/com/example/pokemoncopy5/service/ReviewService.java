package com.example.pokemoncopy5.service;

import com.example.pokemoncopy5.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto);

    List<ReviewDto> getReviewsByPokemonId(int pokemonId);

    ReviewDto getPokemonReviewById(int pokemonId, int reviewId);

    ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto);
}
