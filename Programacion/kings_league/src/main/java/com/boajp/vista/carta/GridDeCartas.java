package com.boajp.vista.carta;

import com.boajp.utilidades.EstilosDeVistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class GridDeCartas extends JPanel {
    private final ArrayList<CartaAbstracta> CARTAS;

    public GridDeCartas(ArrayList<CartaAbstracta> cartas, JScrollPane scrollPane) {
        CARTAS = cartas;
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);

        scrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarGridDeCartas(scrollPane.getWidth());
            }
        });
    }

    public void actualizarGridDeCartas(int viewPort) {
        removeAll();
        setLayout(new GridBagLayout());
        int columnas = Math.max( (int) (viewPort / (CARTAS.get(0).getAnchura() * 1.1)), 1);
        if (columnas >= CARTAS.size()) {
            columnas = CARTAS.size() ;
        }

        int indiceFila = 0;
        int indice = 0;

        try {
            while (indice < CARTAS.size()) {
                for (int indiceColumna = 0; indiceColumna < columnas; ++indiceColumna) {
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.gridx = indiceColumna;
                    constraints.gridy = indiceFila;
                    constraints.gridwidth = 1;
                    constraints.gridheight = 1;
                    constraints.weightx = 0;
                    constraints.weighty = 0;
                    constraints.anchor = GridBagConstraints.CENTER;
                    constraints.fill = GridBagConstraints.NONE;
                    constraints.insets = new Insets(10, 10, 10, 10);
                    constraints.ipadx = 0;
                    constraints.ipady = 0;
                    add(CARTAS.get(indice), constraints);
                    ++indice;
                }
                ++indiceFila;
            }
        }catch (Exception ignore) {}
        revalidate();
        repaint();
    }
}
