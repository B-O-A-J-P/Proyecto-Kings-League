package com.boajp.vistas.usuario.crudDialogs.equipo;

import javax.swing.*;

public class ModificarEquipo extends JDialog {
    private JPanel contentPane;
    private JButton bAceptar;
    private JButton bModificar;
    private JTextField tfNombre;
    private JTextField presupuestoTf;
    private String copiaNombre;
    private String copiaPresupuesto;

    public ModificarEquipo(String nombre, String presupuesto) {
        copiaNombre = nombre;
        copiaPresupuesto = presupuesto;

        tfNombre.setText(nombre);
        presupuestoTf.setText(presupuesto);

        desabilitarCampos();

        setContentPane(contentPane);
        setSize(400,400);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(bAceptar);
    }

    public void desabilitarCampos() {
        tfNombre.setEnabled(false);
        presupuestoTf.setEnabled(false);
    }
    public void habilitarCampos() {
        tfNombre.setEnabled(true);
        presupuestoTf.setEnabled(true);
    }

    public void restablecerValoresPorDefecto() {
        tfNombre.setText(copiaNombre);
        presupuestoTf.setText(copiaPresupuesto);
    }

    public void establecerValoresPorDefecto() {
        copiaNombre = tfNombre.getText();
        copiaPresupuesto = presupuestoTf.getText();
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

    public int getPresupuestoTf() {
        return Integer.parseInt(presupuestoTf.getText());
    }

    public String getCopiaNombre() {
        return copiaNombre;
    }

    public void setCopiaNombre(String copiaNombre) {
        this.copiaNombre = copiaNombre;
    }

}
