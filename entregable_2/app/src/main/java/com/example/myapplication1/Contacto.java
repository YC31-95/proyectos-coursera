package com.example.myapplication1;

import java.io.Serializable;

public class Contacto implements Serializable {

    private final String nombre;
    private final long fechaMillis;
    private final String telefono;
    private final String email;
    private final String descripcion;

    public Contacto(String nombre, long fechaMillis, String telefono,
                    String email, String descripcion) {
        this.nombre = nombre;
        this.fechaMillis = fechaMillis;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public long getFechaMillis() {
        return fechaMillis;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDescripcion() {
        return descripcion;
    }
}