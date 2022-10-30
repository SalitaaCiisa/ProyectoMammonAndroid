package com.example.proyectomammon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        final TextView textIngresarSesion = (TextView) findViewById(R.id.textViewVolverLogin);
        final Button boton_Registrar = findViewById(R.id.buttonRegistrarse);

        boton_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView texto = findViewById(R.id.textView_Avisa_Validador);
                if (validate_form()){
                    texto.setText("Campos validados correctamente");
                }
            }
        });
    }

    public void cambioActividad(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public Boolean validate_form(){
        EditText email_ETX = findViewById(R.id.email_ETX);
        EditText username_ETX = findViewById(R.id.usuario_ETX);
        EditText password_ETX = findViewById(R.id.password_ETX);

        if(TextUtils.isEmpty(email_ETX.getText().toString())){
            email_ETX.setError("Este campo es obligatorio");
            return false;
        }
        else if(!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email_ETX.getText().toString()).find()){
            email_ETX.setError("El formato del correo es incorrecto");
            return false;
        }
        if(TextUtils.isEmpty(username_ETX.getText().toString())){
            username_ETX.setError("Este campo es obligatorio");
            return false;
        }
        else if(username_ETX.getText().toString().length() < 5){
            username_ETX.setError("El largo del username no puede ser menor a 5 caracteres");
            return false;
        }
        else if(!Pattern.compile("^([A-Za-z]{5,})$").matcher(username_ETX.getText().toString()).find()){
            username_ETX.setError("El username debe contener solo letras");
            return false;
        }
        if(TextUtils.isEmpty(password_ETX.getText().toString())){
            password_ETX.setError("Este campo es obligatorio");
            return false;
        }
        else if(password_ETX.getText().toString().length() < 8){
            password_ETX.setError("El largo de la contraseña no puede ser menor a 8 caracteres");
            return false;
        }
        else if(Pattern.compile("^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$").matcher(password_ETX.getText().toString()).find()){
            password_ETX.setError("La contraseña debe contener una letra mayuscula, una minuscula, un numero y un caracter especial");
            return false;
        }

        return true;
    }
}