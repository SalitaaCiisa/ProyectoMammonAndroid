package com.example.proyectomammon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.proyectomammon.resources.Cobros;
import com.example.proyectomammon.resources.Cuentas;

import java.util.ArrayList;

public class DbCobros extends DbHelper{

    private Context context;

    public DbCobros(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long create(Cobros cobro){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nombreCobro", cobro.getNombreCobro());
            values.put("cobrador", cobro.getCobrador());
            values.put("monto", cobro.getMonto());
            values.put("fechaCobro", cobro.getFechaCobro());
            values.put("descripcion", cobro.getDescripcion());
            values.put("frecuencia", cobro.getFrecuencia());

            idResponse = db.insert(TABLE_COBROS, null, values);
        }catch (Exception ex){
            Log.e("Error create cobro: "+cobro.getNombreCobro(), ex.toString());
        }

        dbhelper.close();

        return  idResponse;
    }

    public ArrayList<Cobros> read(){

        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<Cobros> cobrosList = new ArrayList<>();
        Cobros cobro = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM "+TABLE_COBROS,null);

            if (cursor.moveToNext()){
                do {
                    cobro = new Cobros();
                    cobro.setId(cursor.getInt(0));
                    cobro.setNombreCobro(cursor.getString(2));
                    cobro.setCobrador(cursor.getString(3));
                    cobro.setMonto(cursor.getInt(4));
                    cobro.setFechaCobro(cursor.getString(5));
                    cobro.setDescripcion(cursor.getString(6));
                    cobro.setFrecuencia(cursor.getString(7));

                    cobrosList.add(cobro);
                }while (cursor.moveToNext());
            }

        }catch (SQLiteException ex){
            Log.e("Error read cobro: "+cobro.getNombreCobro()+", id:"+cobro.getId(), ex.toString());
        }

        cursor.close();
        dbhelper.close();

        return cobrosList;
    }

    public long update(Cobros cobro){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nombreCobro", cobro.getNombreCobro());
            values.put("cobrador", cobro.getCobrador());
            values.put("monto", cobro.getMonto());
            values.put("fechaCobro", cobro.getFechaCobro());
            values.put("descripcion", cobro.getDescripcion());
            values.put("frecuencia", cobro.getFrecuencia());

            idResponse = db.update(TABLE_COBROS, values,"id = '"+cobro.getId()+"'",null);
        }catch (Exception ex){
            Log.e("Error update cobro: "+cobro.getNombreCobro()+", id:"+cobro.getId(), ex.toString());
        }

        dbhelper.close();
        return idResponse;
    }

    public long delete(Cobros cobro){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try {
            idResponse = db.delete(TABLE_COBROS,"id = '"+cobro.getId()+"'",null);
        }catch (Exception ex){
            Log.e("Error delete cobro: "+cobro.getNombreCobro()+", id:"+cobro.getId(), ex.toString());
        }

        dbhelper.close();
        return idResponse;
    }
}
