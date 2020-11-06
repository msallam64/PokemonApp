package com.example.pokemonapp.Networking;

import com.example.pokemonapp.Model.PokemonRespons;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;


public interface PokemonAPIservice {

    @GET("pokemon")
    Observable<PokemonRespons> getPokemons();
}
