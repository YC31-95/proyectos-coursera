package com.example.mascotas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    private TextInputEditText etNombre;
    private TextInputEditText etCorreo;
    private TextInputEditText etMensaje;
    private Button btnEnviar;

    // Correo que recibirá los comentarios
    private static final String CORREO_DESTINO =
            "gomezpv19@gmail.com";

    // Correo de Gmail que enviará los comentarios
    private static final String CORREO_EMISOR =
            "gomezpv19@gmail.com";

    /*
     * IMPORTANTE:
     * Aquí debes colocar la CONTRASEÑA DE APLICACIÓN
     * generada por Google.
     *
     * NO uses la contraseña normal de Gmail.
     */
    private static final String CONTRASENA_APP =
            "ylvmerunnbkrehms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacto);

        // Referencias a los campos del formulario
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        // Botón enviar
        btnEnviar.setOnClickListener(v -> enviarCorreo());
    }

    private void enviarCorreo() {

        // Obtener información escrita por el usuario
        String nombre =
                etNombre.getText().toString().trim();

        String correo =
                etCorreo.getText().toString().trim();

        String mensaje =
                etMensaje.getText().toString().trim();

        // Validar nombre
        if (nombre.isEmpty()) {

            etNombre.setError(
                    "Escribe tu nombre"
            );

            etNombre.requestFocus();

            return;
        }

        // Validar correo
        if (correo.isEmpty()) {

            etCorreo.setError(
                    "Escribe tu correo"
            );

            etCorreo.requestFocus();

            return;
        }

        // Validar mensaje
        if (mensaje.isEmpty()) {

            etMensaje.setError(
                    "Escribe un mensaje"
            );

            etMensaje.requestFocus();

            return;
        }

        // Desactivar botón mientras se envía
        btnEnviar.setEnabled(false);

        // Enviar correo en segundo plano
        new Thread(() -> {

            try {

                // Configuración del servidor SMTP de Gmail
                Properties props = new Properties();

                props.put(
                        "mail.smtp.auth",
                        "true"
                );

                props.put(
                        "mail.smtp.starttls.enable",
                        "true"
                );

                props.put(
                        "mail.smtp.host",
                        "smtp.gmail.com"
                );

                props.put(
                        "mail.smtp.port",
                        "587"
                );

                // Crear sesión de correo
                Session session =
                        Session.getInstance(
                                props,
                                new Authenticator() {

                                    @Override
                                    protected PasswordAuthentication
                                    getPasswordAuthentication() {

                                        return new PasswordAuthentication(
                                                CORREO_EMISOR,
                                                CONTRASENA_APP
                                        );
                                    }
                                }
                        );

                // Crear mensaje
                Message email =
                        new MimeMessage(session);

                // Remitente
                email.setFrom(
                        new InternetAddress(
                                CORREO_EMISOR
                        )
                );

                // Destinatario
                email.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(
                                CORREO_DESTINO
                        )
                );

                // Asunto
                email.setSubject(
                        "Comentario desde Mascotas"
                );

                // Contenido
                String contenido =
                        "Nuevo comentario recibido\n\n"
                                + "Nombre: "
                                + nombre
                                + "\n"
                                + "Correo: "
                                + correo
                                + "\n\n"
                                + "Mensaje:\n"
                                + mensaje;

                email.setText(contenido);

                // Enviar
                Transport.send(email);

                // Regresar al hilo principal
                new Handler(
                        Looper.getMainLooper()
                ).post(() -> {

                    Toast.makeText(
                            ContactoActivity.this,
                            "Comentario enviado correctamente",
                            Toast.LENGTH_LONG
                    ).show();

                    // Limpiar formulario
                    etNombre.setText("");
                    etCorreo.setText("");
                    etMensaje.setText("");

                    // Activar botón nuevamente
                    btnEnviar.setEnabled(true);
                });

            } catch (Exception e) {

                // Mostrar error
                new Handler(
                        Looper.getMainLooper()
                ).post(() -> {

                    Toast.makeText(
                            ContactoActivity.this,
                            "Error al enviar: "
                                    + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();

                    btnEnviar.setEnabled(true);
                });
            }

        }).start();
    }
}