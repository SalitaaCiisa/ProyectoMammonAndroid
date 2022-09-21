package com.example.proyectomammon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.buttonLogin);
    }

    public void cambioActividad(View view){
        Intent i = new Intent(getApplicationContext(), crudCuentasBancarias.class);
        startActivity(i);
    }
}