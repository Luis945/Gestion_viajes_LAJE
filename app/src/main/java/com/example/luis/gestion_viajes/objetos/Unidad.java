package com.example.luis.gestion_viajes.objetos;

/**
 * Created by luis on 03/04/18.
 */

public class Unidad {
    int reg;
    String estado;

    public Unidad(int reg, String estado) {
        this.reg = reg;
        this.estado = estado;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
