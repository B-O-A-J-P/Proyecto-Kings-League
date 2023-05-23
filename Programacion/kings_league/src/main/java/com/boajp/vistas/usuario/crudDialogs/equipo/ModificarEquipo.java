package com.boajp.vistas.usuario.crudDialogs.equipo;

import javax.swing.*;

public class ModificarEquipo extends JDialog {
    private JPanel contentPane;
    private JButton bAceptar;
    private JButton bModificar;
    private JTextField tfNombre;
    private String copiaNombre;

    public ModificarEquipo(String nombre, String presupuesto) {
        copiaNombre = nombre;

        tfNombre.setText(nombre);

        desabilitarCampos();

        setContentPane(contentPane);
        setSize(400,400);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(bAceptar);
    }

    public void desabilitarCampos() {
        tfNombre.setEditable(false);
    }
    public void habilitarCampos() {
        tfNombre.setEditable(true);
    }

    public void restablecerValoresPorDefecto() {
        tfNombre.setText(copiaNombre);
    }

    public void establecerValoresPorDefecto() {
        copiaNombre = tfNombre.getText();
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

    public void setbAceptar(JButton bAceptar) {
        this.bAceptar = bAceptar;
    }

    public void setbModificar(JButton bModificar) {
        this.bModificar = bModificar;
    }

    public void setTfNombre(JTextField tfNombre) {
        this.tfNombre = tfNombre;
    }


    public String getCopiaNombre() {
        return copiaNombre;
    }

    public void setCopiaNombre(String copiaNombre) {
        this.copiaNombre = copiaNombre;
    }

}
