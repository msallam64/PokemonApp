package com.example.pokemonapp.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemonapp.Model.Pokemon;
import com.example.pokemonapp.Model.PokemonRespons;
import com.example.pokemonapp.Repositary.Repositary;

import java.util.ArrayList;

import io.reactivex.rxjava3.functions.Function;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private Repositary repository;
    MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();

    @ViewModelInject
    public PokemonViewModel(Repositary repositary) {
        this.repository = repositary;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonRespons, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonRespons pokemonResponse) throws Throwable {
                        ArrayList<Pokemon> list = pokemonResponse.getResult();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }


}
