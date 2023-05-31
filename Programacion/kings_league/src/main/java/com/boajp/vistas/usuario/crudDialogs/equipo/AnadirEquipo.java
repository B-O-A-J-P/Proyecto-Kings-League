package com.boajp.vistas.usuario.crudDialogs.equipo;

import javax.swing.*;
import java.awt.event.*;

public class AnadirEquipo extends JDialog {
    private JPanel contentPane;
    private JButton bAceptar;
    private JButton bCancelar;
    private JTextField tfNombre;
    private JTextField presupuestoTf;

    public AnadirEquipo() {


        bAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        bCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bAceptar);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setbAceptar(JButton bAceptar) {
        this.bAceptar = bAceptar;
    }

    public void setbCancelar(JButton bCancelar) {
        this.bCancelar = bCancelar;
    }

    public void setTfNombre(JTextField tfNombre) {
        this.tfNombre = tfNombre;
    }


    public JButton getbAceptar() {
        return bAceptar;
    }

    public JButton getbCancelar() {
        return bCancelar;
    }

    public String getTfNombre() {
        return tfNombre.getText();
    }

    public int getPresupuestoTf() {
        return Integer.parseInt(presupuestoTf.getText());
    }
}
