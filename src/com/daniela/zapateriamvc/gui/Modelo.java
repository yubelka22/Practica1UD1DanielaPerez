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
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {

    public ArrayList<Zapato> listaZapatos;

    public Modelo() {
        listaZapatos = new ArrayList<Zapato>();
    }

    public ArrayList<Zapato> obtenerZapatos() {
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

//dentro de cada coche, moto tengo que poner las etiquetas matricula, marca,...
    nodoDatos=documento.createElement("marca");
            nodoZapato.appendChild(nodoDatos);

    texto=documento.createTextNode(unZapato.getMarca());
            nodoDatos.appendChild(texto);

    nodoDatos=documento.createElement("modelo");
            nodoZapato.appendChild(nodoDatos);

    texto=documento.createTextNode(unZapato.getModelo());
            nodoDatos.appendChild(texto);

    nodoDatos=documento.createElement("talla");
            nodoZapato.appendChild(nodoDatos);

    texto=documento.createTextNode(unZapato.getTalla());
            nodoDatos.appendChild(texto);

    nodoDatos=documento.createElement("fechaIngreso");
            nodoZapato.appendChild(nodoDatos);

    texto=documento.createTextNode(String.valueOf(unZapato.getFechaIngreso().toString()));
            nodoDatos.appendChild(texto);

    //nos falta el dato diferente (tipoSuela, material)
            if (unZapato instanceof Zapato) {
        nodoDatos=documento.createElement("tipoSuela");
        nodoZapato.appendChild(nodoDatos);

        texto=documento.createTextNode(String.valueOf(((ZapatoDeportivo) unZapato).getTipoSuela()));
        nodoDatos.appendChild(texto);
    } else {
        nodoDatos=documento.createElement("material");
        nodoZapato.appendChild(nodoDatos);

        texto=documento.createTextNode(String.valueOf(((ZapatoFormal) unZapato).getMaterial()));
        nodoDatos.appendChild(texto);
    }

}
    //guardo en fichero
    Source source = new DOMSource(documento);
    Result resultado = new StreamResult(fichero);

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);

                }

//importar
public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException{
        listaZapatos=new ArrayList<Zapato>();
        ZapatoDeportivo nuevoZapatoDeportivo =null;
        ZapatoFormal nuevoZapatoFormal =null;


        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document documento=builder.parse(fichero);

        NodeList listaElementos=documento.getElementsByTagName("*");

        for(int i=0;i<listaElementos.getLength();i++){
        Element nodoZapato=(Element)listaElementos.item(i);
        if(nodoZapato.getTagName().equals("ZapatoDeportivo")){
        nuevoZapatoDeportivo=new ZapatoDeportivo();
        nuevoZapatoDeportivo.setMarca(nodoZapato.getChildNodes().item(0).getTextContent());
        nuevoZapatoDeportivo.setModelo(nodoZapato.getChildNodes().item(1).getTextContent());
        nuevoZapatoDeportivo.settalla(nodoVehiculo.getChildNodes().item(2).getTextContent());
        nuevoCoche.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
        nuevoCoche.setNumPlazas(Integer.parseInt(nodoVehiculo.getChildNodes().item(4).getTextContent()));
        listaVehiculos.add(nuevoCoche);
        }else{
        nuevaMoto=new Moto();
        nuevaMoto.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
        nuevaMoto.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
        nuevaMoto.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
        nuevaMoto.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
        nuevaMoto.setKms(Double.parseDouble(nodoVehiculo.getChildNodes().item(4).getTextContent()));
        listaVehiculos.add(nuevaMoto);
        }

        }


        }
}

