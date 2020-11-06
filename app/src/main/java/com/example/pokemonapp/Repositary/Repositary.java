package com.example.pokemonapp.Repositary;

import com.example.pokemonapp.Model.PokemonRespons;
import com.example.pokemonapp.Networking.PokemonAPIservice;

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
