package com.boajp.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class clasificacion {
    private JPanel pClasificacion;

    public clasificacion() {

            String[] columnNames = {"Posicion", "Logo", "Equipo", "Pts", "Suma Goles"};
            Object[][] cells =
                    {
                            {"Mercury", 2440.0, 0, false, Color.YELLOW},
                            {"Venus", 6052.0, 0, false, Color.YELLOW},
                            {"Earth", 71492.0, 16, true, Color.BLUE},
                            {"Mars", 3397.0, 2, false, Color.RED},
                            {"Jupiter", 71492.0, 16, true, Color.ORANGE},
                            {"Saturn", 60268.0, 18, true, Color.ORANGE},
                            {"Uranus", 25559.0, 17, true, Color.BLUE},
                            {"Neptune", 24766.0, 8, true, Color.BLUE},
                            {"Pluto", 1137.0, 1, false, Color.BLACK}
                    };


        DefaultTableModel modelo = new DefaultTableModel(null, columnNames);


                JTable table = new JTable (modelo);



                pClasificacion.add(table.getTableHeader(),BorderLayout.NORTH);
                pClasificacion.add(table, BorderLayout.CENTER);
        pClasificacion.add(table);







    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clasificacion");
        frame.setContentPane(new clasificacion().pClasificacion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
