package com.boajp.vistas.usuario.crudDialogs.jugador;

import javax.swing.*;

public class ModificarJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombreTf;
    private JTextField apellidoTf;
    private JTextField dniTf;
    private JComboBox<String> pieCb;
    private JSpinner alturaSp;
    private String copiaNombre;
    private String copiaApellido;
    private String copiaDni;
    private String copiaPie;
    private int copiaAltura;

    public ModificarJugador(String nombre, String apellido, String dni, String pie, int altura) {
        deshabilitarCampos();
        copiaNombre = nombre;
        copiaApellido = apellido;
        copiaDni = dni;
        copiaPie = pie;
        copiaAltura = altura;

        nombreTf.setText(nombre);
        apellidoTf.setText(apellido);
        dniTf.setText(dni);
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(new String[]{"izquierdo", "derecho"});
        pieCb.setModel(modelo);

        if (pie.equalsIgnoreCase("izquierdo"))
            pieCb.setSelectedIndex(0);
        else
            pieCb.setSelectedIndex(1);

        alturaSp.setValue(altura);

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
        pieCb.setEnabled(false);
        alturaSp.setEnabled(false);
    }

    public void habilitarCampos() {
        nombreTf.setEnabled(true);
        apellidoTf.setEnabled(true);
        dniTf.setEnabled(true);
        pieCb.setEnabled(true);
        alturaSp.setEnabled(true);
    }

    public void restablecerValoresPorDefecto() {
        nombreTf.setText(copiaNombre);
        apellidoTf.setText(copiaApellido);
        dniTf.setText(copiaDni);
        if (copiaPie.equalsIgnoreCase("izquierdo"))
            pieCb.setSelectedIndex(0);
        else
            pieCb.setSelectedIndex(1);
        alturaSp.setValue(copiaAltura);
    }

    public void establecerValoresPorDefecto() {
        copiaNombre = nombreTf.getText();
        copiaApellido = apellidoTf.getText();
        copiaDni = dniTf.getText();
        copiaPie = pieCb.getItemAt(pieCb.getSelectedIndex());
        copiaAltura = (Integer) alturaSp.getValue();
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

    public JTextField getNombreTf() {
        return nombreTf;
    }

    public void setNombreTf(JTextField nombreTf) {
        this.nombreTf = nombreTf;
    }

    public JTextField getApellidoTf() {
        return apellidoTf;
    }

    public void setApellidoTf(JTextField apellidoTf) {
        this.apellidoTf = apellidoTf;
    }

    public JTextField getDniTf() {
        return dniTf;
    }

    public void setDniTf(JTextField dniTf) {
        this.dniTf = dniTf;
    }

    public JComboBox<String> getPieCb() {
        return pieCb;
    }

    public void setPieCb(JComboBox<String> pieCb) {
        this.pieCb = pieCb;
    }

    public JSpinner getAlturaSp() {
        return alturaSp;
    }

    public void setAlturaSp(JSpinner alturaSp) {
        this.alturaSp = alturaSp;
    }

    public String getCopiaNombre() {
        return copiaNombre;
    }

    public void setCopiaNombre(String copiaNombre) {
        this.copiaNombre = copiaNombre;
    }

    public String getCopiaApellido() {
        return copiaApellido;
    }

    public void setCopiaApellido(String copiaApellido) {
        this.copiaApellido = copiaApellido;
    }

    public String getCopiaDni() {
        return copiaDni;
    }

    public void setCopiaDni(String copiaDni) {
        this.copiaDni = copiaDni;
    }

    public String getCopiaPie() {
        return copiaPie;
    }

    public void setCopiaPie(String copiaPie) {
        this.copiaPie = copiaPie;
    }

    public int getCopiaAltura() {
        return copiaAltura;
    }

    public void setCopiaAltura(int copiaAltura) {
        this.copiaAltura = copiaAltura;
    }
}
