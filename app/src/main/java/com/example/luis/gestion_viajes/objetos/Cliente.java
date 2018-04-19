package com.example.luis.gestion_viajes.objetos;

/**
 * Created by luis on 03/04/18.
 */

public class Cliente {
    String Telefono,Nombre,Direccion,Entre_1,Entre_2,Nota,Colonia;
    int Id;


    public Cliente(int id, String nombre, String telefono, String direccion, String entre_1, String entre_2, String colonia, String nota) {
        Id=id;
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


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
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

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }
}
