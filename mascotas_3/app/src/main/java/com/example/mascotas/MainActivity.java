package com.example.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerMascotas;
    FloatingActionButton btnSubir;
    Toolbar toolbar;

    ArrayList<Mascota> mascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Toolbar personalizada
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Lista
        recyclerMascotas =
                findViewById(R.id.recyclerMascotas);

        // Botón subir
        btnSubir =
                findViewById(R.id.btnSubir);

        // Crear lista de mascotas
        mascotas = new ArrayList<>();

        mascotas.add(
                new Mascota(
                        "Romeo Santos",
                        R.drawable.mascota1,
                        4
                )
        );

        mascotas.add(
                new Mascota(
                        "Bananitas",
                        R.drawable.mascota2,
                        3
                )
        );

        mascotas.add(
                new Mascota(
                        "Lunita",
                        R.drawable.mascota3,
                        5
                )
        );

        mascotas.add(
                new Mascota(
                        "Familia",
                        R.drawable.mascota4,
                        0
                )
        );

        // Adaptador
        AdaptadorMascota adapter =
                new AdaptadorMascota(mascotas);

        // RecyclerView
        recyclerMascotas.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerMascotas.setAdapter(adapter);

        // Botón para subir al principio
        btnSubir.setOnClickListener(v ->
                recyclerMascotas.smoothScrollToPosition(0)
        );
    }

    // Mostrar menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(
                R.menu.menu_main,
                menu
        );

        return true;
    }

    // Acción de la estrella
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==
                R.id.action_favoritos) {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            FavoritosActivity.class
                    );

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}