package com.example.proyectomammon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        final Button button = (Button) findViewById(R.id.buttonLogin);
    }

    public void cambioActividadHome(View view){
        Intent i = new Intent(getApplicationContext(), Home.class);
        if (validate_form()){
        startActivity(i);
        }
    }

    public void cambioActividadRegistrarse(View view){
        Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }

    public Boolean validate_form(){
        EditText username_ETX = findViewById(R.id.usuario_ETX);
        EditText password_ETX = findViewById(R.id.password_ETX);

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