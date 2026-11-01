package com.daniela.zapateriamvc.gui;

import com.daniela.zapateriamvc.base.Zapato;
import com.daniela.zapateriamvc.base.ZapatoDeportivo;
import com.daniela.zapateriamvc.base.ZapatoFormal;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {

    public ArrayList<Zapato> listaZapatos;

    public Modelo() {
        listaZapatos = new ArrayList<Zapato>();
    }

    public ArrayList<Zapato> obtenerVehiculos() {
        return listaZapatos;
    }

    //altaZapatoDeportivo
    public void altaZapatoDeportivo(String marca, String modelo, int talla, LocalDate
            fechaIngreso, String tipoSuela) {
        ZapatoDeportivo nuevoZapatoDeportivo = new ZapatoDeportivo(marca, modelo, talla, fechaIngreso, tipoSuela);
        listaZapatos.add(nuevoZapatoDeportivo);

    }

    //altaZapatoFormal
    public void altaZapatoFormal(String marca, String modelo, int talla, LocalDate
            fechaIngreso, String material) {
        ZapatoFormal nuevoZapatoFormal = new ZapatoFormal(marca, modelo, talla, fechaIngreso, material);
        listaZapatos.add(nuevoZapatoFormal);

    }

    //existeTalla
    public boolean existeTalla(int talla) {
        for (Zapato unZapato : listaZapatos) {
            if (unZapato.getTalla() == talla) {
                return true;
            }
        }
        return false;
    }

    //exportar
    public void exportar(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //añado el nodo raiz Zapatos
        Element raiz = documento.createElement("Zapatos");
        documento.getDocumentElement().appendChild(raiz);

        //otros elementos
        Element nodoZapato = null;
        Element nodoDatos = null;
        Text texto = null;


        for (Zapato unZapato : listaZapatos) {
            //añado dentro de la etiqueta raiz vehiculos (coche o moto)
            if (unZapato instanceof ZapatoDeportivo) {
                nodoZapato = documento.createElement("ZapatoDeportivo");
            } else {
                nodoZapato = documento.createElement("ZapatoFormal");
            }
            raiz.appendChild(nodoZapato);

        }
    }
}

