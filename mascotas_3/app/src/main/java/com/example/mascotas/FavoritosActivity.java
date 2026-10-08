package com.example.mascotas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritosActivity extends AppCompatActivity {

    RecyclerView recyclerFavoritos;
    Toolbar toolbarFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favoritos);

        // Toolbar
        toolbarFavoritos =
                findViewById(R.id.toolbarFavoritos);

        setSupportActionBar(toolbarFavoritos);

        // Mostrar flecha para regresar
        if (getSupportActionBar() != null) {

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        // RecyclerView
        recyclerFavoritos =
                findViewById(R.id.recyclerFavoritos);

        ArrayList<Mascota> favoritos =
                new ArrayList<>();

        favoritos.add(
                new Mascota(
                        "Firulais",
                        R.drawable.mascota1,
                        5
                )
        );

        favoritos.add(
                new Mascota(
                        "Michi",
                        R.drawable.mascota2,
                        4
                )
        );

        favoritos.add(
                new Mascota(
                        "Toby",
                        R.drawable.mascota3,
                        5
                )
        );

        favoritos.add(
                new Mascota(
                        "Nala",
                        R.drawable.mascota4,
                        3
                )
        );

        favoritos.add(
                new Mascota(
                        "Bruno",
                        R.drawable.mascota1,
                        4
                )
        );

        AdaptadorMascota adapter =
                new AdaptadorMascota(favoritos);

        recyclerFavoritos.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerFavoritos.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return true;
    }
}