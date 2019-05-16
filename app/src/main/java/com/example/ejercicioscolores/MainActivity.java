package com.example.ejercicioscolores;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int color;
    private int numtoques;
    private long tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.color = getResources().getColor(R.color.negro);
        this.numtoques = 0;

        tiempo = System.currentTimeMillis();

    }


    public void cambiarColor(View v) {
        LinearLayout cajatocada = (LinearLayout) v;

        if (v.getTag() == null) {
            Log.d("TOCADO", v.getBackground().toString());
            v.setTag(true);
            cajatocada.setBackgroundColor(this.color);
            numtoques++;
            if (numtoques == 6) {
                tiempo = System.currentTimeMillis() - tiempo;
                Toast.makeText(this, "Has tardado " + (tiempo /1000) + " segundos.", Toast.LENGTH_LONG).show();
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

}
