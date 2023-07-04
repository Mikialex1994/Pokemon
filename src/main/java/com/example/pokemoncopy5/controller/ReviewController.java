package com.example.pokemoncopy5.controller;

import com.example.pokemoncopy5.dto.ReviewDto;
import com.example.pokemoncopy5.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable(value = "pokemonId") int pokemonId){

        return new ResponseEntity<>(reviewService.createReview(pokemonId,reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{pokemonId}/reviews")
    public List<ReviewDto> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId){

        return reviewService.getReviewsByPokemonId(pokemonId);
    }

    @GetMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "pokemonId") int pokemonId,@PathVariable(value = "id") int reviewId){

        ReviewDto reviewDto = reviewService.getPokemonReviewById(pokemonId,reviewId);

        return new ResponseEntity<>(reviewDto,HttpStatus.OK);
    }

    @PutMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "id") int reviewId, @RequestBody ReviewDto reviewDto) {

        return new ResponseEntity<>(reviewService.updateReview(pokemonId,reviewId,reviewDto),HttpStatus.OK);
    }

}
