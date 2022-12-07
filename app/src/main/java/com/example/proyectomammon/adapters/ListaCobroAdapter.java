package com.example.proyectomammon.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomammon.AddEditAbonos;
import com.example.proyectomammon.R;
import com.example.proyectomammon.crudAbonos;
import com.example.proyectomammon.db.DbAbonos;
import com.example.proyectomammon.db.DbCobros;
import com.example.proyectomammon.resources.Abonos;
import com.example.proyectomammon.resources.Cobros;

import java.util.ArrayList;

public class ListaCobroAdapter extends RecyclerView.Adapter<ListaCobroAdapter.CobroViewHolder> {

    private ArrayList<Cobros> cobrosList;
    private Context context;

    public ListaCobroAdapter(ArrayList<Cobros> cobrosList, Context context) {
        this.cobrosList = cobrosList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaCobroAdapter.CobroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cobro_lista_item, parent, false);
        return new CobroViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListaCobroAdapter.CobroViewHolder holder, int position) {
        TextView DGTVnombreCobro, DGTVmonto, DGTVfechaCobro, DGTVfrecuencia, DGTVdescripcion;
        Button DGBtnBorrar_Cobro, DGBtnEditar_Cobro;

        DbCobros dbCobros = new DbCobros(context);

        final Dialog dialog = new Dialog(context);
        //Desactivamos el titulo
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Hacemos que el usuario sea capaz de cerrar el dialogo cuando toque fuera del mismo
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_cobro);
        //inicializamos los items del dialogo
        DGTVnombreCobro = dialog.findViewById(R.id.TVlistaNombreCobro);
        DGTVmonto = dialog.findViewById(R.id.TVlistamonto);
        DGTVfechaCobro = dialog.findViewById(R.id.TVlistaFecha);
        DGTVfrecuencia = dialog.findViewById(R.id.TVlistaFrecuencia);
        DGTVdescripcion = dialog.findViewById(R.id.TVlistaDescripcion);
        //Buttons
        DGBtnBorrar_Cobro = dialog.findViewById(R.id.btnBorrar_Cobro);
        DGBtnEditar_Cobro = dialog.findViewById(R.id.btnEditar_cobro);

        //Recicler view
        holder.TVlistaNombreCobro.setText(cobrosList.get(position).getNombreCobro());
        holder.TVlistaMonto.setText(Integer.toString(cobrosList.get(position).getMonto()));
        holder.TVlistaFecha.setText(cobrosList.get(position).getFechaCobro());
        holder.TVlistaFrecuencia.setText(cobrosList.get(position).getFrecuencia());
        //Dialog
        DGTVnombreCobro.setText(cobrosList.get(position).getNombreCobro());
        DGTVmonto.setText(Integer.toString(cobrosList.get(position).getMonto()));
        DGTVfechaCobro.setText(cobrosList.get(position).getFechaCobro());
        DGTVfrecuencia.setText(cobrosList.get(position).getFrecuencia());
        DGTVdescripcion.setText(cobrosList.get(position).getDescripcion());

        holder.cobro_lista_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        //Le ponemos OnClick al boton de editar del dialogo
        DGBtnEditar_Cobro.setOnClickListener(view -> {
            dialog.dismiss();
            Intent i = new Intent(context, AddEditAbonos.class);
            context.startActivity(i);
        });
        //Le ponemos OnClick al boton de borrar del dialogo
        DGBtnBorrar_Cobro.setOnClickListener(view -> {
            dbCobros.delete(cobrosList.get(position));
            dialog.dismiss();

            Intent i = new Intent(context, crudAbonos.class);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return cobrosList.size();
    }

    public class CobroViewHolder extends RecyclerView.ViewHolder {
        TextView TVlistaNombreCobro, TVlistaMonto, TVlistaFecha, TVlistaFrecuencia;
        LinearLayout cobro_lista_item;

        public CobroViewHolder(@NonNull View itemView) {
            super(itemView);
            TVlistaNombreCobro = itemView.findViewById(R.id.TVlistaNombreCobro);
            TVlistaMonto = itemView.findViewById(R.id.TVlistamonto);
            TVlistaFecha = itemView.findViewById(R.id.TVlistaFecha);
            TVlistaFrecuencia = itemView.findViewById(R.id.TVlistaFrecuencia);
            cobro_lista_item = itemView.findViewById(R.id.cobro_lista_item);
        }
    }
}
