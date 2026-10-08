package com.example.mascotas;

public class Mascota {

    private String nombre;
    private int foto;
    private int rating;

    public Mascota(String nombre, int foto, int rating) {
        this.nombre = nombre;
        this.foto = foto;
        this.rating = rating;
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