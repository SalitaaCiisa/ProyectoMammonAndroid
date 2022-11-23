package com.example.proyectomammon.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomammon.R;
import com.example.proyectomammon.resources.Abonos;

import java.util.ArrayList;

public class ListaAbonoAdapter extends RecyclerView.Adapter<ListaAbonoAdapter.AbonoViewHolder> {

    private ArrayList<Abonos> abonosList;

    public ListaAbonoAdapter(ArrayList<Abonos> abonosList) {
        this.abonosList = abonosList;
    }

    @NonNull
    @Override
    public AbonoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.abono_lista_item, parent, false);
        return new AbonoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AbonoViewHolder holder, int position) {
        holder.TVlistaNombreAbono.setText(abonosList.get(position).getNombreAbono());
        holder.TVlistaMonto.setText(Integer.toString(abonosList.get(position).getMonto()));
        holder.TVlistaFecha.setText(abonosList.get(position).getFechaAbono());
        holder.TVlistaFrecuencia.setText(abonosList.get(position).getFrecuencia());
    }

    @Override
    public int getItemCount() {
        return abonosList.size();
    }

    public class AbonoViewHolder extends RecyclerView.ViewHolder {
        TextView TVlistaNombreAbono, TVlistaMonto, TVlistaFecha, TVlistaFrecuencia;

        public AbonoViewHolder(@NonNull View itemView) {
            super(itemView);
            TVlistaNombreAbono = itemView.findViewById(R.id.TVlistaNombreAbono);
            TVlistaMonto = itemView.findViewById(R.id.TVlistamonto);
            TVlistaFecha = itemView.findViewById(R.id.TVlistaFecha);
            TVlistaFrecuencia = itemView.findViewById(R.id.TVlistaFrecuencia);
        }
    }
}
