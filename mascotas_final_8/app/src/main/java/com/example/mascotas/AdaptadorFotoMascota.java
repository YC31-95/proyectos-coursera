package com.example.mascotas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorFotoMascota
        extends RecyclerView.Adapter<AdaptadorFotoMascota.FotoViewHolder> {

    private ArrayList<FotoMascota> fotos;

    public AdaptadorFotoMascota(ArrayList<FotoMascota> fotos) {
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_foto_mascota, parent, false);

        return new FotoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(
            @NonNull FotoViewHolder holder,
            int position) {

        FotoMascota foto = fotos.get(position);

        // Mostrar fotografía
        holder.imgFotoMascota.setImageResource(
                foto.getFoto()
        );

        // Huesos
        ImageView[] huesos = {
                holder.hueso1,
                holder.hueso2,
                holder.hueso3,
                holder.hueso4,
                holder.hueso5
        };

        for (int i = 0; i < 5; i++) {

            if (i < foto.getRating()) {

                huesos[i].setImageResource(
                        R.drawable.hueso_amarillo
                );

            } else {

                huesos[i].setImageResource(
                        R.drawable.hueso_blanco
                );
            }

            final int ratingSeleccionado = i + 1;

            huesos[i].setOnClickListener(v -> {

                int posicion =
                        holder.getBindingAdapterPosition();

                if (posicion != RecyclerView.NO_POSITION) {

                    fotos.get(posicion)
                            .setRating(ratingSeleccionado);

                    notifyItemChanged(posicion);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class FotoViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgFotoMascota;

        ImageView hueso1;
        ImageView hueso2;
        ImageView hueso3;
        ImageView hueso4;
        ImageView hueso5;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFotoMascota =
                    itemView.findViewById(R.id.imgFotoMascota);

            hueso1 =
                    itemView.findViewById(R.id.hueso1);

            hueso2 =
                    itemView.findViewById(R.id.hueso2);

            hueso3 =
                    itemView.findViewById(R.id.hueso3);

            hueso4 =
                    itemView.findViewById(R.id.hueso4);

            hueso5 =
                    itemView.findViewById(R.id.hueso5);
        }
    }
}