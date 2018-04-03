package com.example.luis.gestion_viajes.objetos;

/**
 * Created by luis on 03/04/18.
 */

public class Cliente {
    String Telefono,Nombre,Direccion,Entre_1,Entre_2,Nota;
    int Colonia;

    public Cliente(String telefono, String nombre, String direccion, String entre_1, String entre_2, int colonia) {
        Telefono = telefono;
        Nombre = nombre;
        Direccion = direccion;
        Entre_1 = entre_1;
        Entre_2 = entre_2;
        Colonia = colonia;

    }

    public Cliente(String telefono, String nombre, String direccion, String entre_1, String entre_2, String nota, int colonia) {
        Telefono = telefono;
        Nombre = nombre;
        Direccion = direccion;
        Entre_1 = entre_1;
        Entre_2 = entre_2;
        Nota = nota;
        Colonia = colonia;
    }

    public Cliente() {

    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getEntre_1() {
        return Entre_1;
    }

    public void setEntre_1(String entre_1) {
        Entre_1 = entre_1;
    }

    public String getEntre_2() {
        return Entre_2;
    }

    public void setEntre_2(String entre_2) {
        Entre_2 = entre_2;
    }

    public int getColonia() {
        return Colonia;
    }

    public void setColonia(int colonia) {
        Colonia = colonia;
    }
}
