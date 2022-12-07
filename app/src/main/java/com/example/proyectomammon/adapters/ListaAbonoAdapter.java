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
import com.example.proyectomammon.crudCuentasBancarias;
import com.example.proyectomammon.db.DbAbonos;
import com.example.proyectomammon.resources.Abonos;

import java.util.ArrayList;

public class ListaAbonoAdapter extends RecyclerView.Adapter<ListaAbonoAdapter.AbonoViewHolder> {

    private ArrayList<Abonos> abonosList;
    private Context context;

    public ListaAbonoAdapter(ArrayList<Abonos> abonosList, Context context) {
        this.abonosList = abonosList;
        this.context = context;
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

        TextView DGTVnombreAbono, DGTVmonto, DGTVfechaAbono, DGTVfrecuencia, DGTVdescripcion;
        Button DGBtnBorrar_Abono, DGBtnEditar_Abono;

        DbAbonos dbAbonos = new DbAbonos(context);

        final Dialog dialog = new Dialog(context);
        //Desactivamos el titulo
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Hacemos que el usuario sea capaz de cerrar el dialogo cuando toque fuera del mismo
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_abono);
        //inicializamos los items del dialogo
        DGTVnombreAbono = dialog.findViewById(R.id.TVlistaNombreAbono);
        DGTVmonto = dialog.findViewById(R.id.TVlistamonto);
        DGTVfechaAbono = dialog.findViewById(R.id.TVlistaFecha);
        DGTVfrecuencia = dialog.findViewById(R.id.TVlistaFrecuencia);
        DGTVdescripcion = dialog.findViewById(R.id.TVlistaDescripcion);
            //Buttons
        DGBtnBorrar_Abono = dialog.findViewById(R.id.btnBorrar_cuenta);
        DGBtnEditar_Abono = dialog.findViewById(R.id.btnEditar_cuenta);

        //Recicler view
        holder.TVlistaNombreAbono.setText(abonosList.get(position).getNombreAbono());
        holder.TVlistaMonto.setText(Integer.toString(abonosList.get(position).getMonto()));
        holder.TVlistaFecha.setText(abonosList.get(position).getFechaAbono());
        holder.TVlistaFrecuencia.setText(abonosList.get(position).getFrecuencia());
        //Dialog
        DGTVnombreAbono.setText(abonosList.get(position).getNombreAbono());
        DGTVmonto.setText(Integer.toString(abonosList.get(position).getMonto()));
        DGTVfechaAbono.setText(abonosList.get(position).getFechaAbono());
        DGTVfrecuencia.setText(abonosList.get(position).getFrecuencia());
        DGTVdescripcion.setText(abonosList.get(position).getDescripcion());

        holder.abono_lista_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        //Le ponemos OnClick al boton de editar del dialogo
        DGBtnEditar_Abono.setOnClickListener(view -> {
            dialog.dismiss();
            Intent i = new Intent(context, AddEditAbonos.class);
            context.startActivity(i);
        });
        //Le ponemos OnClick al boton de borrar del dialogo
        DGBtnBorrar_Abono.setOnClickListener(view -> {
            dbAbonos.delete(abonosList.get(position));
            dialog.dismiss();

            Intent i = new Intent(context, crudAbonos.class);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return abonosList.size();
    }

    public class AbonoViewHolder extends RecyclerView.ViewHolder {
        TextView TVlistaNombreAbono, TVlistaMonto, TVlistaFecha, TVlistaFrecuencia;
        LinearLayout abono_lista_item;

        public AbonoViewHolder(@NonNull View itemView) {
            super(itemView);
            TVlistaNombreAbono = itemView.findViewById(R.id.TVlistaNombreAbono);
            TVlistaMonto = itemView.findViewById(R.id.TVlistamonto);
            TVlistaFecha = itemView.findViewById(R.id.TVlistaFecha);
            TVlistaFrecuencia = itemView.findViewById(R.id.TVlistaFrecuencia);
            abono_lista_item = itemView.findViewById(R.id.abono_lista_item);
        }
    }
}
