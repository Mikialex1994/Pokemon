package com.example.pokemoncopy5.service;

import com.example.pokemoncopy5.dto.PokemonDto;

public interface PokemonService {


    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonDto getPokemonById(int id);
}
