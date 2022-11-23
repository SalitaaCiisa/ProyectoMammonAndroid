package com.example.proyectomammon.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomammon.R;
import com.example.proyectomammon.interfaces.CuentasAPI;
import com.example.proyectomammon.resources.Cuentas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaCuentaAdapter extends RecyclerView.Adapter<ListaCuentaAdapter.CuentaViewHolder> {

    private ArrayList<Cuentas> cuentasList;

    public ListaCuentaAdapter(ArrayList<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
    }

    @NonNull
    @Override
    public CuentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuenta_lista_item, parent, false);
        return new CuentaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuentaViewHolder holder, int position) {

        final String[] respuesta1 = new String[1];
        final String[] respuesta2 = new String[1];
        final String[] respuesta3 = new String[1];
        respuesta1[0] = "Vacio";
        respuesta2[0] = "Vacio";
        respuesta3[0] = "Vacio";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fintoc.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CuentasAPI cuentasAPI = retrofit.create(CuentasAPI.class);
        Call<String> call = cuentasAPI.find(cuentasList.get(position).getLink_token(),cuentasList.get(position).getApi_key());
        call.enqueue(new Callback<String>(){
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.isSuccessful()) {
                        respuesta1[0] = response.body();
                        respuesta2[0] = response.body();
                        respuesta3[0] = response.body();
                    }
                }catch (Exception ex){
                    respuesta1[0] = ex.toString();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



        holder.TVnombreCuenta.setText(cuentasList.get(position).getNombreCuenta());
        holder.TVnumeroCuenta.setText(respuesta1[0]);
        holder.TVbalance.setText(respuesta2[0]);
        holder.TVmovimiento.setText(respuesta3[0]);
        holder.TVlink_token.setText(cuentasList.get(position).getLink_token());
        holder.TVapi_key.setText(cuentasList.get(position).getApi_key());
        holder.TVid_cuenta.setText(cuentasList.get(position).getId_cuenta());
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
