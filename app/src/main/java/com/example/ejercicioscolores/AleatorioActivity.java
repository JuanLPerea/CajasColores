package com.example.ejercicioscolores;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.Random;

public class AleatorioActivity extends AppCompatActivity {

    private Random r;
    private int numeroFilas;
    private int numeroColumnas;
    private LinearLayout linearBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aleatorio);


        // Podemos cambiar esto para indicar el total de filas y columnas que queremos
        numeroFilas = 10;
        numeroColumnas = 10;

        layoutAleatorio();

    }


    private void layoutAleatorio(){

        linearBase = findViewById(R.id.originalLL);

        r = new Random();

        // Sacamos un número de filas
        int cajasAlto = r.nextInt(numeroFilas);
        int[] pesofila = new int[cajasAlto + 1];

        // Asignamos pesos aleatorios a la fila
        pesofila = asignarPesos(pesofila);



        // Insertamos las filas en el LinearBase, y en cada una le insertamos columnas aleatoriamente
        for (int cnd = 0 ; cnd < cajasAlto ; cnd++) {

            insertarFilas(linearBase, pesofila[cnd]);

        }

    }


    // Asigna pesos aleatoriamente a un número de elementos repartiendo el 100% entre ellos
    private int[] asignarPesos (int [] pesos) {
        // asignar pesos a las filas de forma aleatoria
        int totalpeso = 100;
        int maxpeso = 30;
        int minpeso = 10;
        int[] salidapesos = new int[pesos.length + 1];
        

        Log.d("MIAPP", "NUMERO FILAS " + pesos.length);
        for (int cnd = 0 ; cnd < pesos.length - 1 ; cnd++) {
            if (totalpeso < maxpeso) {
                salidapesos[cnd] = r.nextInt(totalpeso - minpeso) + minpeso;
            } else {
                salidapesos[cnd] = r.nextInt(maxpeso - minpeso) + minpeso;
            }

            Log.d("MIAPP", "PESO " + salidapesos[cnd]);
            totalpeso -= salidapesos[cnd];
        }
        salidapesos[pesos.length] = totalpeso;
        Log.d("MIAPP", "PESO " + salidapesos[pesos.length]);




        return salidapesos;
    }



    // insertamos filas y en cada una un número de columnas aleatorio
    private void insertarFilas(LinearLayout filas, int peso){


        Log.d("MIAPP", "NUEVA FILA");
        // Creamos una nueva fila
        LinearLayout nuevoLinear = new LinearLayout(this);
        nuevoLinear.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, peso));
        nuevoLinear.setOrientation(LinearLayout.VERTICAL);
        nuevoLinear.setId(newId());
        nuevoLinear.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        nuevoLinear.addView(filas, 0);


        // Insertar las columnas dentro de cada fila
        // Sacamos un número de columnas
        int cajasAncho = r.nextInt(numeroColumnas);
        int[] pesocolumna = new int[cajasAncho];

        // Asignamos pesos aleatorios a la fila
        pesocolumna = asignarPesos(pesocolumna);



        // Insertamos las filas en el LinearBase, y en cada una le insertamos columnas aleatoriamente
        for (int cnd = 0 ; cnd < cajasAncho ; cnd++) {

            insertarColumnas(nuevoLinear, pesocolumna[cnd]);

        }

    }


    private void insertarColumnas(LinearLayout columnas, int peso) {

        Log.d("MIAPP", "NUEVA COLUMNA");

        // Creamos una nueva columna
        LinearLayout nuevoLinear = new LinearLayout(this);
        nuevoLinear.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0, peso));
        nuevoLinear.setOrientation(LinearLayout.HORIZONTAL);
        nuevoLinear.setId(newId());
        nuevoLinear.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        nuevoLinear.addView(columnas, 0);

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

