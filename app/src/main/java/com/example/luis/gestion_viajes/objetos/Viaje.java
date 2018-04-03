package com.example.luis.gestion_viajes.objetos;

/**
 * Created by luis on 03/04/18.
 */

public class Viaje {
    int cliente,unidad,operadora;
    String direccion,entre1,entre2,colonia;

    public Viaje(int cliente, int unidad, int operadora, String direccion, String entre1, String entre2, String colonia) {
        this.cliente = cliente;
        this.unidad = unidad;
        this.operadora = operadora;
        this.direccion = direccion;
        this.entre1 = entre1;
        this.entre2 = entre2;
        this.colonia = colonia;
    }

    public Viaje(int cliente, int unidad, String direccion, String entre1, String entre2) {
        this.cliente = cliente;
        this.unidad = unidad;
        this.direccion = direccion;
        this.entre1 = entre1;
        this.entre2 = entre2;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public int getOperadora() {
        return operadora;
    }

    public void setOperadora(int operadora) {
        this.operadora = operadora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEntre1() {
        return entre1;
    }

    public void setEntre1(String entre1) {
        this.entre1 = entre1;
    }

    public String getEntre2() {
        return entre2;
    }

    public void setEntre2(String entre2) {
        this.entre2 = entre2;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
}
