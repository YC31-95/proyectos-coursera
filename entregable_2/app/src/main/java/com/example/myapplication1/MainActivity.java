package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACTO = "extra_contacto";
    private static final String STATE_FECHA = "state_fecha";

    private TextInputLayout tilNombre, tilFecha, tilTelefono, tilEmail, tilDescripcion;
    private TextInputEditText etNombre, etFecha, etTelefono, etEmail, etDescripcion;

    private Long fechaMillis = null;

    // Recibe los datos de vuelta cuando se pulsa "Editar datos"
    private final ActivityResultLauncher<Intent> confirmacionLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getData() != null) {
                    Contacto c = (Contacto) result.getData().getSerializableExtra(EXTRA_CONTACTO);
                    if (c != null) precargarDatos(c);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        tilNombre = findViewById(R.id.tilNombre);
        tilFecha = findViewById(R.id.tilFecha);
        tilTelefono = findViewById(R.id.tilTelefono);
        tilEmail = findViewById(R.id.tilEmail);
        tilDescripcion = findViewById(R.id.tilDescripcion);

        etNombre = findViewById(R.id.etNombre);
        etFecha = findViewById(R.id.etFecha);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDescripcion = findViewById(R.id.etDescripcion);

        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_FECHA)) {
            fechaMillis = savedInstanceState.getLong(STATE_FECHA);
            etFecha.setText(formatearFecha(fechaMillis));
        }

        View.OnClickListener abrirPicker = v -> mostrarDatePicker();
        etFecha.setOnClickListener(abrirPicker);
        tilFecha.setEndIconOnClickListener(abrirPicker);

        findViewById(R.id.btnSiguiente).setOnClickListener(v -> onSiguiente());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fechaMillis != null) outState.putLong(STATE_FECHA, fechaMillis);
    }

    // ------------------------------------------------------------ DatePicker

    private void mostrarDatePicker() {
        CalendarConstraints restricciones = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now())
                .build();

        MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.elige_fecha)
                .setSelection(fechaMillis != null ? fechaMillis : MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(restricciones)
                .build();

        picker.addOnPositiveButtonClickListener(seleccion -> {
            fechaMillis = seleccion;
            etFecha.setText(formatearFecha(seleccion));
            tilFecha.setError(null);
        });
        picker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    static String formatearFecha(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date(millis));
    }

    // ------------------------------------------------------------ Navegación

    private void onSiguiente() {
        if (!validar()) return;

        Contacto contacto = new Contacto(
                textoDe(etNombre),
                fechaMillis,
                textoDe(etTelefono),
                textoDe(etEmail),
                textoDe(etDescripcion));

        Intent intent = new Intent(this, ConfirmacionActivity.class);
        intent.putExtra(EXTRA_CONTACTO, contacto);
        confirmacionLauncher.launch(intent);
    }

    private void precargarDatos(Contacto c) {
        etNombre.setText(c.getNombre());
        fechaMillis = c.getFechaMillis();
        etFecha.setText(formatearFecha(c.getFechaMillis()));
        etTelefono.setText(c.getTelefono());
        etEmail.setText(c.getEmail());
        etDescripcion.setText(c.getDescripcion());
        etNombre.requestFocus();
        etNombre.setSelection(etNombre.length());
    }

    // ------------------------------------------------------------ Validación

    private boolean validar() {
        boolean ok = true;

        if (textoDe(etNombre).split("\\s+").length < 2) {
            tilNombre.setError(getString(R.string.error_nombre));
            ok = false;
        } else tilNombre.setError(null);

        if (fechaMillis == null) {
            tilFecha.setError(getString(R.string.error_fecha));
            ok = false;
        } else tilFecha.setError(null);

        String tel = textoDe(etTelefono).replaceAll("[\\s\\-()+]", "");
        if (!tel.matches("\\d{7,15}")) {
            tilTelefono.setError(getString(R.string.error_telefono));
            ok = false;
        } else tilTelefono.setError(null);

        String email = textoDe(etEmail);
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError(getString(R.string.error_email));
            ok = false;
        } else tilEmail.setError(null);

        if (textoDe(etDescripcion).isEmpty()) {
            tilDescripcion.setError(getString(R.string.error_descripcion));
            ok = false;
        } else tilDescripcion.setError(null);

        return ok;
    }

    private static String textoDe(TextInputEditText et) {
        return et.getText() == null ? "" : et.getText().toString().trim();
    }
}