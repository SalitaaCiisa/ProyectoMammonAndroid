package com.example.proyectomammon.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomammon.R;
import com.example.proyectomammon.apiconsume.CuentasConsume;
import com.example.proyectomammon.crudCuentasBancarias;
import com.example.proyectomammon.interfaces.CuentasAPI;
import com.example.proyectomammon.resources.Cuentas;
import com.example.proyectomammon.resources.cuenta_api.CuentasResourceApus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaCuentaAdapter extends RecyclerView.Adapter<ListaCuentaAdapter.CuentaViewHolder> {

    private ArrayList<Cuentas> cuentasDbList;
    private Context context;

    public ListaCuentaAdapter(Context context, ArrayList<Cuentas> cuentasDbList/*, ArrayList<CuentasResourceApus> cuentasApisList*/) {
        this.cuentasDbList = cuentasDbList;
        this.context = context;
    }

    @NonNull
    @Override
    public CuentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuenta_lista_item, parent, false);
        return new CuentaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CuentaViewHolder holder, @SuppressLint("RecyclerView") int position) {

        /*
        CuentasConsume cuentasConsume = new CuentasConsume(context,cuentasDbList.get(position));
        ArrayList<CuentasResourceApus> cuentasResourceApusArrayList = cuentasConsume.getAllCuentas();
        CuentasResourceApus cuentasApis = cuentasResourceApusArrayList.get(0);

        //DB
        holder.TVnombreCuenta.setText(cuentasDbList.get(position).getNombreCuenta());
        holder.TVlink_token.setText(cuentasDbList.get(position).getLink_token());
        holder.TVapi_key.setText(cuentasDbList.get(position).getApi_key());

        //API
        holder.TVnumeroCuenta.setText(cuentasApis.getNumber());
        holder.TVmovimiento.setText(cuentasApis.getRefreshedAt());
        holder.TVid_cuenta.setText(cuentasApis.getId());
        holder.TVbalance.setText(Integer.toString(cuentasApis.getBalance().getAvailable()));
         */


        Call<List<CuentasResourceApus>> call;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fintoc.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CuentasAPI cuentasAPI = retrofit.create(CuentasAPI.class);

        //DB
        holder.TVnombreCuenta.setText(cuentasDbList.get(position).getNombreCuenta());
        holder.TVlink_token.setText(cuentasDbList.get(position).getLink_token());
        holder.TVapi_key.setText(cuentasDbList.get(position).getApi_key());

        //API
        call = cuentasAPI.find(cuentasDbList.get(position).getLink_token(),cuentasDbList.get(position).getApi_key());
        call.enqueue(new Callback<List<CuentasResourceApus>>() {
            @Override
            public void onResponse(Call<List<CuentasResourceApus>> call, Response<List<CuentasResourceApus>> response) {
                try {
                    if ((response.isSuccessful()) && (!response.body().isEmpty())) {
                        CuentasResourceApus cuentasApis = response.body().get(0);
                        Toast.makeText(context,"Exito en cuenta "+cuentasDbList.get(position).getNombreCuenta(),Toast.LENGTH_LONG).show();

                        holder.TVnumeroCuenta.setText(cuentasApis.getNumber());
                        holder.TVmovimiento.setText(cuentasApis.getRefreshedAt());
                        holder.TVid_cuenta.setText(cuentasApis.getId());
                        holder.TVbalance.setText(Integer.toString(cuentasApis.getBalance().getAvailable()));

                    }else {
                        Toast.makeText(context, "Cuenta "+ cuentasDbList.get(position).getNombreCuenta()+" fallo o esta vacia, Error 666", Toast.LENGTH_LONG).show();
                        Log.e("Error 666 in cuenta "+ cuentasDbList.get(position).getNombreCuenta(),response.errorBody().string());
                    }
                }catch (Exception ex){
                    Toast.makeText(context,"Error al 222 in cuenta "+ cuentasDbList.get(position).getNombreCuenta(),Toast.LENGTH_LONG).show();
                    Log.e("Error al 222 in cuenta "+ cuentasDbList.get(position).getNombreCuenta(), ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<CuentasResourceApus>> call, Throwable t) {
                Toast.makeText(context,"Error al 333 en cuenta " + cuentasDbList.get(position).getNombreCuenta(),Toast.LENGTH_LONG).show();
                Log.e("Error al 333 on cuenta" + cuentasDbList.get(position).getNombreCuenta(), t.toString());
            }
        });

        /*
        try {
        //DB
        holder.TVnombreCuenta.setText(cuentasDbList.get(position).getNombreCuenta());
        holder.TVlink_token.setText(cuentasDbList.get(position).getLink_token());
        holder.TVapi_key.setText(cuentasDbList.get(position).getApi_key());
        //Api
        holder.TVnumeroCuenta.setText(cuentasApisList.get(position).getNumber());
        holder.TVmovimiento.setText(cuentasApisList.get(position).getRefreshedAt());
        holder.TVid_cuenta.setText(cuentasApisList.get(position).getId());

        }catch (Exception ex){
            Toast.makeText(context,"Error in 444.all holder",Toast.LENGTH_LONG).show();
            Log.e("Error in 444.all holder: "+position, ex.toString());
        }

        try {
            //Api balance
            holder.TVbalance.setText(Integer.toString(cuentasApisList.get(position).getBalance().getAvailable()));
        }catch (Exception ex){
            Toast.makeText(context,"Error in 444.balance holder",Toast.LENGTH_LONG).show();
            Log.e("Error in 444.balance holder: "+position, ex.toString());
        }
         */
    }

    @Override
    public int getItemCount() {
        return cuentasDbList.size();
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
