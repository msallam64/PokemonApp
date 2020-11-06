package com.example.pokemonapp.networking;

import com.example.pokemonapp.model.PokemonRespons;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;


public interface PokemonAPIservice {

    @GET("pokemon")
    Observable<PokemonRespons> getPokemons();
}
