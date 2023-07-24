package com.example.pokemoncopy5.service;

import com.example.pokemoncopy5.dto.PokemonDto;
import com.example.pokemoncopy5.entity.Pokemon;
import com.example.pokemoncopy5.repository.PokemonRepository;
import com.example.pokemoncopy5.service.impl.PokemonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    public void PokemonService_CreatePokemon_ReturnPokemonDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto pokemonDto1 = pokemonService.createPokemon(pokemonDto);

        Assertions.assertThat(pokemonDto1).isNotNull();

    }

    @Test
    public void PokemonService_GetById_ReturnDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

        PokemonDto pokemonDto = pokemonService.getPokemonById(1);

        Assertions.assertThat(pokemonDto).isNotNull();

    }

    @Test
    public void PokemonService_UpdatePokemon_ReturnPokemonDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto pokemonDto1 = pokemonService.updatePokemon(pokemonDto,1);

        Assertions.assertThat(pokemonDto1).isNotNull();

    }

    @Test
    public void PokemonService_DeletePokemon_ReturnDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

       assertAll(()-> pokemonService.deletePokemon(1));

    }

}
