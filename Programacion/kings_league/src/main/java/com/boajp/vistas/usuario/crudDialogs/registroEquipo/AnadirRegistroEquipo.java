package com.boajp.vistas.usuario.crudDialogs.registroEquipo;

import javax.swing.*;
import java.awt.event.*;

public class AnadirRegistroEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> codigoTemporadaCb;
    private JComboBox<String> codigoEquipoCb;
    private DefaultComboBoxModel<String> codigoTemporadasModelo;
    private DefaultComboBoxModel<String> codigoEquiposModelo;

    public AnadirRegistroEquipo(String[] codigoTemporadas, String[] codigoEquipos) {
        codigoTemporadasModelo = new DefaultComboBoxModel<>(codigoTemporadas);
        codigoEquiposModelo = new DefaultComboBoxModel<>(codigoEquipos);

        codigoTemporadaCb.setModel(codigoTemporadasModelo);
        codigoEquipoCb.setModel(codigoEquiposModelo);

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

    public int getCodigoTemporadaCb() {
        return  Integer.parseInt(codigoTemporadaCb.getItemAt(codigoTemporadaCb.getSelectedIndex()));
    }

    public int getCodigoEquipoCb() {
        return Integer.parseInt(codigoEquipoCb.getItemAt(codigoEquipoCb.getSelectedIndex()));
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
