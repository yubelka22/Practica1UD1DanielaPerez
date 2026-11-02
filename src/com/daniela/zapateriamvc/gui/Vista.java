package com.daniela.zapateriamvc.gui;

import com.daniela.zapateriamvc.base.Zapato;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Vista {
    private JPanel panel1;
    private JRadioButton zapatoDeportivoRadioButton;
    private JRadioButton zapatoFormalRadioButton;
    private JTextField marcaTxt;
    private JTextField modeloTxt;
    private JTextField tallaTxt;
    private JLabel suelaMaterialLbl;
    private JTextField suelaMaterialTxt;
    private JButton nuevoButton;
    private JButton importarButton;
    private JButton exportarButton;
    private JButton eliminarButton;
    private JList list1;
    private DatePicker fechaIngresoDPicker;

    //creados por mi
    public JFrame frame;
    public DefaultListModel<Zapato> dlmvehiculo;

    public Vista() {
        frame = new JFrame("Zapatos MVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
        //centrar
        frame.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        dlmvehiculo= new DefaultListModel<Zapato>();
        list1.setModel(dlmzapato);
    }
}
