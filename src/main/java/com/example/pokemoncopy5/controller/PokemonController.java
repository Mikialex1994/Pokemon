package com.example.pokemoncopy5.controller;

import com.example.pokemoncopy5.dto.PokemonDto;
import com.example.pokemoncopy5.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){

        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto),HttpStatus.CREATED);

    }

}
