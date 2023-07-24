package com.example.pokemoncopy5.controller;

import com.example.pokemoncopy5.dto.PokemonDto;
import com.example.pokemoncopy5.dto.PokemonResponse;
import com.example.pokemoncopy5.dto.ReviewDto;
import com.example.pokemoncopy5.entity.Pokemon;
import com.example.pokemoncopy5.entity.Review;
import com.example.pokemoncopy5.repository.PokemonRepository;
import com.example.pokemoncopy5.service.PokemonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PokemonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pokemon pokemon;
    private Review review;
    private ReviewDto reviewDto;
    private PokemonDto pokemonDto;

    @BeforeEach
    public void init(){

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
    public void PokemonController_CreatePokemon_ReturnCrated() throws Exception {

        given(pokemonService.createPokemon(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/pokemon/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pokemonDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(pokemonDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.is(pokemonDto.getType())));

    }

    @Test
    public void PokemonController_GetAllPokemon_ReturnResponseDto() throws Exception{

        PokemonResponse pokemonResponse = PokemonResponse.builder()
                .pageSize(10)
                .last(true)
                .pageNo(1)
                .content(Arrays.asList(pokemonDto))
                .build();

        when(pokemonService.getAllPokmeon(1,10)).thenReturn(pokemonResponse);

        ResultActions response = mockMvc.perform(get("/api/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo","1")
                .param("pageSize", "10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()",CoreMatchers.is(pokemonResponse.getContent().size())));

    }

    @Test
    public void PokemonController_GetPokemonById_ReturnPokemonDto() throws Exception {

        int pokemonId = 1;

        when(pokemonService.getPokemonById(pokemonId)).thenReturn(pokemonDto);

        ResultActions response = mockMvc.perform(get("/api/pokemon/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pokemonDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(pokemonDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type",CoreMatchers.is(pokemonDto.getType())));

    }

    @Test
    public void PokemonController_UpdatePokemon_ReturnPokemonDto() throws Exception {

        int pokemonId = 1;

        when(pokemonService.updatePokemon(pokemonDto,pokemonId)).thenReturn(pokemonDto);

        ResultActions response = mockMvc.perform(put("/api/pokemon/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pokemonDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(pokemonDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.is(pokemonDto.getType())));

    }

    @Test
    public void PokemonController_DeletePokemon_ReturnPokemonDto() throws Exception {

        int pokemonId = 1;

        doNothing().when(pokemonService).deletePokemon(pokemonId);

        ResultActions response = mockMvc.perform(delete("/api/pokemon/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }


}
