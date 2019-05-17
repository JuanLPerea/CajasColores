package com.example.ejercicioscolores;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class DuplicarActivity extends AppCompatActivity {

    private LinearLayout raiz;
    private Random r;
    private int numeroCuadros;
    private int numeroHijos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duplicar);

        numeroCuadros = 0;

        raiz = findViewById(R.id.originalLL);

        raiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duplicar(v);
            }
        });



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
            final LinearLayout nuevo1 = new LinearLayout(this);
            nuevo1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            nuevo1.setOrientation(LinearLayout.VERTICAL);
            nuevo1.setId(newId());
            nuevo1.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

            nuevo1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    duplicar(nuevo1);
                }
            });

            contenedor.addView(nuevo1, 0);

            final LinearLayout nuevo2 = new LinearLayout(this);
            nuevo2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            nuevo2.setOrientation(LinearLayout.VERTICAL);
            nuevo2.setId(newId());
            nuevo2.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

            nuevo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    duplicar(nuevo2);
                }
            });
            contenedor.addView(nuevo2, 0);
        } else {
            final LinearLayout nuevo1 = new LinearLayout(this);
            nuevo1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
            nuevo1.setOrientation(LinearLayout.HORIZONTAL);
            nuevo1.setId(newId());
            nuevo1.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

            nuevo1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    duplicar(nuevo1);
                }
            });

            contenedor.addView(nuevo1, 0);

            final LinearLayout nuevo2 = new LinearLayout(this);
            nuevo2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
            nuevo2.setOrientation(LinearLayout.HORIZONTAL);
            nuevo2.setId(newId());
            nuevo2.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

            nuevo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    duplicar(nuevo2);
                }
            });
            contenedor.addView(nuevo2, 0);
        }


        // Contar los linear usando la Función de mas abajo (Recursividad)
        numeroHijos= 0;
        mostrarLayout(raiz);


        // Usar ChildCount SOLO CUENTA LOS HIJOS, no los objetos que dependen de ellos
        Log.d("CHILDCOUNT" , "num.: " + raiz.getChildCount());

        // A Pelo con un contador estático
        numeroCuadros += 2;
        Log.d("MIAPP", "Numero de cuadros: " + numeroCuadros);

        if (numeroCuadros >= 50) {
            Toast.makeText(this, "Hay 50 cuadros o mas", Toast.LENGTH_LONG).show();
            salir();
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

    private void mostrarLayout(View vista)
    {

        Log.d("CHILDS", vista.getClass().getCanonicalName());
        if (vista instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup) vista;
            for (int i = 0; i<viewGroup.getChildCount(); i++)
            {
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
        } while( findViewById( resultado) != null);
        return resultado;
    }


}
