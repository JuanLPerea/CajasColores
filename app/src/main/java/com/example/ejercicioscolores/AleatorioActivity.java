package com.example.ejercicioscolores;

import android.content.Intent;
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

public class AleatorioActivity extends AppCompatActivity {

    private Random r;
    private int numeroFilas;
    private int numeroColumnas;
    private static int totalCuadros;
    private LinearLayout linearBase;
    private long tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aleatorio);

        // Podemos cambiar esto para indicar el total de filas y columnas que queremos
        numeroFilas = 7;
        numeroColumnas = 7;
        totalCuadros = 0;

        tiempo = System.currentTimeMillis();

        layoutAleatorio();


        Log.d("TOCADO", "Total de cuadros"  + totalCuadros);

    }


    private void layoutAleatorio() {

        linearBase = findViewById(R.id.originalLL);

        r = new Random();

        // Sacamos un número de filas aleatorio
        int cajasAlto = r.nextInt(numeroFilas - 3) + 3;    // Mínimo 3 filas
        Log.d("TOCADO", "Total de filas: "  + cajasAlto);
        int[] pesofila = new int[cajasAlto + 1];

        // Asignamos pesos aleatorios a la fila
        pesofila = asignarPesos(pesofila);


        // Insertamos las filas en el LinearBase, y en cada una le insertamos columnas aleatoriamente (tooodo muuuy aleatorio)
        for (int cnd = 0; cnd < cajasAlto; cnd++) {
            insertarFilas(linearBase, pesofila[cnd]);
        }

        totalCuadros -= cajasAlto;
      //  contarCuadros(linearBase);                     // Para calcular el total de cuadros y luego detectar el fin del juego
        Log.d("CHILDS", "Total cuadros" + totalCuadros);
    }


    // insertamos filas y en cada una un número de columnas aleatorio
    private void insertarFilas(LinearLayout filas, int peso) {


        Log.d("MIAPP", "NUEVA FILA");
        // Creamos una nueva fila
        LinearLayout nuevoLinear = new LinearLayout(this);
        nuevoLinear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, peso));
        nuevoLinear.setOrientation(LinearLayout.HORIZONTAL);
        nuevoLinear.setId(newId());
        nuevoLinear.setBackgroundColor(Color.rgb((int) (Math.random() * 205) + 50, (int) (Math.random() * 205) + 50, (int) (Math.random() * 205) + 50));
        filas.addView(nuevoLinear, 0);


        // Insertar las columnas dentro de cada fila
        // Sacamos un número de columnas al azar
        int cajasAncho = r.nextInt(numeroColumnas - 3) + 3;    // Mínimo 3 columnas dentro de cada fila

        Log.d("TOCADO", "Total de columnas: "  + cajasAncho);

        int[] pesocolumna = new int[cajasAncho];
        // Asignamos pesos aleatorios a la fila
        pesocolumna = asignarPesos(pesocolumna);

        // Insertamos las columnas en cada fila
        for (int cnd = 0; cnd < cajasAncho; cnd++) {

            insertarColumnas(nuevoLinear, pesocolumna[cnd]);

        }


    }

    private void cuadroTocado(View v) {

        LinearLayout cajatocada = (LinearLayout) v;

        Log.d("TOCADO", "Cuadros: " + totalCuadros);

        if (v.getTag() == null ) {

            v.setTag(true);
            cajatocada.setBackgroundColor(Color.BLACK);
            totalCuadros--;
            //   if (numtoques == 1) tiempo = System.currentTimeMillis();
            if (totalCuadros <= 0) {
                tiempo = System.currentTimeMillis() - tiempo;
                Double tiempodbl = tiempo / 1000d;
                String tiempotxt = String.format("Has tardado: %1$.3f segundos", tiempodbl);
                Toast.makeText(this, tiempotxt, Toast.LENGTH_LONG).show();
                salir();
            }
        }
    }


    private void insertarColumnas(LinearLayout columnas, int peso) {

        Log.d("MIAPP", "NUEVA COLUMNA");

        // Creamos una nueva columna
        LinearLayout nuevoLinear = new LinearLayout(this);
        nuevoLinear.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, peso));
        nuevoLinear.setOrientation(LinearLayout.HORIZONTAL);
        nuevoLinear.setId(newId());
        nuevoLinear.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        nuevoLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuadroTocado(v);
            }
        });
        columnas.addView(nuevoLinear, 0);
        totalCuadros++;


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


    // Asigna pesos aleatoriamente a un número de elementos repartiendo el 100% entre ellos
    private int[] asignarPesos(int[] pesos) {
        // asignar pesos a las filas de forma aleatoria
        int totalpeso = 100;
        int[] salidapesos = new int[pesos.length + 1];


        Log.d("MIAPP", "NUMERO FILAS " + pesos.length);

        for (int cnd = 0; cnd < pesos.length - 1; cnd++) {
            int maxpeso = totalpeso / pesos.length;
            int minpeso = maxpeso/5;
            if (maxpeso<minpeso) maxpeso=minpeso;
            int pesoAleatorio = r.nextInt(maxpeso - minpeso) + minpeso;
            salidapesos[cnd] = pesoAleatorio;
            totalpeso-=salidapesos[cnd];
        }
        salidapesos[pesos.length] = totalpeso;

        return salidapesos;
    }

    private void salir() {
        // Cerrar la actividad
        Intent intent = new Intent(this, SpinnerActivity.class);
        startActivity(intent);

        /* Cerrar del todo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }
        */
    }


    private void contarCuadros(View vista)
    {
        Log.d("CHILDS", vista.getClass().getCanonicalName());
        if (vista instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup) vista;
            for (int i = 0; i<viewGroup.getChildCount(); i++)
            {
                View vistahija = viewGroup.getChildAt(i);
                contarCuadros(vistahija);
            }
        }

        Log.d("CHILDS", "num: " + totalCuadros);
    }

}

