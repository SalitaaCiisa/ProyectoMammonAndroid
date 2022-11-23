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

import com.example.proyectomammon.adapters.ListaAbonoAdapter;
import com.example.proyectomammon.adapters.ListaCuentaAdapter;
import com.example.proyectomammon.db.DbAbonos;
import com.example.proyectomammon.db.DbCuentas;
import com.example.proyectomammon.db.DbHelper;
import com.example.proyectomammon.resources.Abonos;
import com.example.proyectomammon.resources.Cuentas;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

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
        cuentasList = new ArrayList<>();
        dbhelper = new DbHelper(this);
        dbCuentas = new DbCuentas(this);
        listaCuentaAdapter = new ListaCuentaAdapter(dbCuentas.read());
        /* ------------------------------------------------------------------------- */

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