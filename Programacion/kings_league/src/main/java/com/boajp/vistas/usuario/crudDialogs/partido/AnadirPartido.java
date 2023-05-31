package com.boajp.vistas.usuario.crudDialogs.partido;

import javax.swing.*;
import java.awt.event.*;

public class AnadirPartido extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> codigoJornadaCb;
    private JComboBox<String> faseCb;
    private JComboBox<String> equipoUnoCb;
    private JComboBox<String> equipoDosCb;
    private JTextField horaTf;
    private DefaultComboBoxModel<String> faseModelo;
    private DefaultComboBoxModel<String> codigoJornadasModelo;
    private DefaultComboBoxModel<String> codigoEquiposModeloUno;
    private DefaultComboBoxModel<String> codigoEquiposModeloDos;

    public AnadirPartido(String[] codigoJornadas, String[] codigoEquipos) {
        codigoEquiposModeloUno = new DefaultComboBoxModel<>(codigoEquipos);
        codigoEquiposModeloDos = new DefaultComboBoxModel<>(codigoEquipos);
        codigoJornadasModelo = new DefaultComboBoxModel<>(codigoJornadas);
        faseModelo = new DefaultComboBoxModel<>(new String[]{"Regular", "Semifinal", "Final"});
        codigoJornadaCb.setModel(codigoJornadasModelo);
        equipoUnoCb.setModel(codigoEquiposModeloUno);
        equipoDosCb.setModel(codigoEquiposModeloDos);
        faseCb.setModel(faseModelo);
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

    public int getCodigoJornadaCb() {
        return Integer.parseInt(codigoJornadaCb.getItemAt(codigoJornadaCb.getSelectedIndex()));
    }

    public String getHoraTf() {
        return horaTf.getText();
    }

    public String getFaseCb() {
        if(faseCb.getItemAt(faseCb.getSelectedIndex()).equalsIgnoreCase("final")) {
            return "f";
        } else if (faseCb.getItemAt(faseCb.getSelectedIndex()).equalsIgnoreCase("semifinal")) {
            return "s";
        } else {
            return "r";
        }
    }

    public int getEquipoUnoCb() {
        return Integer.parseInt(equipoUnoCb.getItemAt(equipoUnoCb.getSelectedIndex()));
    }

    public int getEquipoDosCb() {
        return Integer.parseInt(equipoDosCb.getItemAt(equipoDosCb.getSelectedIndex()));
    }

    private void onCancel() {
        dispose();
    }

}
