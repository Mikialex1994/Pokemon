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

<<<<<<< Updated upstream
=======
    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable(value = "id") int id){

        return new ResponseEntity<>(pokemonService.getPokemonById(id),HttpStatus.OK);
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemon(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){

        return new ResponseEntity<>(pokemonService.getAllPokmeon(pageNo,pageSize),HttpStatus.OK);

    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId){

        PokemonDto response = pokemonService.updatePokemon(pokemonDto,pokemonId);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

>>>>>>> Stashed changes
}
