package com.boajp.vista.vistaEquipo;

import com.boajp.modelo.EquipoEntidad;
import com.boajp.repositorios.EquipoRepositorio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoLista extends JPanel {

    public EquipoLista() {
        try{
            //EquipoRepositorio er = new EquipoRepositorio();
        setLayout(new GridBagLayout());
        ArrayList<CartaEquipo> cartas = new ArrayList<>();
        //List<EquipoEntidad> equipos =er.seleccionarTodosLosEquipos();
        for (int x = 0; x < 12; x++) {
            cartas.add(new CartaEquipo());
           //cartas.get(x).getJlLogo().setText(String.valueOf(equipos.get(x).getLogo()));
            //cartas.get(x).getJlNombre().setText(equipos.get(x).getNombre());
            //Color colorFondo = new Color((int) (Math.random() * 0x1000000)) .brighter() ;
            cartas.get(x).getP().setBackground(Color.orange);
            add(cartas.get(x).getP(), new GridBagConstraints((x % 3) * 3, x / 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 30, 30));

        }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void main(String... args) {
        var frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new EquipoLista(), BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
