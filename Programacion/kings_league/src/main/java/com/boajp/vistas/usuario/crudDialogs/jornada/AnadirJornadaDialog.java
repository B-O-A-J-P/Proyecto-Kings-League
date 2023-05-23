package com.boajp.vistas.usuario.crudDialogs.jornada;

import javax.swing.*;
import java.awt.event.*;

public class AnadirJornadaDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> comboBox;
    private JTextField numeroTf;
    private JTextField fechaTf;
    private JTextField ubicacionTf;

    public AnadirJornadaDialog() {
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
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

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        AnadirJornadaDialog dialog = new AnadirJornadaDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
