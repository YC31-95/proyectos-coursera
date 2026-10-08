package com.example.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    private Button btnContacto;
    private Button btnAcerca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ViewPager
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter =
                new ViewPagerAdapter(this);

        viewPager.setAdapter(adapter);

        // Botón Contacto
        btnContacto = findViewById(R.id.btnContacto);

        btnContacto.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ContactoActivity.class
            );

            startActivity(intent);
        });

        // Botón Acerca de
        btnAcerca = findViewById(R.id.btnAcerca);

        btnAcerca.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    AcercaDeActivity.class
            );

            startActivity(intent);
        });
    }
}