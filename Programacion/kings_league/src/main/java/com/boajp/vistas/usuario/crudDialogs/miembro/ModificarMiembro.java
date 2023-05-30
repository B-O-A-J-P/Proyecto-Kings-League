package com.boajp.vistas.usuario.crudDialogs.miembro;

import javax.swing.*;
import java.awt.event.*;

public class ModificarMiembro extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombreTf;
    private JTextField apellidoTf;
    private JTextField dniTf;
    private String copiaNombre;
    private String copiaApellido;
    private String copiaDni;

    public ModificarMiembro(String nombre, String apellido, String dni) {
        copiaNombre = nombre;
        copiaApellido = apellido;
        copiaDni = dni;

        reestablecerValoresPorDefecto();
        deshabilitarCampos();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void deshabilitarCampos() {
        nombreTf.setEnabled(false);
        apellidoTf.setEnabled(false);
        dniTf.setEnabled(false);
        buttonOK.setVisible(false);
    }

    public void habilitarCampos() {
        nombreTf.setEnabled(true);
        apellidoTf.setEnabled(true);
        dniTf.setEnabled(true);
        buttonOK.setVisible(true);
    }

    public void establecerValoresPorDefecto() {
        copiaDni = dniTf.getText();
        copiaApellido = apellidoTf.getText();
        copiaNombre = nombreTf.getText();
    }

    public void reestablecerValoresPorDefecto() {
        dniTf.setText(copiaDni);
        apellidoTf.setText(copiaApellido);
        nombreTf.setText(copiaNombre);
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public String  getNombreTf() {
        return nombreTf.getText();
    }

    public String getApellidoTf() {
        return apellidoTf.getText();
    }

    public String getDniTf() {
        return dniTf.getText();
    }
}
