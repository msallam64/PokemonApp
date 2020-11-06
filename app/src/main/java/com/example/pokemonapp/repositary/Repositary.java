package com.example.pokemonapp.repositary;

import com.example.pokemonapp.model.PokemonRespons;
import com.example.pokemonapp.networking.PokemonAPIservice;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repositary {
    private PokemonAPIservice pokemonAPIservice;

    @Inject
    public Repositary(PokemonAPIservice pokemonAPIservice) {
        this.pokemonAPIservice = pokemonAPIservice;
    }

    public Observable<PokemonRespons> getPokemons() {
        return pokemonAPIservice.getPokemons();
    }
}
