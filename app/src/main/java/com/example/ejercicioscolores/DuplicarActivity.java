package com.example.ejercicioscolores;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DuplicarActivity extends AppCompatActivity {

    private LinearLayout raiz;
    private ImageView pausaTV;
    private Random r;
    private int numeroCuadros;
    private int numeroHijos;
    private int numeroCuadrosParaGanar;
    private long tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duplicar);

        pausaTV = findViewById(R.id.pausaTV);

        Intent intent_creador = getIntent();
        int ntokes = intent_creador.getIntExtra("NTOKES", -1);

        numeroCuadrosParaGanar = ntokes;

        numeroCuadros = 0;

        raiz = findViewById(R.id.originalLL);

        raiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duplicar(v);
            }
        });

        tiempo = System.currentTimeMillis();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.juego1menu:
                Log.d("MIAPP", "Seleccionado menu: " + item.getTitle());
                // Lanzar la version original
                Intent intent0 = new Intent(this, MainActivity.class);
                startActivity(intent0);
                this.finish();

                break;
            case R.id.juego2menu:
                // Lanzar la version Dividir
                Log.d("MIAPP", "Seleccionado menu: " + item.getTitle());
                Intent intent = new Intent(this, DuplicarActivity.class);
                startActivity(intent);
                this.finish();
                break;

            case R.id.juego3menu:
                // Lanzar la version Aleatoria
                Intent intent2 = new Intent(this, AleatorioActivity.class);
                startActivity(intent2);
                this.finish();
                break;
            case R.id.pausaBTN:
                if (pausaTV.getVisibility() == View.INVISIBLE) {
                    pausaTV.setVisibility(View.VISIBLE);
                } else {
                    pausaTV.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.reiniciarBTN:
                raiz.removeAllViews();
                Toast.makeText(this, "Reiniciado juego!!", Toast.LENGTH_LONG).show();
                tiempo = System.currentTimeMillis();
                break;
            default:

                Log.d("MIAPP", "Ni idea de donde ha tocado");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void reanudarPausa(View v) {
        if (pausaTV.getVisibility() == View.INVISIBLE) {
            pausaTV.setVisibility(View.VISIBLE);
        } else {
            pausaTV.setVisibility(View.INVISIBLE);
        }
    }

    public void duplicar(View v) {

        // Hay que ver si queremos dividir nuestro LinearLayout en horizontal o en vertical
        //    Log.d("MIAPP", "DUPLICAR");

        // Recuperamos el id del view que hemos hecho click para usarlo en el findViewById
        int viewId = v.getId();
        // Log.d("MIAPP", "id: " + viewId);

        // Utilizamos el findViewById para que nos recupere todos los parámetros que ya tiene nuestro view (Si no no funciona o eso creo)
        LinearLayout contenedor = findViewById(viewId);

        // Creamos dos nuevos Linear en vertical u horizontal según sea el contenedor (Pa que quede mas potito)
        // Le ponemos un listener dinámico para que al hacer click en el nuevo Linear creado, haga lo mismo hasta el infinitooooo
        if (contenedor.getOrientation() == LinearLayout.HORIZONTAL) {

            contenedor.addView(crearLinear(contenedor.getOrientation()), 0);
            contenedor.addView(crearLinear(contenedor.getOrientation()), 0);
        } else {

            contenedor.addView(crearLinear(contenedor.getOrientation()), 0);
            contenedor.addView(crearLinear(contenedor.getOrientation()), 0);

        }


        // Contar los linear usando la Función de mas abajo (Recursividad)
        numeroHijos = 0;
        mostrarLayout(raiz);


        // Usar ChildCount SOLO CUENTA LOS HIJOS, no los objetos que dependen de ellos
        Log.d("CHILDCOUNT", "num.: " + raiz.getChildCount());

        // A Pelo con un contador estático
        numeroCuadros += 2;
        Log.d("MIAPP", "Numero de cuadros: " + numeroCuadros);

        // Detectar el fin del juego
        if (numeroCuadros >= numeroCuadrosParaGanar) {
            tiempo = System.currentTimeMillis() - tiempo;
            Double tiempodbl = tiempo / 1000d;
            String tiempotxt = String.format("Has tardado: %1$.3f segundos", tiempodbl);
            Toast.makeText(this, tiempotxt, Toast.LENGTH_LONG).show();
            salir();
        }


    }

    private void salir() {
        // Cerrar la actividad

        Intent intent = new Intent(this, SpinnerActivity.class);
        startActivity(intent);

        // this.finish();
        /* Cerrar del todo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }
        */
    }

    private void mostrarLayout(View vista) {

        Log.d("CHILDS", vista.getClass().getCanonicalName());
        if (vista instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) vista;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View vistahija = viewGroup.getChildAt(i);
                mostrarLayout(vistahija);
                numeroHijos++;
            }
        }

        Log.d("CHILDS", "num: " + numeroHijos);
    }

    /**
     * Es el ID calculado
     * Va generando números aleatorios. Comprueba que el valor generado
     * no coincide con un ID existente y si no existe devuelvo el nuevo
     * valor.
     *
     * @return
     */
    private int newId() {
        r = new Random();
        int resultado = -1;
        do {
            resultado = r.nextInt(Integer.MAX_VALUE);
        } while (findViewById(resultado) != null);
        return resultado;
    }

    private LinearLayout crearLinear(int orientacion) {

        // Creamos un linear nuevo con la orientación al contrario del contenedor según pasamos en el parámetro

        int ancho, alto, orientacionHijo;

        if (orientacion == LinearLayout.HORIZONTAL) {
            ancho = 0;
            alto = LinearLayout.LayoutParams.MATCH_PARENT;
            orientacionHijo = LinearLayout.VERTICAL;
        } else {
            ancho = LinearLayout.LayoutParams.MATCH_PARENT;
            alto = 0;
            orientacionHijo = LinearLayout.HORIZONTAL;
        }

        final LinearLayout nuevo1 = new LinearLayout(this);
        nuevo1.setLayoutParams(new LinearLayout.LayoutParams(ancho, alto, 1));
        nuevo1.setOrientation(orientacionHijo);
        nuevo1.setId(newId());
        nuevo1.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

        nuevo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duplicar(nuevo1);
            }
        });


        return nuevo1;

    }


}
