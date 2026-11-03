package com.daniela.zapateriamvc.gui;

import com.daniela.zapateriamvc.base.Zapato;
import com.daniela.zapateriamvc.base.ZapatoDeportivo;
import com.daniela.zapateriamvc.base.ZapatoFormal;
import com.daniela.zapateriamvc.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
            System.out.println("No existe fichero de configuracion " + e.getMessage());
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
                vista.fechaIngresoDPicker.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        vista.suelaMaterialTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.tallaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.fechaIngresoDPicker.setText(null);
        vista.tallaTxt.requestFocus();
    }

    public void refrescar() {
        vista.dlmzapato.clear();
        for (Zapato unZapato : modelo.obtenerZapatos()) {
            vista.dlmzapato.addElement(unZapato);
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.eliminarButton.addActionListener(listener);
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
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }
    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("zapatos.conf"), "Datos configuracion zapatos");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios: \n" +
                            "Marca \n Modelo \n Talla \n Fecha de ingreso \n "
                            + vista.suelaMaterialLbl.getText());
                    break;
                }
                if (vista.zapatoDeportivoRadioButton.isSelected()) {
                    modelo.altaZapatoDeportivo(vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(),
                            Integer.parseInt(vista.tallaTxt.getText()),
                            vista.fechaIngresoDPicker.getDate(),
                            vista.suelaMaterialTxt.getText());
                } else {
                    modelo.altaZapatoFormal(vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(),
                            Integer.parseInt(vista.tallaTxt.getText()),
                            vista.fechaIngresoDPicker.getDate(),
                            vista.suelaMaterialTxt.getText());
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML", "xml");
                int opcion = selectorFichero.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML", "xml");
                int opcion2 = selectorFichero2.showSaveDialog(null);
                if (opcion2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportar(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Zapato Formal":
                vista.suelaMaterialLbl.setText("Material:");
                break;
            case "Zapato Deportivo":
                vista.suelaMaterialLbl.setText("Tipo de Suela:");
                break;
            case "Eliminar":
                try {
                    Zapato unZapato = (Zapato) vista.list1.getSelectedValue();
                    modelo.eliminar(unZapato);
                    limpiarCampos();
                    refrescar();
                } catch (ArrayIndexOutOfBoundsException ae) {
                    Util.mensajeError("Selecciona un zapato a eliminar");
                }
                break;
        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Zapato zapatoSeleccionado = (Zapato) vista.list1.getSelectedValue();
            vista.marcaTxt.setText(zapatoSeleccionado.getMarca());
            vista.modeloTxt.setText(zapatoSeleccionado.getModelo());
            vista.tallaTxt.setText(String.valueOf(zapatoSeleccionado.getTalla()));
            vista.fechaIngresoDPicker.setDate(zapatoSeleccionado.getFechaIngreso());
            if (zapatoSeleccionado instanceof ZapatoDeportivo) {
                vista.suelaMaterialTxt.setText(((ZapatoDeportivo) zapatoSeleccionado).getTipoSuela());
                vista.zapatoDeportivoRadioButton.doClick();

                ImageIcon icono = new ImageIcon("img/deportivo.png");
                System.out.println("Ancho de la imagen: " + icono.getIconWidth());
                Image imagenEscalada = icono.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
                vista.imagenZapatoLabel.setIcon(new ImageIcon(imagenEscalada));
            } else {
                vista.suelaMaterialTxt.setText(((ZapatoFormal) zapatoSeleccionado).getMaterial());
                vista.zapatoFormalRadioButton.doClick();

                ImageIcon icono = new ImageIcon("img/formal.png");
                System.out.println("Ancho de la imagen: " + icono.getIconWidth());
                Image imagenEscalada = icono.getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH);
                vista.imagenZapatoLabel.setIcon(new ImageIcon(imagenEscalada));
            }
        }
    }
    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}