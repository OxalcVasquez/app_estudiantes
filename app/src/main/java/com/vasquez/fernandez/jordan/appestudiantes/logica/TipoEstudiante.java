package com.vasquez.fernandez.jordan.appestudiantes.logica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vasquez.fernandez.jordan.appestudiantes.datos.Conexion;

import java.util.ArrayList;

public class TipoEstudiante extends Conexion {
    private int id;
    private String nombre;

    public static ArrayList<TipoEstudiante> listaTipos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void cargarDatosTipos(){
        SQLiteDatabase bd = this.getReadableDatabase();

        String sql = "select * from tipo_estudiante";

        Cursor cursorTipos = bd.rawQuery(sql,null);

        listaTipos.clear();

        while (cursorTipos.moveToNext()){
            TipoEstudiante tipoEstudiante = new TipoEstudiante();
            tipoEstudiante.setId(cursorTipos.getInt(0)); //id
            tipoEstudiante.setNombre(cursorTipos.getString(1)); //String
            listaTipos.add(tipoEstudiante);
        }

    }

    public String[] obtenernombreTiposes() {
        this.cargarDatosTipos();
        String nombreTipos[] = new String[listaTipos.size()];

        for (int i = 0; i < listaTipos.size() ; i++) {
            nombreTipos[i] = listaTipos.get(i).getNombre();
        }

        return nombreTipos;
    }

}
