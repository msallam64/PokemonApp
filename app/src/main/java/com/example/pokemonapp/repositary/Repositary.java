package com.example.pokemonapp.repositary;

import androidx.lifecycle.LiveData;

import com.example.pokemonapp.db.PokemonDao;
import com.example.pokemonapp.model.Pokemon;
import com.example.pokemonapp.model.PokemonRespons;
import com.example.pokemonapp.networking.PokemonAPIservice;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repositary {
    private PokemonAPIservice pokemonAPIservice;
    private PokemonDao pokemonDao;

    @Inject
    public Repositary(PokemonAPIservice pokemonAPIservice, PokemonDao pokemonDao) {
        this.pokemonAPIservice = pokemonAPIservice;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonRespons> getPokemons() {
        return pokemonAPIservice.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon) {
        pokemonDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        pokemonDao.deletePokemon(pokemonName);
    }

    public LiveData<List<Pokemon>> getFavourit() {
        return pokemonDao.getPokemons();
    }
}
