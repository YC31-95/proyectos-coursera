package com.example.mascotas;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "mascota")
public class Mascota {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private int foto;
    private int rating;

    @Ignore
    public Mascota(String nombre, int foto, int rating) {
        this.nombre = nombre;
        this.foto = foto;
        this.rating = rating;
    }

    public Mascota(int id, String nombre, int foto, int rating) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFoto() {
        return foto;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
