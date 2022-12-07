package com.example.proyectomammon;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectomammon.adapters.ListaCobroAdapter;
import com.example.proyectomammon.db.DbCobros;
import com.google.android.material.navigation.NavigationView;

public class crudCobros extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //Vista
    NavigationView navView;
    TextView txtToolBar;
    ImageView menuIcon;
    RecyclerView RVcobros;
    //Clases
    LinearLayoutManager llManager;
    DbCobros dbCobros;
    ListaCobroAdapter listaCobroAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_cobros);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        /*-------------------------------------------------------------------------------------------*/
        final DrawerLayout drLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navigationview);
        txtToolBar = findViewById(R.id.tvToolbarText);
        menuIcon = findViewById(R.id.menuIcon);
        RVcobros = findViewById(R.id.recyclerViewCobros);
        RVcobros.setLayoutManager(llManager);

        llManager = new LinearLayoutManager(getApplicationContext());
        dbCobros = new DbCobros(this);
        listaCobroAdapter = new ListaCobroAdapter(dbCobros.read(),this);
        /*-------------------------------------------------------------------------------------------*/

        //Titulo de vista
        txtToolBar.setText("Cobros");

        //RecyclerView cosas
        RVcobros.setLayoutManager(llManager);
        RVcobros.setAdapter(listaCobroAdapter);

        //Botton de menu
        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drLayout.openDrawer(GravityCompat.START);
            }
        });

        //Acciones de la barra de navegacion
        navView.setNavigationItemSelectedListener(this);

        //Boton de añadir cobro
        Button botonAnadir = findViewById(R.id.buttonAnadir);
        botonAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEditCobros.class);
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