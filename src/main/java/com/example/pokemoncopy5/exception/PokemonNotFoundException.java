package com.example.pokemoncopy5.exception;

public class PokemonNotFoundException extends RuntimeException {

    public PokemonNotFoundException(String message){

        super(message);
    }

}
