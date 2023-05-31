package com.boajp.vistas.usuario.crudDialogs.contratoMiembro;

import com.boajp.utilidades.FechaUtilidades;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;

public class AnadirContratoMiembro extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> codigoEquipoCb;
    private JComboBox<String> codigoJugadorCb;
    private JTextField fechaEntradaTf;
    private JTextField fechaSalidaTf;
    private JComboBox<String> funcionCb;
    private DefaultComboBoxModel<String> codigoEquiposModelo;
    private DefaultComboBoxModel<String> codigoMiembrosModelo;
    private DefaultComboBoxModel<String> funcionesModelo;

    public AnadirContratoMiembro(String[] codigoEquipos, String[] codigoMiembros) {
        codigoEquiposModelo = new DefaultComboBoxModel<>(codigoEquipos);
        codigoMiembrosModelo = new DefaultComboBoxModel<>(codigoMiembros);
        funcionesModelo = new DefaultComboBoxModel<>(new String[]{"Presidente", "Staff", "Entrenador"});

        codigoEquipoCb.setModel(codigoEquiposModelo);
        codigoJugadorCb.setModel(codigoMiembrosModelo);
        funcionCb.setModel(funcionesModelo);

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

    private void onCancel() {
        // add your code here if necessary
        dispose();
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

    public int getCodigoMiembro() {
        return Integer.parseInt(codigoJugadorCb.getItemAt(codigoJugadorCb.getSelectedIndex()));
    }

    public LocalDate getFechaEntradaTf() {
        return FechaUtilidades.stringToFecha(fechaEntradaTf.getText());
    }

    public LocalDate getFechaSalidaTf() {
        return FechaUtilidades.stringToFecha(fechaSalidaTf.getText());
    }

    public String getFuncionCb() {
        if (funcionCb.getItemAt(funcionCb.getSelectedIndex()).equalsIgnoreCase("presidente"))
            return "p";
        else if (funcionCb.getItemAt(funcionCb.getSelectedIndex()).equalsIgnoreCase("staff"))
            return "s";
        else
            return "e";
    }

    public DefaultComboBoxModel<String> getCodigoEquiposModelo() {
        return codigoEquiposModelo;
    }

    public DefaultComboBoxModel<String> getCodigoMiembrosModelo() {
        return codigoMiembrosModelo;
    }

    public DefaultComboBoxModel<String> getFuncionesModelo() {
        return funcionesModelo;
    }
}
