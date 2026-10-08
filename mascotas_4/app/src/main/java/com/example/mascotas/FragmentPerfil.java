package com.example.mascotas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentPerfil extends Fragment {

    private RecyclerView recyclerFotos;

    public FragmentPerfil() {
        // Constructor vacío
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(
                R.layout.fragment_perfil,
                container,
                false
        );

        // RecyclerView
        recyclerFotos =
                vista.findViewById(R.id.recyclerFotos);

        // Grid de 3 columnas
        GridLayoutManager layoutManager =
                new GridLayoutManager(
                        requireContext(),
                        3
                );

        recyclerFotos.setLayoutManager(layoutManager);

        // Datos dummy
        ArrayList<FotoMascota> fotos =
                new ArrayList<>();

        fotos.add(
                new FotoMascota(
                        R.drawable.mascota1,
                        5
                )
        );

        fotos.add(
                new FotoMascota(
                        R.drawable.mascota2,
                        4
                )
        );

        fotos.add(
                new FotoMascota(
                        R.drawable.mascota3,
                        3
                )
        );

        fotos.add(
                new FotoMascota(
                        R.drawable.mascota4,
                        5
                )
        );

        fotos.add(
                new FotoMascota(
                        R.drawable.mascota1,
                        4
                )
        );

        fotos.add(
                new FotoMascota(
                        R.drawable.mascota2,
                        2
                )
        );

        // Adaptador
        AdaptadorFotoMascota adapter =
                new AdaptadorFotoMascota(fotos);

        recyclerFotos.setAdapter(adapter);

        return vista;
    }
}