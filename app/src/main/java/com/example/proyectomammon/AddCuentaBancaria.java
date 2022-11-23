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
import android.widget.Toast;

import com.example.proyectomammon.db.DbCuentas;
import com.example.proyectomammon.resources.Cuentas;

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

        Cuentas cuenta = new Cuentas();
        DbCuentas dbCuentas = new DbCuentas(this);

        txtToolBar.setText("Añadiendo cuenta bancaria");
        btnAccion.setText("Crear cuenta");

        //Flecha para volver a la vista anterior
        backArrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), crudAbonos.class));
            }
        });
        //Boton para Crear
        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate_form()){
                    cuenta.setNombreCuenta(String.valueOf(nombreCuenta.getText()));
                    cuenta.setLink_token(String.valueOf(link_token.getText()));
                    cuenta.setApi_key(String.valueOf(API_Secret_Key.getText()));
                    cuenta.setId_cuenta("ID aleatorio");
                    long respuesta = dbCuentas.create(cuenta);

                    if (respuesta > 0) {
                        Toast.makeText(AddCuentaBancaria.this,"Registro de id "+respuesta+" creado con exito",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(AddCuentaBancaria.this,"Error al crear registro, id: "+respuesta,Toast.LENGTH_LONG).show();
                    }

                    Intent i = new Intent(getApplicationContext(), crudCuentasBancarias.class);
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