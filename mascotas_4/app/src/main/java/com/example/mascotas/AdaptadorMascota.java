package com.example.mascotas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorMascota
        extends RecyclerView.Adapter<AdaptadorMascota.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;

    public AdaptadorMascota(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mascota, parent, false);

        return new MascotaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MascotaViewHolder holder,
            int position) {

        Mascota mascota = mascotas.get(position);

        holder.txtNombre.setText(mascota.getNombre());

        holder.imgMascota.setImageResource(
                mascota.getFoto()
        );

        ImageView[] huesos = {
                holder.hueso1,
                holder.hueso2,
                holder.hueso3,
                holder.hueso4,
                holder.hueso5
        };

        for (int i = 0; i < 5; i++) {

            // Mostrar el rating actual
            if (i < mascota.getRating()) {

                huesos[i].setImageResource(
                        R.drawable.hueso_amarillo
                );

            } else {

                huesos[i].setImageResource(
                        R.drawable.hueso_blanco
                );
            }

            // Rating que corresponde a este hueso
            final int ratingSeleccionado = i + 1;

            // Hacer el hueso clicable
            huesos[i].setOnClickListener(v -> {

                int posicion =
                        holder.getBindingAdapterPosition();

                if (posicion != RecyclerView.NO_POSITION) {

                    mascotas.get(posicion)
                            .setRating(ratingSeleccionado);

                    notifyItemChanged(posicion);
                }
            });
        }

    } // ← ESTA LLAVE FALTABA

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgMascota;

        TextView txtNombre;

        ImageView hueso1;
        ImageView hueso2;
        ImageView hueso3;
        ImageView hueso4;
        ImageView hueso5;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMascota =
                    itemView.findViewById(R.id.imgMascota);

            txtNombre =
                    itemView.findViewById(R.id.txtNombre);

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