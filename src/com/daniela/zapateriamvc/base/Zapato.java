package com.daniela.zapateriamvc.base;

import java.time.LocalDate;

public class Zapato {
    private String marca;
    private String modelo;
    private int talla;
    private LocalDate fechaIngreso;

    public Zapato(){

    }

    public Zapato(String marca, String modelo, int talla, LocalDate fechaIngreso) {
        this.marca = marca;
        this.modelo = modelo;
        this.talla = talla;
        this.fechaIngreso = fechaIngreso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
