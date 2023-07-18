package com.example.pokemoncopy5.service.impl;

import com.example.pokemoncopy5.dto.ReviewDto;
import com.example.pokemoncopy5.entity.Pokemon;
import com.example.pokemoncopy5.entity.Review;
import com.example.pokemoncopy5.exception.PokemonNotFoundException;
import com.example.pokemoncopy5.exception.ReviewNotFoundException;
import com.example.pokemoncopy5.repository.PokemonRepository;
import com.example.pokemoncopy5.repository.ReviewRepository;
import com.example.pokemoncopy5.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int pokemonId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

        List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);

       return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getPokemonReviewById(int pokemonId, int reviewId) {

       Pokemon pokemon = pokemonRepository.findById(pokemonId)
               .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id not found"));

       Review review = reviewRepository.findById(reviewId)
               .orElseThrow(()-> new ReviewNotFoundException("Review with associated id not found"));

       if(review.getPokemon().getId() != pokemon.getId()){

           throw new ReviewNotFoundException("Review not found");
       }

       return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id is not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated id not found"));

        if(review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This review does not belong to a pokemon");

        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    @Override
    public void deleteReviews(int pokemonId, int reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated id is not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated id not found"));

        if(review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This review does not belong to a pokemon");

        }

        reviewRepository.delete(review);

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
