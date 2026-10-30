package com.daniela.zapateriamvc.base;

import java.time.LocalDate;

public class ZapatoFormal extends Zapato{
    private String material;

    public ZapatoFormal(){
        super();
    }

    public ZapatoFormal(String marca, String modelo, int talla, LocalDate fechaIngreso, String material) {
        super(marca, modelo, talla, fechaIngreso);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "ZapatoFormal:"  + getMarca() + " "  + getModelo() + " " + getTalla();
    }
}
