package com.example.cooperativapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaramos Variables
    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazo Variables con ID correspondientes de activity_main
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contraseña);
        spinner = findViewById(R.id.SpinnerRoles);

        // Configurar el Spinner
        String[] roles = {"Administrador", "Usuario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Configuración del botón "botonI"
        findViewById(R.id.botonI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirSonidoYAcceder();
            }
        });
    }

    // Método para reproducir sonido y realizar la validación de acceso
    private void reproducirSonidoYAcceder() {
        // Reproducir sonido
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ingreso);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });

        // Validar credenciales y navegar
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();

        if (user.isEmpty()) {
            Toast.makeText(MainActivity.this, "El campo usuario está vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            Toast.makeText(MainActivity.this, "El campo contraseña está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar credenciales
        if (user.equals("A") && pass.equals("123456") && rol.equals("Usuario")) {
            Intent intent = new Intent(MainActivity.this, AccesoActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Usuario o Contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    // Navegar a la pantalla de registro
    public void onClickRegistro(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}

