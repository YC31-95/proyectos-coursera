package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionActivity extends AppCompatActivity {

    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);
        setTitle(R.string.titulo_confirmacion);

        contacto = (Contacto) getIntent().getSerializableExtra(MainActivity.EXTRA_CONTACTO);
        if (contacto == null) {
            finish();
            return;
        }

        ((TextView) findViewById(R.id.tvNombre)).setText(contacto.getNombre());
        ((TextView) findViewById(R.id.tvFecha)).setText(MainActivity.formatearFecha(contacto.getFechaMillis()));
        ((TextView) findViewById(R.id.tvTelefono)).setText(contacto.getTelefono());
        ((TextView) findViewById(R.id.tvEmail)).setText(contacto.getEmail());
        ((TextView) findViewById(R.id.tvDescripcion)).setText(contacto.getDescripcion());

        findViewById(R.id.btnEditar).setOnClickListener(v -> {
            Intent resultado = new Intent();
            resultado.putExtra(MainActivity.EXTRA_CONTACTO, contacto);
            setResult(RESULT_OK, resultado);
            finish();
        });
    }
}