package com.example.pokemoncopy5.repository;

import com.example.pokemoncopy5.entity.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonRepository_SaveAll_ReturnSavePokemon(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon savePokemon = pokemonRepository.save(pokemon);

        Assertions.assertThat(savePokemon).isNotNull();
        Assertions.assertThat(savePokemon.getId()).isGreaterThan(0);

    }

    @Test
    public void PokemonRepository_GetAll_ReturnPokemon(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon pokemon1 = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

         pokemonRepository.save(pokemon);
         pokemonRepository.save(pokemon1);

        List<Pokemon> pokemonList = pokemonRepository.findAll();

        Assertions.assertThat(pokemonList).isNotNull();
        Assertions.assertThat(pokemonList.size()).isGreaterThan(1);

    }

    @Test
    public void PokemonRepository_FindById_ReturnPokemon(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemon1 = pokemonRepository.findById(pokemon.getId()).get();

        Assertions.assertThat(pokemon1.getId()).isNotNull();
        Assertions.assertThat(pokemon1.getId()).isGreaterThan(0);

    }

    @Test
    public void PokemonRepository_FindByType_ReturnPokemon(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemon1 = pokemonRepository.findByType(pokemon.getType());

        Assertions.assertThat(pokemon1.getType()).isNotNull();

    }

    @Test
    public void PokemonRepository_UpdatePokemon_ReturnPokemon(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemon1 = pokemonRepository.findById(pokemon.getId()).get();

        pokemon1.setName("Ditto");
        pokemon1.setType("normal");

        Pokemon pokemon2 = pokemonRepository.save(pokemon1);

        Assertions.assertThat(pokemon2.getName()).isNotNull();
        Assertions.assertThat(pokemon2.getType()).isNotNull();

    }

    @Test
    public void PokemonRepository_DeletePokemon_ReturnString(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);

        pokemonRepository.deleteById(pokemon.getId());

        Optional<Pokemon> pokemon1 = pokemonRepository.findById(pokemon.getId());

        Assertions.assertThat(pokemon1).isEmpty();

    }

}
