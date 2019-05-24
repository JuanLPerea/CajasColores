package com.example.ejercicioscolores;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferenciasUsuario {



    public static double recuperarRecord(Context context, String juego) {
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        Float record = ficherosp.getFloat(juego, -1F);
    return record;
    }


    public static int recuperarTokes(Context context) {

        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        int ntokes = ficherosp.getInt(Constantes.NUNTOKES, -1);

        return ntokes;
    }

    public static void grabarRecord(Context context, String juego, String jugador, double record) {

        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = ficherosp.edit();
        editor.putString(juego, jugador + " ....." + record);
        editor.commit();

    }

}
