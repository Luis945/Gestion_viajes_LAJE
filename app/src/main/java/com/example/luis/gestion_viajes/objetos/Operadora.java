package com.example.luis.gestion_viajes.objetos;

/**
 * Created by luis on 03/04/18.
 */

public class Operadora {
    int Id;
    String Nombre,Apellidos,Estado,Tipo_operadora,Contraseña;

    public Operadora(int id,String contrasena){
        this.Id=id;
        this.Contraseña=contrasena;
    }

    public Operadora(int id, String nombre, String apellidos, String estado, String tipo_operadora, String contraseña) {
        Id = id;
        Nombre = nombre;
        Apellidos = apellidos;
        Estado = estado;
        Tipo_operadora = tipo_operadora;
        Contraseña = contraseña;
    }

    public Operadora(int id, String nombre, String apellidos, String estado, String tipo_operadora) {
        Id = id;
        Nombre = nombre;
        Apellidos = apellidos;
        Estado = estado;
        Tipo_operadora = tipo_operadora;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getTipo_operadora() {
        return Tipo_operadora;
    }

    public void setTipo_operadora(String tipo_operadora) {
        Tipo_operadora = tipo_operadora;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
