package com.boajp.vistas.usuario.crudDialogs.equipo;

import javax.swing.*;
import java.awt.event.*;

public class ModificarEquipo extends JDialog {
    private JPanel contentPane;
    private JButton bAceptar;
    private JButton bModificar;
    private JTextField tfNombre;
    private JTextField tfLogo;

    private static String copiaNombre;
    private static String copiaLogo;

    public ModificarEquipo(String nombre, String logo) {
        copiaNombre = nombre;
        copiaLogo = logo;

        tfNombre.setText(nombre);
        tfLogo.setText(logo);

        setContentPane(contentPane);
        setSize(400,400);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(bAceptar);
    }

    public void desabilitarCampos() {
        tfNombre.setEditable(false);
        tfLogo.setEditable(false);
    }
    public void habilitarCampos() {
        tfNombre.setEditable(true);
        tfLogo.setEditable(true);
    }

    public void restablecerValoresPorDefecto() {
        tfNombre.setText(copiaNombre);
        tfLogo.setText(copiaLogo);
    }

    public void establecerValoresPorDefecto() {
        copiaNombre = tfNombre.getText();
        copiaLogo = tfLogo.getText();
    }



    public JButton getbAceptar() {
        return bAceptar;
    }

    public JButton getbModificar() {
        return bModificar;
    }

    public JTextField getTfNombre() {
        return tfNombre;
    }

    public JTextField getTfLogo() {
        return tfLogo;
    }

    public static String getCopiaNombre() {
        return copiaNombre;
    }

    public static String getCopiaLogo() {
        return copiaLogo;
    }
}
