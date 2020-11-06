package com.example.pokemonapp.di;

import android.app.Application;

import androidx.room.Room;

import com.example.pokemonapp.db.PokemonDao;
import com.example.pokemonapp.db.PokemonDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static PokemonDataBase provideDB(Application application) {
        return Room.databaseBuilder(application, PokemonDataBase.class, "fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokemonDao provideDAO(PokemonDataBase pokemonDataBase) {
        return pokemonDataBase.pokemonDao();
    }
}
