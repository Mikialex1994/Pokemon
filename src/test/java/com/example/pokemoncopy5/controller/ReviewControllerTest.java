package com.example.pokemoncopy5.controller;

import com.example.pokemoncopy5.dto.PokemonDto;
import com.example.pokemoncopy5.dto.ReviewDto;
import com.example.pokemoncopy5.entity.Pokemon;
import com.example.pokemoncopy5.entity.Review;
import com.example.pokemoncopy5.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.ContentRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void ReviewController_GetReviewByPokemonId() throws Exception {

        int pokemonId = 1;

        when(reviewService.getReviewsByPokemonId(pokemonId)).thenReturn(Arrays.asList(reviewDto));

        ResultActions response = mockMvc.perform(get("/api/pokemon/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(reviewDto).size())));

    }

    @Test
    public void ReviewController_UpdateReview_ReturnReview() throws Exception{

        int pokemonId = 1;
        int reviewId = 1;

        when(reviewService.updateReview(reviewId,pokemonId,reviewDto)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(put("/api/pokemon/1/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",CoreMatchers.is(reviewDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",CoreMatchers.is(reviewDto.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars",CoreMatchers.is(reviewDto.getStars())));

    }

    @Test
    public void ReviewController_CreateReview_ReturnReviewDto() throws Exception {

        int pokemonId = 1;

        when(reviewService.createReview(pokemonId,reviewDto)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(post("/api/pokemon/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",CoreMatchers.is(reviewDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",CoreMatchers.is(reviewDto.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars",CoreMatchers.is(reviewDto.getStars())));

    }

    @Test
    public void ReviewController_GetReviewById_ReturnReviewDto() throws Exception {

        int pokemonId = 1;
        int reviewId = 1;

        when(reviewService.getPokemonReviewById(reviewId,pokemonId)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(get("/api/pokemon/1/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",CoreMatchers.is(reviewDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",CoreMatchers.is(reviewDto.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars",CoreMatchers.is(reviewDto.getStars())));

    }

    @Test
    public void ReviewController_DeleteReview_ReturnIsEmpty() throws Exception {

        int pokemonId = 1;
        int reviewId = 1;

        doNothing().when(reviewService).deleteReviews(reviewId,pokemonId);

        ResultActions response = mockMvc.perform(delete("/api/pokemon/1/reviews/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }





}
