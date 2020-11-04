package com.example.pokemonapp.Networking;

import com.example.pokemonapp.Model.PokemonRespons;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface PokemonAPIservice {

    @GET("pokemon")
    Observable<PokemonRespons> getPokemons();
}
