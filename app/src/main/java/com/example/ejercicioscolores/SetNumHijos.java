package com.example.ejercicioscolores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.NumberPicker;

public class SetNumHijos extends AppCompatActivity {

    private NumberPicker dificultadPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_num_hijos);


        dificultadPicker = findViewById(R.id.numeroTokes);

        // Ocultamos el teclado virtual para ver la pantalla completa
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(dificultadPicker.getWindowToken(), 0);

        dificultadPicker.setMinValue(5);
        dificultadPicker.setMaxValue(30);


        // Recuperamos de Shared Preferences la selección del usuario en el último juego
        int tokes = PreferenciasUsuario.recuperarTokes(this.getApplicationContext());
        if (tokes == -1) {
            dificultadPicker.setValue(15);
        } else {
            dificultadPicker.setValue(tokes);
        }


    }

    public void jugar(View v) {
        // Guardamos la elección del usuario para la siguiente vez que juegue
        PreferenciasUsuario.grabarTokes(this.getApplicationContext(), dificultadPicker.getValue());

        // Lanzamos el juego
        Intent intent = new Intent(this, DuplicarActivity.class);
        intent.putExtra("NTOKES", dificultadPicker.getValue());
        startActivity(intent);
    }


}
