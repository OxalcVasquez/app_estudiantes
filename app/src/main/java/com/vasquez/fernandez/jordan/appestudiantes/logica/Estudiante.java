package com.vasquez.fernandez.jordan.appestudiantes.logica;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vasquez.fernandez.jordan.appestudiantes.datos.Conexion;

import java.util.ArrayList;

public class Estudiante extends Conexion {

    private int id;
    private String nombre;
    private String direccion;
    private boolean sexo;
    private boolean membresia;
    private String telefono;
    private String foto;
    private String fechaNacimiento;
    private int tipoEstudianteId;
    private String contrasena;
    private double promedio;
    private String tipoEstudiante;

    public static ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isMembresia() {
        return membresia;
    }

    public void setMembresia(boolean membresia) {
        this.membresia = membresia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTipoEstudianteId() {
        return tipoEstudianteId;
    }

    public void setTipoEstudianteId(int tipoEstudianteId) {
        this.tipoEstudianteId = tipoEstudianteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getTipoEstudiante() {
        return tipoEstudiante;
    }

    public void setTipoEstudiante(String tipoEstudiante) {
        this.tipoEstudiante = tipoEstudiante;
    }

    public void cargarDatosEstudiante(){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT E.*,T.nombre as tipo  FROM ESTUDIANTE E\n" +
                "INNER JOIN TIPO_ESTUDIANTE T ON (E.tipo_estudiante_id == T.id)\n" +
                "order by nombres_apellidos\n" +
                ";\n";

        Cursor cursor = db.rawQuery(sql,null);

        listaEstudiantes.clear();

        while(cursor.moveToNext()){
            Estudiante estudiante = new Estudiante();
            estudiante.setId(cursor.getInt(0));
            estudiante.setNombre(cursor.getString(1));
            estudiante.setDireccion(cursor.getString(2));
            estudiante.setSexo( cursor.getString(3).equals("1"));
            estudiante.setMembresia( cursor.getString(4).equals("1"));
            estudiante.setTelefono(cursor.getString(5));
            estudiante.setFoto(cursor.getString(6));
            estudiante.setFechaNacimiento(cursor.getString(7));
            estudiante.setContrasena(cursor.getString(8));
            estudiante.setPromedio(cursor.getDouble(9));
            estudiante.setTipoEstudianteId(cursor.getInt(10));
            estudiante.setTipoEstudiante(cursor.getString(11));

            listaEstudiantes.add(estudiante);

        }
    }

    public long registrar(){
        long r = 0;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombres_apellidos",this.getNombre());
            values.put("direccion",this.getDireccion());
            values.put("sexo",this.isSexo()?"1":"0");
            values.put("membresia",this.isMembresia()?"1":"0");
            values.put("contrasena",this.getContrasena());
            values.put("numero_telefono",this.getTelefono());
            values.put("foto",this.getFoto());
            values.put("fecha_nacimiento",this.getFechaNacimiento());
            values.put("promedio_semestral",this.getPromedio());
            values.put("tipo_estudiante_id",this.getTipoEstudianteId());
            r = db.insert("estudiante",null,values);
        } catch (Exception e){
            e.printStackTrace();

        }
        return  r;

    }

    public long eliminar(){
        long r = 0;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String id = String.valueOf(this.getId());

            r = db.delete("estudiante","id = ?", new String[]{id});
        } catch (Exception e){
            e.printStackTrace();

        }
        return  r;

    }

}
