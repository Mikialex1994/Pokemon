package com.example.pokemoncopy5.service;

import com.example.pokemoncopy5.dto.PokemonDto;
import com.example.pokemoncopy5.dto.PokemonResponse;

public interface PokemonService {


    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonDto getPokemonById(int id);

    PokemonResponse getAllPokmeon(int pageNo, int pageSize);
}
