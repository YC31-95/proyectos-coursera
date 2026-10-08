package com.example.mascotas;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity
        implements AdaptadorMascota.OnRatingChangedListener {

    private RecyclerView recyclerFavoritos;
    private MascotaDatabase database;
    private AdaptadorMascota adapter;
    private final ArrayList<Mascota> mascotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favoritos);

        Toolbar toolbarFavoritos = findViewById(R.id.toolbarFavoritos);
        setSupportActionBar(toolbarFavoritos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerFavoritos = findViewById(R.id.recyclerFavoritos);
        recyclerFavoritos.setLayoutManager(
                new LinearLayoutManager(this)
        );

        adapter = new AdaptadorMascota(mascotas, this);
        recyclerFavoritos.setAdapter(adapter);

        database = MascotaDatabase.getDatabase(this);

        cargarMascotas();
    }

    private void cargarMascotas() {

        MascotaDatabase.databaseWriteExecutor.execute(() -> {

            List<Mascota> guardadas =
                    database.mascotaDao().obtenerUltimasCinco();

            runOnUiThread(() -> {

                mascotas.clear();

                if (guardadas.isEmpty()) {
                    mascotas.addAll(mascotasIniciales());
                } else {
                    mascotas.addAll(guardadas);
                }

                adapter.notifyDataSetChanged();
            });
        });
    }

    private ArrayList<Mascota> mascotasIniciales() {

        ArrayList<Mascota> lista = new ArrayList<>();

        lista.add(new Mascota("Firulais", R.drawable.mascota1, 0));
        lista.add(new Mascota("Michi", R.drawable.mascota2, 0));
        lista.add(new Mascota("Toby", R.drawable.mascota3, 0));
        lista.add(new Mascota("Nala", R.drawable.mascota4, 0));
        lista.add(new Mascota("Bruno", R.drawable.mascota1, 0));

        return lista;
    }

    @Override
    public void onRatingChanged(Mascota mascota) {
        Mascota copia = new Mascota(
                0,
                mascota.getNombre(),
                mascota.getFoto(),
                mascota.getRating()
        );

        MascotaDatabase.databaseWriteExecutor.execute(() -> {

            database.mascotaDao().insertar(copia);
            database.mascotaDao().conservarUltimasCinco();

            List<Mascota> ultimas =
                    database.mascotaDao().obtenerUltimasCinco();

            runOnUiThread(() -> {

                mascotas.clear();
                mascotas.addAll(ultimas);
                adapter.notifyDataSetChanged();

                Toast.makeText(
                        this,
                        "Rating guardado. Se conservan las últimas 5 mascotas.",
                        Toast.LENGTH_SHORT
                ).show();
            });
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
