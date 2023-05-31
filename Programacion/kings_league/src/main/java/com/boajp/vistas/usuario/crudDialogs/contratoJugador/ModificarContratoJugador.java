package com.boajp.vistas.usuario.crudDialogs.contratoJugador;

import com.boajp.utilidades.FechaUtilidades;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;

public class ModificarContratoJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> codigoEquipoCb;
    private JComboBox<String> codigoJugadorCb;
    private JTextField salarioTf;
    private JTextField clausulaTf;
    private JTextField fechaInicioTf;
    private JTextField fechaFinTf;
    private String copiaCodigoEquipo;
    private String copiaCodigoJugador;
    private String copiaSalario;
    private String copiaClausula;
    private String copiaFechaInicio;
    private String copiaFechaFin;
    private DefaultComboBoxModel<String> codigoEquiposModelo;
    private DefaultComboBoxModel<String> codigoJugadoresModelo;


    public ModificarContratoJugador(String codigoEquipo, String codigoJugador, String salario, String clausula, String fechaInicio, String fechaFin, String[] codigoEquipos, String[] codigoJugadores) {
        copiaCodigoEquipo = codigoEquipo;
        copiaCodigoJugador = codigoJugador;
        copiaSalario = salario;
        copiaClausula = clausula;
        copiaFechaInicio = fechaInicio;
        copiaFechaFin = fechaFin;

        codigoEquiposModelo = new DefaultComboBoxModel<>(codigoEquipos);
        codigoJugadoresModelo = new DefaultComboBoxModel<>(codigoJugadores);
        codigoEquipoCb.setModel(codigoEquiposModelo);
        codigoJugadorCb.setModel(codigoJugadoresModelo);

        deshabilitarCampos();
        reestablecerValoresPorDefecto();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public int getIndex(String valor, JComboBox<String> comboBox) {
        int tamano = comboBox.getItemCount();
        for ( int x = 0; x < tamano; x++) {
            if (comboBox.getItemAt(x).equalsIgnoreCase(valor))
                return x;
        }
        return 0;
    }

    public void establecerValoresPorDefecto() {
        copiaCodigoEquipo = codigoEquipoCb.getItemAt(codigoEquipoCb.getSelectedIndex());
        copiaCodigoJugador = codigoJugadorCb.getItemAt(codigoJugadorCb.getSelectedIndex());
        copiaSalario = salarioTf.getText();
        copiaClausula = clausulaTf.getText();
        copiaFechaInicio = fechaInicioTf.getText();
        copiaFechaFin = fechaFinTf.getText();
    }

    public void reestablecerValoresPorDefecto() {
        codigoEquipoCb.setSelectedIndex(getIndex(copiaCodigoEquipo, codigoEquipoCb));
        codigoJugadorCb.setSelectedIndex(getIndex(copiaCodigoJugador, codigoJugadorCb));
        salarioTf.setText(copiaSalario);
        clausulaTf.setText(copiaClausula);
        fechaInicioTf.setText(copiaFechaInicio);
        fechaFinTf.setText(copiaFechaFin);
    }

    public void deshabilitarCampos() {
        codigoEquipoCb.setEnabled(false);
        codigoJugadorCb.setEnabled(false);
        salarioTf.setEnabled(false);
        clausulaTf.setEnabled(false);
        fechaInicioTf.setEnabled(false);
        fechaFinTf.setEnabled(false);
        buttonOK.setVisible(false);
    }

    public void habilitarCampos(){
        codigoEquipoCb.setEnabled(true);
        codigoJugadorCb.setEnabled(true);
        salarioTf.setEnabled(true);
        clausulaTf.setEnabled(true);
        fechaInicioTf.setEnabled(true);
        fechaFinTf.setEnabled(true);
        buttonOK.setVisible(true);
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public int getCodigoEquipoCb() {
        return Integer.parseInt(codigoEquipoCb.getItemAt(codigoEquipoCb.getSelectedIndex()));
    }

    public int getCodigoJugadorCb() {
        return Integer.parseInt(codigoJugadorCb.getItemAt(codigoJugadorCb.getSelectedIndex()));
    }

    public int getSalarioTf() {
        return Integer.parseInt(salarioTf.getText());
    }

    public int getClausulaTf() {
        return Integer.parseInt(clausulaTf.getText());
    }

    public LocalDate getFechaInicioTf() {
        return FechaUtilidades.stringToFecha(fechaInicioTf.getText());
    }

    public LocalDate getFechaFinTf() {
        return FechaUtilidades.stringToFecha(fechaFinTf.getText());
    }

    public String getCopiaCodigoEquipo() {
        return copiaCodigoEquipo;
    }

    public String getCopiaCodigoJugador() {
        return copiaCodigoJugador;
    }

    public String getCopiaSalario() {
        return copiaSalario;
    }

    public String getCopiaClausula() {
        return copiaClausula;
    }

    public String getCopiaFechaInicio() {
        return copiaFechaInicio;
    }

    public String getCopiaFechaFin() {
        return copiaFechaFin;
    }

    public DefaultComboBoxModel<String> getCodigoEquiposModelo() {
        return codigoEquiposModelo;
    }

    public DefaultComboBoxModel<String> getCodigoJugadoresModelo() {
        return codigoJugadoresModelo;
    }
}
