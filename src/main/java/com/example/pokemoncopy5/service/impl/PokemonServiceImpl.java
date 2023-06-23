package com.example.pokemoncopy5.service.impl;

import com.example.pokemoncopy5.dto.PokemonDto;
import com.example.pokemoncopy5.entity.Pokemon;
import com.example.pokemoncopy5.repository.PokemonRepository;
import com.example.pokemoncopy5.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {

        Pokemon pokemon = new Pokemon();

        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon pokemon1 = pokemonRepository.save(pokemon);

        PokemonDto pokemonDto1 = new PokemonDto();

        pokemonDto1.setId(pokemon1.getId());
        pokemonDto1.setName(pokemon1.getName());
        pokemonDto1.setType(pokemon1.getType());

        return pokemonDto1;
    }



    private PokemonDto mapToDto(Pokemon pokemon){

        PokemonDto pokemonDto1 = new PokemonDto();

        pokemonDto1.setId(pokemon.getId());
        pokemonDto1.setName(pokemon.getName());
        pokemonDto1.setType(pokemon.getType());

        return pokemonDto1;
    }

    private Pokemon mapToPokemon(PokemonDto pokemonDto){

        Pokemon pokemon = new Pokemon();

        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        return pokemon;
    }




}
