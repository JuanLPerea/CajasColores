package com.example.ejercicioscolores;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class PreferenciasUsuario {


    public static double recuperarRecord(Context context, String juego) {
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        Float record = ficherosp.getFloat(juego, -1F);
        return record;
    }

    public static String recuperarRecordTXT(Context context, String juego) {
        String record = null;


        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);

        switch (juego) {
            case Constantes.RECORDCLASICO:
                record = ficherosp.getString(Constantes.RECORDCLASICOTXT, "Juego Clásico ....... 0");
                break;
            case Constantes.RECORDALEATORIO:
                record = ficherosp.getString(Constantes.RECORDDUPLICARTXT, "Juego Duplicar Cajas .. 0");
                break;
            case Constantes.RECORDDUPLICAR:
                record = ficherosp.getString(Constantes.RECORDALEATORIOTXT,  "Juego Aleatorio ....... 0");
                break;
            default:
                record = null;
                break;
        }


        return record;
    }


    public static String recuperarNick(Context context) {
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        String nickName = ficherosp.getString(Constantes.NOMBREJUGADOR, "Jugador");
        return nickName;
    }

    public static void grabarNick(Context context, String nickName) {
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = ficherosp.edit();
        editor.putString(Constantes.NOMBREJUGADOR, nickName);
        editor.commit();
    }


    public static int recuperarTokes(Context context) {
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        int ntokes = ficherosp.getInt(Constantes.NUNTOKES, -1);
        return ntokes;
    }


    public static void grabarTokes(Context context, int tokes) {
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = ficherosp.edit();
        editor.putInt(Constantes.NUNTOKES, tokes);
        editor.commit();
    }


    public static void grabarRecord(Context context, String juego, String jugador, double record) {

        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = ficherosp.edit();


        switch (juego) {
            case Constantes.RECORDCLASICO:
                editor.putString(Constantes.RECORDCLASICOTXT, "CLASICO " + jugador + " " + record);
                editor.putFloat(Constantes.RECORDCLASICO, (float) record);
                break;
            case Constantes.RECORDALEATORIO:
                editor.putString(Constantes.RECORDALEATORIOTXT, "ALEATORIO " + jugador + " " + record);
                editor.putFloat(Constantes.RECORDALEATORIO, (float) record);
                break;
            case Constantes.RECORDDUPLICAR:
                editor.putString(Constantes.RECORDDUPLICARTXT, "DUPLICAR" + jugador + " " + record);
                editor.putFloat(Constantes.RECORDDUPLICAR, (float) record);
                break;
        }


        editor.commit();

    }

    public static void grabarHistorico(Context context, Set<String> historico) {
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = ficherosp.edit();
        editor.putStringSet(Constantes.HISTORICO_RECORDS, historico);
        editor.commit();
    }

    public static Set<String> recuperarHistorico(Context context) {
        Set historico = null;
        SharedPreferences ficherosp = context.getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        historico = ficherosp.getStringSet(Constantes.HISTORICO_RECORDS, null);
        return historico;
    }

    public static void historicoAdd(Context context, String nuevaPartida) {

        // Añadimos un nuevo record al histórico y lo grabamos en las Shared Preferences
        Set<String> setHistorico = recuperarHistorico(context);
        if (setHistorico == null) setHistorico = new HashSet<String>();
        setHistorico.add(nuevaPartida);
        grabarHistorico(context, setHistorico);


    }

}
