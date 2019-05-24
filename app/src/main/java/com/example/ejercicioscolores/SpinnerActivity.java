package com.example.ejercicioscolores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    private TextView recordsTV;
    private EditText nickName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Spinner spinner = findViewById(R.id.spinnerVersion);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, NOMBRES);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);

        // Miramos si hay grabado Nickname y lo recuperamos para mostrarlo en pantalla
        nickName = findViewById(R.id.nickNameET);
        nickName.setText(PreferenciasUsuario.recuperarNick(this.getApplicationContext()));

        // Mostramos los records de cada juego en pantalla
        recordsTV = findViewById(R.id.recordsET);
        String records = PreferenciasUsuario.recuperarRecordTXT(this.getApplicationContext(), Constantes.RECORDCLASICO);
        records += "\n";
        records += PreferenciasUsuario.recuperarRecordTXT(this.getApplicationContext(), Constantes.RECORDALEATORIO);
        records += "\n";
        records += PreferenciasUsuario.recuperarRecordTXT(this.getApplicationContext(), Constantes.RECORDDUPLICAR);

        recordsTV.setText(records);

    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d("MIAPP", "TOCADO " + pos);


        if (nickName.getText() == null) {
            nickName.setText("Jugador");
        }

        // Grabamos el NickName en Shared Preferences
        PreferenciasUsuario.grabarNick(this.getApplicationContext(), nickName.getText().toString());


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


    @Override
    protected void onStop() {
        super.onStop();
        //TODO GUARDAR LOS RECORDS



        // records = ficherosp.getString("RECORDS" , "Record    -----   0\nRecord    -----   1\nRecord    -----   2\n");


    }


    public void VerHistorico(View view) {
        // Lanzar la version Aleatoria
        Intent intentHistorico = new Intent(this, HistoricoActivity.class);
        startActivity(intentHistorico);
    }
}

