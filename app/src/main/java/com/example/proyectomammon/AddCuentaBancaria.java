package com.example.proyectomammon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddCuentaBancaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cuenta_bancaria);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Bundle intentTransfer = getIntent().getExtras();

        TextView txtToolBar = findViewById(R.id.tvToolbarText);
        ImageView backArrow = findViewById(R.id.flechaAtras);

        EditText nombreCuenta, link_token, API_Secret_Key;
            nombreCuenta = findViewById(R.id.etNombreCuenta);
            link_token = findViewById(R.id.etLink_Token);
            API_Secret_Key = findViewById(R.id.etApiSecretKey);

        Button btnAccion = findViewById(R.id.buttonAccion);

        final String[] respuestasParaPasarView = new String[3];

        txtToolBar.setText("Añadiendo cuenta bancaria");
        btnAccion.setText("Crear cuenta");

        //Flecha para volver a la vista anterior
        backArrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), crudCobros.class));
            }
        });

        //Boton para Crear
        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate_form()){
                    respuestasParaPasarView[0] = String.valueOf(nombreCuenta.getText());
                    respuestasParaPasarView[1] = String.valueOf(link_token.getText());
                    respuestasParaPasarView[2] = String.valueOf(API_Secret_Key.getText());
                    Intent i = new Intent(getApplicationContext(), crudCuentasBancarias.class);
                    i.putExtra("RespuestasCreate", respuestasParaPasarView);
                    startActivity(i);
                }
            }
        });
    }

    //Funcion para validar formulario
    @NonNull
    private Boolean validate_form(){
        EditText nombreCuenta, link_token, API_Secret_Key;
        nombreCuenta = findViewById(R.id.etNombreCuenta);
        link_token = findViewById(R.id.etLink_Token);
        API_Secret_Key = findViewById(R.id.etApiSecretKey);

        if(TextUtils.isEmpty(nombreCuenta.getText().toString())){
            nombreCuenta.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(link_token.getText().toString())){
            link_token.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(API_Secret_Key.getText().toString())){
            API_Secret_Key.setError("Este campo es obligatorio");
            return false;
        }

        return true;
    }
}