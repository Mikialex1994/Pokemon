package com.example.pokemoncopy5.service;

import com.example.pokemoncopy5.dto.PokemonDto;
import com.example.pokemoncopy5.dto.ReviewDto;
import com.example.pokemoncopy5.entity.Pokemon;
import com.example.pokemoncopy5.entity.Review;
import com.example.pokemoncopy5.repository.PokemonRepository;
import com.example.pokemoncopy5.repository.ReviewRepository;
import com.example.pokemoncopy5.service.impl.ReviewServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Pokemon pokemon;
    private Review review;
    private ReviewDto reviewDto;
    private PokemonDto pokemonDto;

    @BeforeEach
    public void setup(){

         pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

         pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewDto = ReviewDto.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

    }

    @Test
    public void ReviewService_CreateReview_ReturnReview(){

        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.of(pokemon));
        when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);

        ReviewDto saveReview = reviewService.createReview(pokemon.getId(), reviewDto);

        Assertions.assertThat(saveReview).isNotNull();

    }

    @Test
    public void ReviewService_GetReviewsByPokemonId_ReturnReviewDto(){

        int reviewId = 1;
        int pokemonId= 1;

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findByPokemonId(reviewId)).thenReturn(Arrays.asList(review));

        List<ReviewDto> pokemonReturn = reviewService.getReviewsByPokemonId(reviewId);

        Assertions.assertThat(pokemonReturn).isNotNull();

    }

    @Test
    public void ReviewService_GetReviewById_ReturnReviewDto(){

        int reviewID = 1;
        int pokemonId = 1;

        review.setPokemon(pokemon);

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewID)).thenReturn(Optional.of(review));

        ReviewDto reviewReturn = reviewService.getPokemonReviewById(reviewID,pokemonId);

        Assertions.assertThat(reviewReturn).isNotNull();

    }

    @Test
    public void ReviewService_UpdateReviews_ReturnReviewDto(){

        int pokemonId = 1;
        int reviewId = 1;

        review.setPokemon(pokemon);

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(reviewRepository.save(review)).thenReturn(review);

        ReviewDto reviewDto1 = reviewService.updateReview(pokemonId,reviewId,reviewDto);

        Assertions.assertThat(reviewDto1).isNotNull();

    }

    @Test
    public void ReviewService_DeleteReview_ReturnReviewDto(){

        int pokemonId = 1;
        int reviewId = 1;

        review.setPokemon(pokemon);

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        assertAll(()-> reviewService.deleteReviews(pokemonId,reviewId));

    }

}
