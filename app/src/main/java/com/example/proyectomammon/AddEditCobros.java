package com.example.proyectomammon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectomammon.dialog.DatePickerFragment;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddEditCobros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_cobros);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Bundle intentTransfer = getIntent().getExtras();

        TextView txtToolBar = findViewById(R.id.tvToolbarText);
        ImageView backArrow = findViewById(R.id.flechaAtras);

        EditText nombreCobro, nombreCobrador, monto, fechaCobro, descripcion;
            nombreCobro = findViewById(R.id.etNombreCobro);
            nombreCobrador = findViewById(R.id.etNombreCobrador);
            monto = findViewById(R.id.etMonto);
            fechaCobro = findViewById(R.id.etFecha);
            descripcion = findViewById(R.id.etDescripcion);

        Spinner spinnerFrecuencia;
            spinnerFrecuencia = findViewById(R.id.spinnerFrecuencia);

        Button btnAccion = findViewById(R.id.buttonAccion);

        final String[] respuestasParaPasarView = new String[6];

        switch (intentTransfer.getString("activityAccion")){
            case "crear":
                txtToolBar.setText("Añadiendo cobro");
                btnAccion.setText("Crear cobro");
                break;
            case "editar":
                txtToolBar.setText("Editando cobro");
                btnAccion.setText("Editar cobro");
                break;
            default:
                txtToolBar.setText("Error al transferir nombre");
                break;
        }

        //Flecha para volver a la vista anterior
        backArrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), crudCobros.class));
            }
        });
        //Dialogo para seleccionar fecha
        fechaCobro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(fechaCobro);
            }
        });
        //Seleccion Spinner, guardado de datos
        spinnerFrecuencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                respuestasParaPasarView[4] = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Boton para Crear
        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate_form()){
                    respuestasParaPasarView[0] = String.valueOf(nombreCobro.getText());
                    respuestasParaPasarView[1] = String.valueOf(nombreCobrador.getText());
                    respuestasParaPasarView[2] = String.valueOf(monto.getText());
                    respuestasParaPasarView[3] = String.valueOf(fechaCobro.getText());
                    respuestasParaPasarView[5] = String.valueOf(descripcion.getText());
                    Intent i = new Intent(getApplicationContext(), crudCobros.class);
                    i.putExtra("RespuestasCreate", respuestasParaPasarView);
                    startActivity(i);
                }
            }
        });
    }

    private void showDatePickerDialog(EditText i) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                final String selectedDate = day + " / " + (month+1) + " / " + year;
                i.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    //Funcion para validar formulario
    @NonNull
    private Boolean validate_form(){
        EditText nombreCobro, nombreCobrador, monto, fechaCobro, descripcion;
        nombreCobro = findViewById(R.id.etNombreCobro);
        nombreCobrador = findViewById(R.id.etNombreCobrador);
        monto = findViewById(R.id.etMonto);
        fechaCobro = findViewById(R.id.etFecha);
        descripcion = findViewById(R.id.etDescripcion);

        if(TextUtils.isEmpty(nombreCobro.getText().toString())){
            nombreCobro.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(nombreCobrador.getText().toString())){
            nombreCobrador.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(monto.getText().toString())){
            monto.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(fechaCobro.getText().toString())){
            fechaCobro.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(descripcion.getText().toString())){
            descripcion.setError("Este campo es obligatorio");
            return false;
        }

        return true;
    }
}