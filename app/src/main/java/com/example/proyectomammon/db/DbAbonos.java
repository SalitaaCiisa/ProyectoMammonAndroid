package com.example.proyectomammon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.proyectomammon.resources.Abonos;
import com.example.proyectomammon.resources.Cobros;

import java.util.ArrayList;

public class DbAbonos extends DbHelper {

    private Context context;

    public DbAbonos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long create(Abonos abono){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nombreAbono", abono.getNombreAbono());
            values.put("abonador", abono.getAbonador());
            values.put("monto", abono.getMonto());
            values.put("fechaAbono", abono.getFechaAbono());
            values.put("descripcion", abono.getDescripcion());
            values.put("frecuencia", abono.getFrecuencia());

            idResponse = db.insert(TABLE_ABONOS, null, values);
        }catch (Exception ex){
            Log.e("Error create abono: "+abono.getNombreAbono(), ex.toString());
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
            Log.e("Error read abono: "+abono.getNombreAbono()+", id:"+abono.getId(), ex.toString());
        }

        cursor.close();
        dbhelper.close();

        return abonosList;
    }

    public long update(Abonos abono){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nombreAbono", abono.getNombreAbono());
            values.put("abonador", abono.getAbonador());
            values.put("monto", abono.getMonto());
            values.put("fechaAbono", abono.getFechaAbono());
            values.put("descripcion", abono.getDescripcion());
            values.put("frecuencia", abono.getFrecuencia());

            idResponse = db.update(TABLE_ABONOS, values,"id = '"+abono.getId()+"'",null);
        }catch (Exception ex){
                Log.e("Error update abono: "+abono.getNombreAbono()+", id:"+abono.getId(), ex.toString());
        }

        dbhelper.close();
        return idResponse;
    }

    public long delete(Abonos abono){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            idResponse = db.delete(TABLE_ABONOS,"id = '"+abono.getId()+"'",null);
        }catch (Exception ex){
            Log.e("Error delete abono: "+abono.getNombreAbono()+", id:"+abono.getId(), ex.toString());
        }

        dbhelper.close();
        return idResponse;
    }
}
