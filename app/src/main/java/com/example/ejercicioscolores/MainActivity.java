package com.example.ejercicioscolores;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int color;
    private int numtoques;
    private long tiempo;
    private ImageButton boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.color = getResources().getColor(R.color.negro);
        this.numtoques = 0;
        this.boton = findViewById(R.id.empezarBTN);
    }


    public void cambiarColor(View v) {
        LinearLayout cajatocada = (LinearLayout) v;

        if (v.getTag() == null && this.boton.getVisibility() == View.INVISIBLE) {
            Log.d("TOCADO", v.getBackground().toString());
            v.setTag(true);
            cajatocada.setBackgroundColor(this.color);
            numtoques++;
         //   if (numtoques == 1) tiempo = System.currentTimeMillis();
            if (numtoques == 6) {
                tiempo = System.currentTimeMillis() - tiempo;
                Double tiempodbl = tiempo / 1000d;
                String tiempotxt = String.format("Has tardado: %1$.3f segundos", tiempodbl);
                Toast.makeText(this,  tiempotxt , Toast.LENGTH_LONG).show();
                salir();
            }
        }


    }


    private void salir() {
        // Cerrar la actividad
        this.finish();
        // Cerrar del todo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }
    }

    public void empezarPartida (View v) {
        Log.d("MIAPP", "Empezar la partida");
        // TODO iniciar el crono, quitar el botón
        tiempo = System.currentTimeMillis();
        boton.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Comienza el juego!!", Toast.LENGTH_SHORT).show();


    }



}
