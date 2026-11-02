package com.daniela.zapateriamvc.gui;

import com.daniela.zapateriamvc.base.Zapato;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Vista {
    private JPanel panel1;
    public JRadioButton zapatoDeportivoRadioButton;
    public JRadioButton zapatoFormalRadioButton;
    public JTextField marcaTxt;
    public JTextField modeloTxt;
    public JTextField tallaTxt;
    public JLabel suelaMaterialLbl;
    public JTextField suelaMaterialTxt;
    public JButton nuevoButton;
    public JButton importarButton;
    public JButton exportarButton;
    public JButton eliminarButton;
    public JList list1;
    public DatePicker fechaIngresoDPicker;

    //creados por mi
    public JFrame frame;
    public DefaultListModel<Zapato> dlmzapato;

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
        dlmzapato= new DefaultListModel<Zapato>();
        list1.setModel(dlmzapato);
    }
}
