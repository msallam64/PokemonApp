package com.example.pokemonapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokemonapp.model.Pokemon;

@Database(entities = Pokemon.class, version = 1, exportSchema = false)
public abstract class PokemonDataBase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();

}
