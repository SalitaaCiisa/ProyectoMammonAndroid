package com.example.proyectomammon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomammon.adapters.ListaCuentaAdapter;
import com.example.proyectomammon.db.DbCuentas;
import com.example.proyectomammon.db.DbHelper;
import com.example.proyectomammon.interfaces.CuentasAPI;
import com.example.proyectomammon.resources.Cuentas;
import com.example.proyectomammon.resources.cuenta_api.CuentasResourceApus;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class crudCuentasBancarias extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //Vista
    NavigationView navView;
    TextView txtToolBar;
    ImageView menuIcon;
    Button botonAnadir;
    RecyclerView RVcuentas;
    //Clases
    LinearLayoutManager llManager;
    ArrayList<Cuentas> cuentasList;
    ArrayList<CuentasResourceApus> cuentasApisList;
    DbHelper dbhelper;
    DbCuentas dbCuentas;
    ListaCuentaAdapter listaCuentaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_cuentas_bancarias);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        /* ------------------------------------------------------------------------- */
        final DrawerLayout drLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navigationview);
        txtToolBar = findViewById(R.id.tvToolbarText);
        menuIcon = findViewById(R.id.menuIcon);
        botonAnadir = findViewById(R.id.buttonAnadir);
        RVcuentas = findViewById(R.id.RVResumenCuentas);

        llManager = new LinearLayoutManager(this);
        dbhelper = new DbHelper(this);
        dbCuentas = new DbCuentas(this);
        /* ------------------------------------------------------------------------- */

        //Adapter llenado
        llenarAdapter();

        //Titulo de la vista
        txtToolBar.setText("Cuentas Bancarias");

        //RecyclerView Cosas
        RVcuentas.setLayoutManager(llManager);
        RVcuentas.setAdapter(listaCuentaAdapter);

        //Boton de menu
        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drLayout.openDrawer(GravityCompat.START);
            }
        });

        //Accion segun seleccion en la barra de navegacion
        navView.setNavigationItemSelectedListener(this);

        //Boton de añadir cuenta
        botonAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCuentaBancaria.class);
                intent.putExtra("activityAccion", "crear");
                startActivity(intent);
            }
        });
    }

    private void llenarAdapter() {
        cuentasApisList = new ArrayList<>();
        cuentasList = (ArrayList<Cuentas>) dbCuentas.read().clone();
        Call<List<CuentasResourceApus>> call;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fintoc.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CuentasAPI cuentasAPI = retrofit.create(CuentasAPI.class);
        int i = 0;
        for (Cuentas cuenta : cuentasList) {
            Toast.makeText(crudCuentasBancarias.this,"Si se llego al forEach vuelta "+i,Toast.LENGTH_LONG).show();
            call = cuentasAPI.find(cuenta.getLink_token(),cuenta.getApi_key());
            int finalI = i;
            call.enqueue(new Callback<List<CuentasResourceApus>>(){
                @Override
                public void onResponse(Call<List<CuentasResourceApus>> call, Response<List<CuentasResourceApus>> response) {
                    try {
                        if ((response.isSuccessful()) && (!response.body().isEmpty())) {
                            cuentasApisList.add(new CuentasResourceApus(response.body().get(0)));
                            //Toast.makeText(crudCuentasBancarias.this,"Cuenta id: "+cuentasApisList.get(finalI).getId(),Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(crudCuentasBancarias.this, "Ronda 2 fallo o esta vacio", Toast.LENGTH_LONG).show();
                            cuentasApisList.add(new CuentasResourceApus());
                        }
                    }catch (Exception ex){
                        Toast.makeText(crudCuentasBancarias.this,"Error al 222 in round "+ finalI,Toast.LENGTH_LONG).show();
                        Log.e("Error in 222 in round "+ finalI, ex.toString());
                        cuentasApisList.add(new CuentasResourceApus());
                    }
                }

                @Override
                public void onFailure(Call<List<CuentasResourceApus>> call, Throwable t) {
                    Toast.makeText(crudCuentasBancarias.this,"Error al 333: "+t,Toast.LENGTH_LONG).show();
                    Log.e("Error in 333", t.toString());
                    cuentasApisList.add(new CuentasResourceApus());
                }
            });
            i++;
        }
        //Toast.makeText(crudCuentasBancarias.this,"Cuenta id: "+cuentasApisList.get(0).getId(),Toast.LENGTH_LONG).show();
        listaCuentaAdapter = new ListaCuentaAdapter(cuentasList,cuentasApisList);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menuHome:
                startActivity(new Intent(getApplicationContext(), Home.class));
                return true;

            case R.id.menuCobros:
                startActivity(new Intent(getApplicationContext(), crudCobros.class));
                return true;

            case R.id.menuAbonos:
                startActivity(new Intent(getApplicationContext(), crudAbonos.class));
                return true;

            case R.id.menuCuentas:
                startActivity(new Intent(getApplicationContext(), crudCuentasBancarias.class));
                return true;

            default:
                return false;
        }
    }
}