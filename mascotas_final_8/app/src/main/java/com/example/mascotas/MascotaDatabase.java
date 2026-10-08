package com.example.mascotas;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Mascota.class}, version = 1, exportSchema = false)
public abstract class MascotaDatabase extends RoomDatabase {

    public abstract MascotaDao mascotaDao();

    private static volatile MascotaDatabase INSTANCE;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newSingleThreadExecutor();

    public static MascotaDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (MascotaDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MascotaDatabase.class,
                            "mascotas_database"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}
