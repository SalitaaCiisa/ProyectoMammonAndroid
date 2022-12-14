package com.example.proyectomammon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        final DrawerLayout drLayout = findViewById(R.id.drawerLayout);
        NavigationView navView = findViewById(R.id.navigationview);
        TextView txtToolBar = findViewById(R.id.tvToolbarText);
        ImageView menuIcon = findViewById(R.id.menuIcon);

        txtToolBar.setText("Home");

        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drLayout.openDrawer(GravityCompat.START);
            }
        });

        navView.setNavigationItemSelectedListener(this);
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