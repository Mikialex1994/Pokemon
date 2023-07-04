package com.example.pokemoncopy5.service;

import com.example.pokemoncopy5.dto.ReviewDto;

public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto);
}
