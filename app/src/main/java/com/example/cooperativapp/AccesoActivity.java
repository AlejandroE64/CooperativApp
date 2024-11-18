package com.example.cooperativapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//Declara la clase AccesoActivity que extiende a AppCompatActivity
public class AccesoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
    }
    public void onClickInicio(View view){
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
    public void onClickMaps(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void onClickForm(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }
}
