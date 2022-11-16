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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectomammon.dialog.DatePickerFragment;

public class AddEditAbonos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_abonos);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Bundle intentTransfer = getIntent().getExtras();

        TextView txtToolBar = findViewById(R.id.tvToolbarText);
        ImageView backArrow = findViewById(R.id.flechaAtras);

        EditText nombreAbono, nombreAbonador, monto, fechaAbono, descripcion;
        nombreAbono = findViewById(R.id.etNombreAbono);
        nombreAbonador = findViewById(R.id.etNombreAbonador);
        monto = findViewById(R.id.etMonto);
        fechaAbono = findViewById(R.id.etFecha);
        descripcion = findViewById(R.id.etDescripcion);

        Spinner spinnerFrecuencia;
        spinnerFrecuencia = findViewById(R.id.spinnerFrecuencia);

        Button btnAccion = findViewById(R.id.buttonAccion);

        final String[] respuestasParaPasarView = new String[6];

        switch (intentTransfer.getString("activityAccion")){
            case "crear":
                txtToolBar.setText("Añadiendo abono");
                btnAccion.setText("Crear abono");
                break;
            case "editar":
                txtToolBar.setText("Editando abono");
                btnAccion.setText("Editar abono");
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
        fechaAbono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(fechaAbono);
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
                    respuestasParaPasarView[0] = String.valueOf(nombreAbono.getText());
                    respuestasParaPasarView[1] = String.valueOf(nombreAbonador.getText());
                    respuestasParaPasarView[2] = String.valueOf(monto.getText());
                    respuestasParaPasarView[3] = String.valueOf(fechaAbono.getText());
                    respuestasParaPasarView[5] = String.valueOf(descripcion.getText());
                    Intent i = new Intent(getApplicationContext(), crudAbonos.class);
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
        EditText nombreAbono, nombreAbonador, monto, fechaAbono, descripcion;
        nombreAbono = findViewById(R.id.etNombreAbono);
        nombreAbonador = findViewById(R.id.etNombreAbonador);
        monto = findViewById(R.id.etMonto);
        fechaAbono = findViewById(R.id.etFecha);
        descripcion = findViewById(R.id.etDescripcion);

        if(TextUtils.isEmpty(nombreAbono.getText().toString())){
            nombreAbono.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(nombreAbonador.getText().toString())){
            nombreAbonador.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(monto.getText().toString())){
            monto.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(fechaAbono.getText().toString())){
            fechaAbono.setError("Este campo es obligatorio");
            return false;
        }
        if(TextUtils.isEmpty(descripcion.getText().toString())){
            descripcion.setError("Este campo es obligatorio");
            return false;
        }

        return true;
    }
}