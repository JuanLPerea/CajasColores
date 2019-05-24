package com.example.ejercicioscolores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdapterHistorico extends RecyclerView.Adapter<AdapterHistorico.AdapterViewHolder> {

    List<String> historico;

    public AdapterHistorico(Set<String> historico) {
        this.historico = new ArrayList(historico);
    }


    public AdapterHistorico() {
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_layout,viewGroup,false);
        AdapterViewHolder viewHolder=new AdapterViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        String[] rowTXT = historico.get(position).split("#");


        String juego = rowTXT[0];
        String jugador = rowTXT[1];
        String level = rowTXT[2];
        String score = rowTXT[3];
        String record = "";
        if (rowTXT.length>4) {
            record = rowTXT[4];
        }


        holder.juego.setText(juego);
        holder.jugador.setText(jugador);
        holder.level.setText(level);
        holder.score.setText(score);
        holder.record.setText(record);
    }

    @Override
    public int getItemCount() {
        return historico.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        protected TextView juego, jugador, level, score, record;


        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            juego = itemView.findViewById(R.id.juego_recyclerTV);
            jugador = itemView.findViewById(R.id.jugador_recyclerTV);
            level = itemView.findViewById(R.id.level_recyclerTV);
            score = itemView.findViewById(R.id.score_recyclerTV);
            record = itemView.findViewById(R.id.record_recyclerTV);

        }
    }
}
