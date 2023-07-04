package com.example.pokemoncopy5.service.impl;

import com.example.pokemoncopy5.dto.ReviewDto;
import com.example.pokemoncopy5.entity.Pokemon;
import com.example.pokemoncopy5.entity.Review;
import com.example.pokemoncopy5.exception.PokemonNotFoundException;
import com.example.pokemoncopy5.repository.PokemonRepository;
import com.example.pokemoncopy5.repository.ReviewRepository;
import com.example.pokemoncopy5.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PokemonRepository pokemonRepository;



    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {

        Review review = mapToReview(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    private ReviewDto mapToDto(Review review){

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }

    private Review mapToReview(ReviewDto reviewDto){

        Review review = new Review();

        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        return review;
    }

}
