package com.example.ejercicioscolores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    /**
     * 1 Hacer una actividad inicial para que con un SPINNER para que se elija la modalidad que quieres jugar
     * 2 Crear una actividad previa a la modalidad "FER" para seleccionar el número de toques antes de entrar. Usar un NumberPicker
     * 3 Hacer un botón de pausa para que el juego se detenga y en ese momento no se pueda seguir (salga una pantalla botón, o imagen superpuesta o cualquier tipo de aviso) y que permita reanudar la partida cuando el usuario vuelva a darle
     * 4 Definir una opción en el menú para que se pueda reiniciar la partida (y con ello el tiempo)
     * <p>
     * EXTRA: PENSAR SOBRE LA APP DEL REGISTRO DE HORAS DE TRABAJO. Definir un nombre, REQUISITOS logo...
     */


    private static final String[] NOMBRES = {"SELECCIONA UNA OPCION", "JUEGO TRADICIONAL", "DUPLICA CAJAS", "PANTALLA ALEATORIA"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerVersion);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, NOMBRES);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d("MIAPP", "TOCADO " + pos);


        switch (pos) {
            case 1:
                Log.d("MIAPP", "Seleccionado menu: " + pos);
                // Lanzar la version original
                Intent intent0 = new Intent(this, MainActivity.class);
                startActivity(intent0);
                this.finish();

                break;
            case 2:
                // Lanzar la version Dividir
                Log.d("MIAPP", "Seleccionado menu: " + pos);
                Intent intent = new Intent(this, SetNumHijos.class);
                startActivity(intent);
                this.finish();
                break;

            case 3:
                // Lanzar la version Aleatoria
                Intent intent2 = new Intent(this, AleatorioActivity.class);
                startActivity(intent2);
                this.finish();
                break;
            default:

                Log.d("MIAPP", "Seleccionado otro");
                break;
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}

