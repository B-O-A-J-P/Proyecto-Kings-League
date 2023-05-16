package com.boajp.vista.carta;

import com.boajp.utilidades.EstilosDeVistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class GridDeDraft extends JPanel {
    private final ArrayList<CartaAbstracta> CARTAS;
    private int indiceActual = 0;
    private JScrollPane scrollPane;

    public GridDeDraft(ArrayList<CartaAbstracta> cartas, JScrollPane scrollPane) {
        this.CARTAS = cartas;
        this.scrollPane = scrollPane;
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);

        scrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarGrid(scrollPane.getWidth());
            }
        });

    }

    public void actualizarGrid(int viewPort) {
        removeAll();
        int columnas = Math.max( (int) (viewPort / (CARTAS.get(0).getAnchura() * 1.2)), 1);
        if (columnas >= CARTAS.size()) {
            columnas = CARTAS.size();
        }

        int i = indiceActual;

        try {
            for (int indiceColumna = 0; indiceColumna < columnas; ++indiceColumna) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = indiceColumna;
                constraints.gridy = 0;
                constraints.gridwidth = 1;
                constraints.gridheight = 1;
                constraints.weightx = 0;
                constraints.weighty = 0;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.fill = GridBagConstraints.NONE;
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.ipadx = 0;
                constraints.ipady = 0;
                if (i >= CARTAS.size()) i = 0;
                add(CARTAS.get(i), constraints);
                ++i;
            }
        }catch (Exception ignore) {}
        revalidate();
        repaint();
    }

    public void anadirBotonSiguiente(JButton boton) {
        boton.addActionListener(e -> {
            cartaSiguiente();
        });
    }

    public void anadirBotonAnterior(JButton boton) {
        boton.addActionListener(e -> {
            cartaAnterior();
        });
    }

    public void cartaSiguiente() {
        if (indiceActual >= CARTAS.size()) {
            indiceActual = 0;
        } else indiceActual++;
        actualizarGrid(scrollPane.getViewport().getWidth());
    }

    public void cartaAnterior() {
        if (indiceActual <= 0) {
            indiceActual = CARTAS.size() - 1;
        } else indiceActual--;
        actualizarGrid(scrollPane.getViewport().getWidth());
    }
}