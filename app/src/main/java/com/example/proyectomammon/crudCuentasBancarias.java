package com.example.proyectomammon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomammon.adapters.ListaAbonoAdapter;
import com.example.proyectomammon.adapters.ListaCuentaAdapter;
import com.example.proyectomammon.db.DbAbonos;
import com.example.proyectomammon.db.DbCuentas;
import com.example.proyectomammon.db.DbHelper;
import com.example.proyectomammon.interfaces.CuentasAPI;
import com.example.proyectomammon.resources.Abonos;
import com.example.proyectomammon.resources.Cuentas;
import com.example.proyectomammon.resources.CuentasApi;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

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
        ArrayList<CuentasApi> cuentasApisList = new ArrayList<>();
        cuentasList = (ArrayList<Cuentas>) dbCuentas.read().clone();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fintoc.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CuentasAPI cuentasAPI = retrofit.create(CuentasAPI.class);
        int i = 1;
        for (Cuentas cuenta : cuentasList) {
            Toast.makeText(crudCuentasBancarias.this,"Si se llego al forEach vuelta "+i,Toast.LENGTH_LONG).show();
            Call<JsonArray> call = cuentasAPI.find(cuenta.getLink_token(),cuenta.getApi_key());
            call.enqueue(new Callback<JsonArray>(){
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    try {
                        if (response.isSuccessful()) {
                            JsonArray jsonArray = response.body();
                            JsonElement jsonElement = jsonArray.get(0);

                            cuentasApisList.add(new CuentasApi(jsonElement));
                        }
                    }catch (Exception ex){
                        Toast.makeText(crudCuentasBancarias.this,"Error al 222: "+ex,Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Toast.makeText(crudCuentasBancarias.this,"Error al 333: "+t,Toast.LENGTH_LONG).show();
                }
            });
            i++;
        }

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