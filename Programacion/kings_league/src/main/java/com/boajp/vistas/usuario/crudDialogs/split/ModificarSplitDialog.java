package com.boajp.vistas.usuario.crudDialogs.split;

import javax.swing.*;

public class ModificarSplitDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField fechaDeInicioTf;
    private JTextField fechaFinTf;
    private JComboBox comboBox;
    private JTextField nombreTf;
    private String copiaNombre;
    private String copiaFechaInicio;
    private String copiaFechaFin;
    private String copiaCodigo;
    private String[] codigos;
    private DefaultComboBoxModel<String> modelo;


    public ModificarSplitDialog(String nombre, String fechaInicio, String fechaFin, String codigo, String[] codigos) {
        modelo = new DefaultComboBoxModel<>(codigos);
        comboBox.setModel(modelo);
        copiaNombre = nombre;
        copiaFechaInicio = fechaInicio;
        copiaFechaFin = fechaFin;
        copiaCodigo = codigo;
        this.codigos = codigos;
        nombreTf.setText(nombre);
        fechaDeInicioTf.setText(fechaInicio);
        fechaFinTf.setText(fechaFin);
        comboBox.setSelectedIndex(encontrarIndiceCodigo(codigo));

        deshabilitarCampos();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public JTextField getNombreTf() {
        return nombreTf;
    }

    public void setNombreTf(JTextField nombreTf) {
        this.nombreTf = nombreTf;
    }

    public void deshabilitarCampos() {
        nombreTf.setEnabled(false);
        fechaDeInicioTf.setEnabled(false);
        fechaFinTf.setEnabled(false);
        comboBox.setEnabled(false);
    }

    public void habilitarCampos() {
        nombreTf.setEnabled(true);
        fechaDeInicioTf.setEnabled(true);
        fechaFinTf.setEnabled(true);
        comboBox.setEnabled(true);
    }

    private int encontrarIndiceCodigo(String codigo) {
        for ( int x = 0; x < codigos.length; x++ ) {
            if (codigos[x].equalsIgnoreCase(codigo))
                return x;
        }
        return -1;
    }

    public void restablecerValoresPorDefecto() {
        nombreTf.setText(copiaNombre);
        fechaDeInicioTf.setText(copiaFechaInicio);
        fechaFinTf.setText(copiaFechaFin);
        comboBox.setSelectedIndex(encontrarIndiceCodigo(copiaCodigo));
    }

    public void establecerValoresPorDefecto() {
        copiaNombre = nombreTf.getText();
        copiaFechaInicio = fechaDeInicioTf.getText();
        copiaFechaFin = fechaFinTf.getText();
        copiaCodigo = (String) comboBox.getSelectedItem();
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public void setButtonOK(JButton buttonOK) {
        this.buttonOK = buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonCancel(JButton buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public JTextField getFechaDeInicioTf() {
        return fechaDeInicioTf;
    }

    public void setFechaDeInicioTf(JTextField fechaDeInicioTf) {
        this.fechaDeInicioTf = fechaDeInicioTf;
    }

    public JTextField getFechaFinTf() {
        return fechaFinTf;
    }

    public void setFechaFinTf(JTextField fechaFinTf) {
        this.fechaFinTf = fechaFinTf;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }
}
