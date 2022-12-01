package com.example.proyectomammon.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomammon.R;
import com.example.proyectomammon.resources.Cuentas;
import com.example.proyectomammon.resources.cuenta_api.CuentasResourceApus;

import java.util.ArrayList;

public class ListaCuentaAdapter extends RecyclerView.Adapter<ListaCuentaAdapter.CuentaViewHolder> {

    private ArrayList<Cuentas> cuentasList;
    private ArrayList<CuentasResourceApus> cuentasApisList;

    public ListaCuentaAdapter(ArrayList<Cuentas> cuentasList, ArrayList<CuentasResourceApus> cuentasApisList) {
        this.cuentasList = cuentasList;
        this.cuentasApisList = cuentasApisList;
    }

    @NonNull
    @Override
    public CuentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuenta_lista_item, parent, false);
        return new CuentaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CuentaViewHolder holder, int position) {
        try {
        //DB
        holder.TVnombreCuenta.setText(cuentasList.get(position).getNombreCuenta());
        holder.TVlink_token.setText(cuentasList.get(position).getLink_token());
        holder.TVapi_key.setText(cuentasList.get(position).getApi_key());
        //Api
        holder.TVnumeroCuenta.setText(cuentasApisList.get(position).getNumber());
        holder.TVmovimiento.setText(cuentasApisList.get(position).getRefreshedAt());
        holder.TVid_cuenta.setText(cuentasApisList.get(position).getId());

        }catch (Exception ex){
            Log.e("Error in 444.all holder: "+position, ex.toString());
        }

        try {
            //Api balance
            holder.TVbalance.setText(Integer.toString(cuentasApisList.get(position).getBalance().getAvailable()));
        }catch (Exception ex){
            Log.e("Error in 444.balance holder: "+position, ex.toString());
        }
    }

    @Override
    public int getItemCount() {
        return cuentasList.size();
    }

    public class CuentaViewHolder extends RecyclerView.ViewHolder {
        TextView TVnombreCuenta, TVnumeroCuenta, TVbalance, TVmovimiento, TVlink_token, TVapi_key, TVid_cuenta;

        public CuentaViewHolder(@NonNull View itemView) {
            super(itemView);
            TVnombreCuenta = itemView.findViewById(R.id.TVlistaNombreCuenta);
            TVnumeroCuenta = itemView.findViewById(R.id.TVlistaNumeroCuenta);
            TVbalance = itemView.findViewById(R.id.TVlistaBalance);
            TVmovimiento = itemView.findViewById(R.id.TVlistaMovimiento);
            TVlink_token = itemView.findViewById(R.id.TVlistaLink_token);
            TVapi_key = itemView.findViewById(R.id.TVlistaApi_key);
            TVid_cuenta = itemView.findViewById(R.id.TVlistaId_cuenta);
        }
    }
}
