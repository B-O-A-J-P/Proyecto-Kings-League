package com.boajp.vistas.usuario.crudDialogs.contratoJugador;

import com.boajp.utilidades.FechaUtilidades;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;

public class AnadirContratoJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> codigoEquipoCb;
    private JComboBox<String> codigoJugadorCb;
    private JTextField salarioTf;
    private JTextField clausulaTf;
    private JTextField fechaInicioTf;
    private JTextField fechaFinTf;
    private DefaultComboBoxModel<String> codigoEquiposModelo;
    private DefaultComboBoxModel<String> codigoJugadoresModelo;

    public AnadirContratoJugador(String[] codigoEquipos, String[] codigoJugadores) {
        codigoEquiposModelo = new DefaultComboBoxModel<>(codigoEquipos);
        codigoJugadoresModelo = new DefaultComboBoxModel<>(codigoJugadores);

        codigoEquipoCb.setModel(codigoEquiposModelo);
        codigoJugadorCb.setModel(codigoJugadoresModelo);

        buttonCancel.addActionListener(new ActionListener() {
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
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
