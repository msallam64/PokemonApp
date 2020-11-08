package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokemonapp.adapters.PokemonAdapter;
import com.example.pokemonapp.model.Pokemon;
import com.example.pokemonapp.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class FavouritActivity extends AppCompatActivity {

    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourit);

        recyclerView = findViewById(R.id.fav_pokemon_recelerview);
        adapter = new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);
        setUpSwipe();

        Button toHome_btn = findViewById(R.id.to_home_btn);
        toHome_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouritActivity.this, MainActivity.class));
            }
        });
        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getFavPokemon();

        viewModel.getFavList().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                ArrayList<Pokemon> list = new ArrayList<>();
                list.addAll(pokemons);
                adapter.setList(list);
            }
        });
    }

    private void setUpSwipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                Pokemon swipedPokemon = adapter.getPokemonAt(swipedPokemonPosition);
                viewModel.deletePokemon(swipedPokemon.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(FavouritActivity.this, "pokemon deleted from database", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}