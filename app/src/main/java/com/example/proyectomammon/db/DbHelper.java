package com.example.proyectomammon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "proyectoMammonDbLite.db";
    public static final String TABLE_ABONOS = "t_abonos";
    public static final String TABLE_COBROS = "t_cobros";
    public static final String TABLE_CUENTAS = "t_cuentas";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_ABONOS+"(" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idUsuario integer," +
                "nombreAbono TEXT NOT NULL," +
                "abonador TEXT not null," +
                "monto integer not null," +
                "fechaAbono TEXT not null," +
                "descripcion TEXT not null," +
                "frecuencia TEXT NOT null)");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_COBROS+"(" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idUsuario integer," +
                "nombreCobro TEXT NOT NULL," +
                "cobrador TEXT not null," +
                "monto integer not null," +
                "fechaCobro TEXT not null," +
                "descripcion TEXT not null," +
                "frecuencia TEXT NOT null)");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_CUENTAS+"(" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "idUsuario integer," +
                "nombreCuenta TEXT NOT NULL," +
                "link_token TEXT not null," +
                "api_key TEXT not null," +
                "id_cuenta TEXT NOT null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_ABONOS);
        onCreate(sqLiteDatabase);

    }
}
