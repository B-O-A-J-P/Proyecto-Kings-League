package com.boajp.vistas.usuario.crudDialogs.partido;

import javax.swing.*;

public class ModificarPartido extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> codigoJornadaCb;
    private JComboBox<String> faseCb;
    private JComboBox<String> equipoUnoCb;
    private JComboBox<String> equipoDosCb;
    private JTextField horaTf;
    private String copiaCodigoJornada;
    private String copiaFase;
    private String copiaEquipoUno;
    private String copiaEquipoDos;
    private String copiaHora;
    private DefaultComboBoxModel<String> codigoJornadasModelo;
    private DefaultComboBoxModel<String> codigoEquiposUnoModelo;
    private DefaultComboBoxModel<String> codigoEquiposDosModelo;
    private DefaultComboBoxModel<String> faseModelo;

    public ModificarPartido(String codigoJornada, String fase, String codigoEquipoUno, String codigoEquipoDos, String hora, String[] codigoJornadas, String[] codigoEquipos) {
        copiaCodigoJornada = codigoJornada;
        copiaFase = fase;
        copiaEquipoUno = codigoEquipoUno;
        copiaEquipoDos = codigoEquipoDos;
        copiaHora = hora;

        codigoEquiposUnoModelo = new DefaultComboBoxModel<>(codigoEquipos);
        codigoEquiposDosModelo = new DefaultComboBoxModel<>(codigoEquipos);
        codigoJornadasModelo = new DefaultComboBoxModel<>(codigoJornadas);
        faseModelo = new DefaultComboBoxModel<>(new String[]{"Regular", "Semifinal", "Final"});

        codigoJornadaCb.setModel(codigoJornadasModelo);
        equipoUnoCb.setModel(codigoEquiposUnoModelo);
        equipoDosCb.setModel(codigoEquiposDosModelo);
        faseCb.setModel(faseModelo);

        deshabilitar();
        reestablecerValoresPorDefecto();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public int getIndex(String valor, JComboBox<String> comboBox) {
        int tamano = comboBox.getItemCount();
        for ( int x = 0; x < tamano; x++) {
            if (comboBox.getItemAt(x).equalsIgnoreCase(valor))
                return x;
        }
        return 0;
    }
    
    public void establecerValoresPorDefecto() {
        copiaCodigoJornada = codigoJornadaCb.getItemAt(codigoJornadaCb.getSelectedIndex());
        copiaHora = horaTf.getText();
        copiaFase = faseCb.getItemAt(faseCb.getSelectedIndex());
        copiaEquipoUno = equipoUnoCb.getItemAt(equipoUnoCb.getSelectedIndex());
        copiaEquipoDos = equipoDosCb.getItemAt(equipoDosCb.getSelectedIndex());
    }

    public void reestablecerValoresPorDefecto() {
        codigoJornadaCb.setSelectedIndex(getIndex(copiaCodigoJornada, codigoJornadaCb));
        horaTf.setText(copiaHora);
        if (copiaFase.equalsIgnoreCase("f"))
            faseCb.setSelectedIndex(2);
        else if (copiaFase.equalsIgnoreCase("s"))
            faseCb.setSelectedIndex(1);
        else
            faseCb.setSelectedIndex(0);
        equipoUnoCb.setSelectedIndex(getIndex(copiaEquipoUno, equipoUnoCb));
        equipoDosCb.setSelectedIndex(getIndex(copiaEquipoDos, equipoDosCb));
    }
    
    public void deshabilitar() {
        codigoJornadaCb.setEnabled(false);
        horaTf.setEnabled(false);
        faseCb.setEnabled(false);
        equipoUnoCb.setEnabled(false);
        equipoDosCb.setEnabled(false);
        buttonOK.setVisible(false);
    }
    
    public void habilitarCampos() {
        codigoJornadaCb.setEnabled(true);
        horaTf.setEnabled(true);
        faseCb.setEnabled(true);
        equipoUnoCb.setEnabled(true);
        equipoDosCb.setEnabled(true);
        buttonOK.setVisible(true);
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
