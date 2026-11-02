package com.daniela.zapateriamvc;

import com.daniela.zapateriamvc.gui.Controlador;
import com.daniela.zapateriamvc.gui.Modelo;
import com.daniela.zapateriamvc.gui.Vista;

public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
       Controlador controlador = new Controlador(vista,modelo);
    }
}

