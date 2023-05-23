package com.boajp.vistas.usuario.crudDialogs.jornada;

import javax.swing.*;
import java.awt.event.*;

public class ModificarJornadaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> comboBox;
    private JTextField numeroTf;
    private JTextField fechaTf;
    private JTextField ubicacionTf;
    private String copiaNumero;
    private String copiaFecha;
    private String copiaUbicacion;
    private String copiaCodigo;
    private String[] codigos;

    public ModificarJornadaDialog(String codigo, String numero, String fecha, String ubicacion, String[] codigos) {
        copiaCodigo = codigo;
        copiaNumero = numero;
        copiaFecha = fecha;
        copiaUbicacion = ubicacion;
        this.codigos = codigos;

        numeroTf.setText(numero);
        fechaTf.setText(fecha);
        ubicacionTf.setText(ubicacion);

        comboBox.setModel(new DefaultComboBoxModel<>(codigos));
        comboBox.setSelectedIndex(encontrarIndiceCodigo(codigo));

        desabilitarCampos();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void habilitarCampos() {
        comboBox.setEnabled(true);
        numeroTf.setEnabled(true);
        fechaTf.setEnabled(true);
        ubicacionTf.setEnabled(true);
    }

    public void desabilitarCampos() {
        comboBox.setEnabled(false);
        numeroTf.setEnabled(false);
        fechaTf.setEnabled(false);
        ubicacionTf.setEnabled(false);
    }

    public void establecerValoresPorDefecto() {
        copiaCodigo = (String) comboBox.getSelectedItem();
        copiaNumero = numeroTf.getText();
        copiaFecha = fechaTf.getText();
        copiaUbicacion = ubicacionTf.getText();
    }

    public void restablecerValoresPorDefecto() {
        comboBox.setSelectedIndex(encontrarIndiceCodigo(copiaCodigo));
        numeroTf.setText(copiaNumero);
        fechaTf.setText(copiaFecha);
        ubicacionTf.setText(copiaUbicacion);
    }

    private int encontrarIndiceCodigo(String codigo) {
        for ( int x = 0; x < codigos.length; x++ ) {
            if (codigos[x].equalsIgnoreCase(codigo))
                return x;
        }
        return -1;
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

    public JComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public JTextField getNumeroTf() {
        return numeroTf;
    }

    public void setNumeroTf(JTextField numeroTf) {
        this.numeroTf = numeroTf;
    }

    public JTextField getFechaTf() {
        return fechaTf;
    }

    public void setFechaTf(JTextField fechaTf) {
        this.fechaTf = fechaTf;
    }

    public JTextField getUbicacionTf() {
        return ubicacionTf;
    }

    public void setUbicacionTf(JTextField ubicacionTf) {
        this.ubicacionTf = ubicacionTf;
    }
}
