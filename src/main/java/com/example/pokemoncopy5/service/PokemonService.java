package com.example.pokemoncopy5.service;

import com.example.pokemoncopy5.dto.PokemonDto;

public interface PokemonService {


    PokemonDto createPokemon(PokemonDto pokemonDto);
<<<<<<< Updated upstream
=======

    PokemonDto getPokemonById(int id);

    PokemonResponse getAllPokmeon(int pageNo, int pageSize);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int pokemonId);
>>>>>>> Stashed changes
}
