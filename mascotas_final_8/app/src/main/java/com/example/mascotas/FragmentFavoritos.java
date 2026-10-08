package com.example.mascotas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavoritos extends Fragment
        implements AdaptadorMascota.OnRatingChangedListener {

    private RecyclerView recyclerFavoritos;
    private MascotaDatabase database;
    private AdaptadorMascota adapter;
    private final ArrayList<Mascota> mascotas = new ArrayList<>();

    public FragmentFavoritos() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(
                R.layout.fragment_favoritos,
                container,
                false
        );

        recyclerFavoritos =
                vista.findViewById(R.id.recyclerFavoritos);

        recyclerFavoritos.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        adapter = new AdaptadorMascota(mascotas, this);
        recyclerFavoritos.setAdapter(adapter);

        database = MascotaDatabase.getDatabase(requireContext());

        cargarMascotas();

        return vista;
    }

    private void cargarMascotas() {

        MascotaDatabase.databaseWriteExecutor.execute(() -> {

            List<Mascota> guardadas =
                    database.mascotaDao().obtenerUltimasCinco();

            requireActivity().runOnUiThread(() -> {

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

            requireActivity().runOnUiThread(() -> {

                mascotas.clear();
                mascotas.addAll(ultimas);
                adapter.notifyDataSetChanged();

                Toast.makeText(
                        requireContext(),
                        "Rating guardado correctamente",
                        Toast.LENGTH_SHORT
                ).show();
            });
        });
    }
}
