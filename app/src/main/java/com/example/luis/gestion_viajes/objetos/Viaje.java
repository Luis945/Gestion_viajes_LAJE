package com.example.luis.gestion_viajes.objetos;

/**
 * Created by luis on 03/04/18.
 */

public class Viaje {
    int cliente,unidad,operadora;
    String telefono,direccion,entre1,entre2,colonia, fecha;


    public Viaje(int cliente, int unidad, int operadora, String telefono, String direccion, String entre1, String entre2, String colonia, String fecha) {
        this.cliente = cliente;
        this.unidad = unidad;
        this.operadora = operadora;
        this.telefono = telefono;
        this.direccion = direccion;
        this.entre1 = entre1;
        this.entre2 = entre2;
        this.colonia = colonia;
        this.fecha = fecha;
    }

    public Viaje(int cliente, int unidad, int operadora, String telefono, String direccion, String entre1, String entre2, String colonia) {
        this.cliente = cliente;
        this.unidad = unidad;
        this.operadora = operadora;
        this.telefono = telefono;
        this.direccion = direccion;
        this.entre1 = entre1;
        this.entre2 = entre2;
        this.colonia = colonia;
    }

    public Viaje(int unidad, int operadora, String telefono, String direccion, String fecha) {
        this.unidad = unidad;
        this.operadora = operadora;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
