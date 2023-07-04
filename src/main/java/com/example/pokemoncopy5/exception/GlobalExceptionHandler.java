package com.example.pokemoncopy5.exception;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorObject> pokemonExceptionHandler(PokemonNotFoundException pokemonNotFoundException, WebRequest request){

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(pokemonNotFoundException.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> errorObject(ReviewNotFoundException reviewNotFoundException, WebRequest request){

        ErrorObject errorObject = new ErrorObject();

        errorObject.setTimeStamp(new Date());
        errorObject.setMessage(reviewNotFoundException.getMessage());
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

}
