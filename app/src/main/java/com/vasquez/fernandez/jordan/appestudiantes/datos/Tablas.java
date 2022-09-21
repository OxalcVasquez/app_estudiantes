package com.vasquez.fernandez.jordan.appestudiantes.datos;

public class Tablas {
    public static String tablaTipoEstudiante = "CREATE TABLE TIPO_ESTUDIANTE(\n" +
            "id int PRIMARY KEY,\n" +
            "nombre varchar(20)\n" +
            ");";

    public static String tablaTipoDatos[]=  new String[]{
            "INSERT INTO TIPO_ESTUDIANTE VALUES (1,'Pregrado');" ,
            "INSERT INTO TIPO_ESTUDIANTE VALUES (2,'Posgrado');"};

    public static String tablaEstudiante = "CREATE TABLE ESTUDIANTE\n" +
            "(\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "nombres_apellidos varchar(50) NOT NULL,\n" +
            "direccion varchar(100) NOT NULL,\n" +
            "sexo bit NOT NULL,\n" +
            "membresia bit NOT NULL,\n" +
            "numero_telefono varchar(20) NOT NULL,\n" +
            "foto blob,\n" +
            "fecha_nacimiento date NOT NULL,\n" +
            "contrasena char(32) NOT NULL,\n" +
            "promedio_semestral real NOT NULL,\n" +
            "tipo_estudiante_id int NOT NULL,\n" +
            "FOREIGN KEY (tipo_estudiante_id) REFERENCES tipo_estudiante(id)\n" +
            ");";

    public static String tablaEstudianteDatos[]=  new String[]{
            "INSERT INTO ESTUDIANTE VALUES (1,'PEREZ JUAN','Av. Balta 23','1','1','98123123',null,'12/04/2022','d9b0a6634f1d768a37e0a1e22ba68e37',12.5,1);" ,
                    "INSERT INTO ESTUDIANTE VALUES (2,'SOLARI MARIA','Av. Balta 23','0','0','2123123',null,'12/04/2008','d9b0a6634f1d768a37e0a1e22ba68e37',16,2);" };


}
