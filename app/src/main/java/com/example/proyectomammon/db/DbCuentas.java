package com.example.proyectomammon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.Nullable;

import com.example.proyectomammon.resources.Abonos;
import com.example.proyectomammon.resources.Cuentas;

import java.util.ArrayList;

public class DbCuentas extends DbHelper{

    private Context context;

    public DbCuentas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long create(Cuentas cuenta){
        long idResponse = -1;
        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nombreCuenta", cuenta.getNombreCuenta());
            values.put("link_token", cuenta.getLink_token());
            values.put("api_key", cuenta.getApi_key());
            values.put("id_cuenta", cuenta.getId_cuenta());

            idResponse = db.insert(TABLE_CUENTAS, null, values);
        }catch (Exception ex){
            ex.toString();
        }

        dbhelper.close();

        return  idResponse;
    }

    public ArrayList<Cuentas> read(){

        DbHelper dbhelper = new DbHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<Cuentas> cuentasList = new ArrayList<>();
        Cuentas cuenta = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM "+TABLE_CUENTAS,null);

            if (cursor.moveToNext()){
                do {
                    cuenta = new Cuentas();
                    cuenta.setId(cursor.getInt(0));
                    cuenta.setNombreCuenta(cursor.getString(2));
                    cuenta.setLink_token(cursor.getString(3));
                    cuenta.setApi_key(cursor.getString(4));
                    cuenta.setId_cuenta(cursor.getString(5));

                    cuentasList.add(cuenta);
                }while (cursor.moveToNext());
            }

        }catch (SQLiteException ex){
            ex.toString();
        }

        cursor.close();
        dbhelper.close();

        return cuentasList;
    }
}
