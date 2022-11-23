package com.example.proyectomammon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.Nullable;

import com.example.proyectomammon.resources.Abonos;

import java.util.ArrayList;

public class DbAbonos extends DbHelper {

    private Context context;

    public DbAbonos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long create(String nombreAbono, String abonador, String monto, String fechaAbono, String descripcion, String frecuencia){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nombreAbono", nombreAbono);
            values.put("abonador", abonador);
            values.put("monto", monto);
            values.put("fechaAbono", fechaAbono);
            values.put("descripcion", descripcion);
            values.put("frecuencia", frecuencia);

            idResponse = db.insert(TABLE_ABONOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }

        dbhelper.close();

        return  idResponse;
    }

    public ArrayList<Abonos> read(){
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<Abonos> abonosList = new ArrayList<>();
        Abonos abono = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM "+TABLE_ABONOS,null);

            if (cursor.moveToNext()){
                do {
                    abono = new Abonos();
                    abono.setId(cursor.getInt(0));
                    abono.setNombreAbono(cursor.getString(2));
                    abono.setAbonador(cursor.getString(3));
                    abono.setMonto(cursor.getInt(4));
                    abono.setFechaAbono(cursor.getString(5));
                    abono.setDescripcion(cursor.getString(6));
                    abono.setFrecuencia(cursor.getString(7));

                    abonosList.add(abono);
                }while (cursor.moveToNext());
            }

        }catch (SQLiteException ex){
            ex.toString();
        }

        cursor.close();
        dbhelper.close();

        return abonosList;
    }
}
