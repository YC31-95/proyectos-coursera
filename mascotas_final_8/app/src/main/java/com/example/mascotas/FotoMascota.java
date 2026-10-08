package com.example.mascotas;

public class FotoMascota {

    private int foto;
    private int rating;

    public FotoMascota(int foto, int rating) {
        this.foto = foto;
        this.rating = rating;
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