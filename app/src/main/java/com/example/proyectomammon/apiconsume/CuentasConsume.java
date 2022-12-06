package com.example.proyectomammon.apiconsume;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectomammon.interfaces.CuentasAPI;
import com.example.proyectomammon.resources.Cuentas;
import com.example.proyectomammon.resources.cuenta_api.CuentasResourceApus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CuentasConsume {
    private Context context;
    private Cuentas cuentaDB;


    private ArrayList<CuentasResourceApus> cuentasResourceApusList = new ArrayList<>();
    private Call<List<CuentasResourceApus>> call;

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.fintoc.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private CuentasAPI cuentasAPI = retrofit.create(CuentasAPI.class);

    public CuentasConsume(Context context, Cuentas cuentaDB) {
        this.context = context;
        this.cuentaDB = cuentaDB;
    }

    public ArrayList<CuentasResourceApus> getAllCuentas(){

        call = cuentasAPI.find(cuentaDB.getLink_token(), cuentaDB.getApi_key());

        call.enqueue(new Callback<List<CuentasResourceApus>>() {
            @Override
            public void onResponse(Call<List<CuentasResourceApus>> call, Response<List<CuentasResourceApus>> response) {
                try {
                    if ((response.isSuccessful()) && (!response.body().isEmpty())) {
                        cuentasResourceApusList.addAll(response.body());

                        Toast.makeText(context,"Exito en cuenta "+cuentaDB.getNombreCuenta(),Toast.LENGTH_LONG).show();

                        Log.v("logs onResponde", "LOGS: "+cuentasResourceApusList.size());
                        ArrayList<CuentasResourceApus> cuentasResourceApusArrayList = new ArrayList<>();
                        for (CuentasResourceApus cra :response.body()) {
                            cuentasResourceApusArrayList.add(new CuentasResourceApus(cuentasResourceApusList.get(0)));
                        }
                    }else {
                        Toast.makeText(context, "Cuenta "+ cuentaDB.getNombreCuenta()+" fallo o esta vacia, Error 666", Toast.LENGTH_LONG).show();
                        Log.e("Error 666 in cuenta "+ cuentaDB.getNombreCuenta(),response.errorBody().string());
                    }
                }catch (Exception ex){
                    Toast.makeText(context,"Error al 222 in cuenta "+ cuentaDB.getNombreCuenta(),Toast.LENGTH_LONG).show();
                    Log.e("Error al 222 in cuenta "+ cuentaDB.getNombreCuenta(), ex.toString());
                }
            }

            @Override
            public void onFailure(Call<List<CuentasResourceApus>> call, Throwable t) {
                Toast.makeText(context,"Error al 333 en cuenta " + cuentaDB.getNombreCuenta(),Toast.LENGTH_LONG).show();
                Log.e("Error al 333 on cuenta" + cuentaDB.getNombreCuenta(), t.toString());
            }
        });

        Log.v("logs getAllCuentas", "LOGS: "+cuentasResourceApusList.size());
        return cuentasResourceApusList;
    }
}
