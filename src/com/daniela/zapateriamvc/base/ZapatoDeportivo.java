package com.daniela.zapateriamvc.base;

import java.time.LocalDate;

public class ZapatoDeportivo extends Zapato{
    private String tipoSuela;

    public ZapatoDeportivo(){
        super();

    }

    public ZapatoDeportivo(String marca, String modelo, int talla, LocalDate fechaIngreso, String tipoSuela) {
        super(marca, modelo, talla, fechaIngreso);
        this.tipoSuela = tipoSuela;
    }

    public String getTipoSuela() {
        return tipoSuela;
    }

    public void setTipoSuela(String tipoSuela) {
        this.tipoSuela = tipoSuela;
    }

    @Override
    public String toString() {
        return "ZapatoDeportivo:"  + getMarca() + " "  + getModelo() + " " + getTalla();
    }
}
