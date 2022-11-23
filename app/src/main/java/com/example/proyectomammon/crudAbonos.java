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
import com.example.proyectomammon.db.DbAbonos;
import com.example.proyectomammon.resources.Abonos;
import com.example.proyectomammon.db.DbHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class crudAbonos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //Vista
    NavigationView navView;
    TextView txtToolBar;
    ImageView menuIcon;
    Button botonAnadir;
    RecyclerView RVabonos;
    //Clases
    LinearLayoutManager llManager;
    ArrayList<Abonos> abonosList;
    DbHelper dbhelper;
    DbAbonos dbAbonos;
    SQLiteDatabase db;
    ListaAbonoAdapter listaAbonoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_abonos);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        /* ------------------------------------------------------------------------- */
        final DrawerLayout drLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navigationview);
        txtToolBar = findViewById(R.id.tvToolbarText);
        menuIcon = findViewById(R.id.menuIcon);
        botonAnadir = findViewById(R.id.buttonAnadir);
        RVabonos = findViewById(R.id.recyclerViewAbonos);

        llManager = new LinearLayoutManager(this);
        abonosList = new ArrayList<>();
        dbhelper = new DbHelper(this);
        dbAbonos = new DbAbonos(this);
        listaAbonoAdapter = new ListaAbonoAdapter(dbAbonos.read());
        /* -------------------------------------------------------------------------- */

        //Titulo de la vista
        txtToolBar.setText("Abonos");

        //RecyclerView Cosas
        RVabonos.setLayoutManager(llManager);
        RVabonos.setAdapter(listaAbonoAdapter);

        //Boton de menu
        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drLayout.openDrawer(GravityCompat.START);
            }
        });

        //Accion segun seleccion en la barra de navegacion
        navView.setNavigationItemSelectedListener(this);

        //Boton de añadir abono
        botonAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEditAbonos.class);
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