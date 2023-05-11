package com.boajp.vista.VistaJugadores;


import javax.swing.*;
import java.awt.*;


public class JugadoresDraft {

    JScrollPane jScrollPane = new JScrollPane();
    JPanel jPanel = new JPanel();

    public JugadoresDraft() {
        jPanel.setLayout(new GridBagLayout());

        for (int x = 0; x < 120; x++) {
            jPanel.add(new CartaJugador("234", null, "Ander Mora", "Derecho", 190),
                    new GridBagConstraints(
                            (x % 5) * 2,
                            x / 5,
                            1,
                            1,
                            1,
                            1,
                            GridBagConstraints.CENTER,
                            GridBagConstraints.BOTH,
                            new Insets(10, 10, 10, 10),
                            0, 0));
            //  cartas.get(x).getlNombre().setText(jugadores.get(x).getNombre());
            // cartas.get(x).getlApellido().setText(jugadores.get(x).getApellido());
            // cartas.get(x).getlAltura().setText(String.valueOf(jugadores.get(x).getAltura()));
            //  cartas.get(x).getlPie().setText(String.valueOf(jugadores.get(x).getPie()));
            // cartas.get(x).getlCodJudador().setText(String.valueOf(jugadores.get(x).getCodJugador()));
        }

        jScrollPane.setViewportView(jPanel); // set the view port to the panel
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.add(new JugadoresDraft().getjScrollPane());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}


