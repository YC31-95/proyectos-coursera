package com.example.mascotas;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MascotaDao {

    @Insert
    long insertar(Mascota mascota);

    @Query("SELECT * FROM mascota ORDER BY id DESC LIMIT 5")
    List<Mascota> obtenerUltimasCinco();

    @Query("DELETE FROM mascota WHERE id NOT IN (SELECT id FROM mascota ORDER BY id DESC LIMIT 5)")
    void conservarUltimasCinco();
}
