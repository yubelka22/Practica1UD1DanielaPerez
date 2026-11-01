package com.daniela.zapateriamvc.gui;

import com.daniela.zapateriamvc.base.Zapato;
import com.daniela.zapateriamvc.base.ZapatoDeportivo;
import com.daniela.zapateriamvc.base.ZapatoFormal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {

    public ArrayList<Zapato> listaZapatos;

    public Modelo() {
        listaZapatos= new ArrayList<Zapato>();
    }

    public ArrayList<Zapato> obtenerVehiculos() {
        return listaZapatos;
    }

    //altaZapatoDeportivo
    public void altaZapatoDeportivo(String marca, String modelo, int talla, LocalDate
            fechaIngreso, String tipoSuela){
           ZapatoDeportivo nuevoZapatoDeportivo = new ZapatoDeportivo(marca,modelo,talla,fechaIngreso,tipoSuela);
            listaZapatos.add(nuevoZapatoDeportivo);

    }

    //altaZapatoFormal
    public void altaZapatoFormal(String marca, String modelo, int talla, LocalDate
            fechaIngreso, String material){
        ZapatoFormal nuevoZapatoFormal = new ZapatoFormal(marca,modelo,talla,fechaIngreso,material);
        listaZapatos.add(nuevoZapatoFormal);

    }
}
