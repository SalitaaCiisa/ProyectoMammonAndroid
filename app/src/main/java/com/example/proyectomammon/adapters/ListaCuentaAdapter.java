package com.example.proyectomammon.adapters;

import android.annotation.SuppressLint;
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
import com.example.proyectomammon.resources.CuentasApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaCuentaAdapter extends RecyclerView.Adapter<ListaCuentaAdapter.CuentaViewHolder> {

    private ArrayList<Cuentas> cuentasList;
    private ArrayList<CuentasApi> cuentasApisList;

    public ListaCuentaAdapter(ArrayList<Cuentas> cuentasList, ArrayList<CuentasApi> cuentasApisList) {
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

        holder.TVnombreCuenta.setText(cuentasList.get(position).getNombreCuenta());
        holder.TVlink_token.setText(cuentasList.get(position).getLink_token());
        holder.TVapi_key.setText(cuentasList.get(position).getApi_key());
        /*
        holder.TVnumeroCuenta.setText(cuentasApisList.get(0).getNumeroCuenta());
        holder.TVbalance.setText(Integer.toString(cuentasApisList.get(0).getBalance()));
        holder.TVmovimiento.setText(cuentasApisList.get(0).getFechaHoraUltimoMovimiento());
        holder.TVid_cuenta.setText(cuentasApisList.get(0).getNumeroCuenta());
         */
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
