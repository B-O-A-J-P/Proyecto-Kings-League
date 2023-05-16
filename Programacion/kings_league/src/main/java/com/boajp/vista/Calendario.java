package com.boajp.vista;

import com.boajp.utilidades.EstilosDeVistas;
import com.boajp.vista.carta.CalendarioTabla;
import com.boajp.vista.carta.CuerpoAbstracto;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Calendario extends JPanel {
    public Calendario(HashMap<String, CuerpoAbstracto> calendario) {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new GridLayout(calendario.size(), 1));
        for (String fecha: calendario.keySet()) {
            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(new JLabel(fecha));
            panel.add(calendario.get(fecha));
            add(panel);
        }
    }

    public static void main(String... args) {
        JLabel[][] list = new JLabel[6][3];
        for (int x = 0; x < 6; x++) {
            list[x] =
                    new JLabel[]{new JLabel("Equipo uno"), new JLabel("vs"), new JLabel("Equipod dos")};
        }
        CalendarioTabla calendarioTabla = new CalendarioTabla(list);
        HashMap<String, CuerpoAbstracto> calendario = new HashMap<>();
        for (int x = 0; x < 6; x++) {
            calendario.put(String.valueOf(x) + " del 5 de 2023", calendarioTabla);
        }
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(new Calendario(calendario));
        frame.add(scrollPane);


        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
