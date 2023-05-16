package com.boajp.vista;

import com.boajp.modelo.JugadorEntidad;
import com.boajp.utilidades.EstilosDeVistas;
import com.boajp.vista.carta.CartaAbstracta;
import com.boajp.vista.carta.CartaJugador;
import com.boajp.vista.carta.GridDeCartas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelJugadores extends JPanel {
    private ArrayList<CartaAbstracta> cartas;
    public PanelJugadores(ArrayList<JugadorEntidad> jugadores, JScrollPane scrollPane) {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new BorderLayout());

        cartas = new ArrayList<>();
        for (JugadorEntidad jugador: jugadores) {
            cartas.add(new CartaJugador(jugador));
        }

        GridDeCartas grid = new GridDeCartas(cartas, scrollPane);
        add(grid);
    }

    public static void main(String... args) {
        ArrayList<JugadorEntidad> jugadores = new ArrayList<>();
        for (int x = 0; x < 100; x++) {
            JugadorEntidad jugadorEntidad = new JugadorEntidad();
            jugadorEntidad.setNombre("Nombre" + x);
            jugadorEntidad.setApellido("Apellido" + x);
            jugadores.add(jugadorEntidad);
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(new PanelJugadores(jugadores, scrollPane));
            frame.add(scrollPane);

            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
