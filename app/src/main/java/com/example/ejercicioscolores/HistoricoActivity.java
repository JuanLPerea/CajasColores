package com.example.ejercicioscolores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HistoricoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Set<String> planetList=new HashSet<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);



        recyclerView = findViewById(R.id.recyclerHistorico);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new AdapterHistorico(PreferenciasUsuario.recuperarHistorico(this.getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }
}
