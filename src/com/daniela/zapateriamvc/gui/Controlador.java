package com.daniela.zapateriamvc.gui;

import com.daniela.zapateriamvc.base.Zapato;

import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Controlador implements ActionListener, ListSelectionListener, WindowListener {
    private Vista vista;
    private Modelo modelo;
    private File ultimaRutaExportada;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe fichero de configuracion "+e.getMessage());
        }
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    private boolean hayCamposVacios() {
        if (vista.suelaMaterialTxt.getText().isEmpty() ||
                vista.marcaTxt.getText().isEmpty() ||
                vista.modeloTxt.getText().isEmpty() ||
                vista.tallaTxt.getText().isEmpty() ||
                vista.fechaIngresoDPicker.getText().isEmpty())

            return true;
        {

            return false;
        }
    }

    private void limpiarCampos(){
        vista.suelaMaterialTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.tallaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.fechaIngresoDPicker.setText(null);
        vista.tallaTxt.requestFocus();
    }

    public void refrescar(){
        vista.dlmzapato.clear();
        for(Zapato unZapato: modelo.obtenerZapatos()){
            vista.dlmzapato.addElement(unZapato);
        }
    }


    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.zapatoDeportivoRadioButton.addActionListener(listener);
        vista.zapatoFormalRadioButton.addActionListener(listener);
    }
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("zapatos.conf"));
        ultimaRutaExportada= new File (configuracion.getProperty("ultimaRutaExportada"));
    }
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }
    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("vehiculos.conf"), "Datos configuracion vehiculos");
    }
}
