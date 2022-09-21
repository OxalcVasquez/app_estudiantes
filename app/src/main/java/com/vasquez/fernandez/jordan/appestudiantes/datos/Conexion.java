package com.vasquez.fernandez.jordan.appestudiantes.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {

    public static Context contextApp;
    public static String dbName = "BDEstudiantes";
    public static int version = 1;

    public Conexion() {
        super(contextApp,dbName , null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Tablas.tablaTipoEstudiante);
        sqLiteDatabase.execSQL(Tablas.tablaEstudiante);

        for (int i = 0; i <  Tablas.tablaTipoDatos.length; i++) {
                    sqLiteDatabase.execSQL(Tablas.tablaTipoDatos[i]);

        }
        for (int i = 0; i <  Tablas.tablaEstudianteDatos.length; i++) {
            sqLiteDatabase.execSQL(Tablas.tablaEstudianteDatos[i]);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
