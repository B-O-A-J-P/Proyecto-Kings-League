package com.boajp.vista.VistaJugadores;

import javax.swing.*;
import java.awt.*;

public class CartaJugador extends JPanel {

    private JLabel lbCodJugador;
    private JLabel lbImagen;
    private JLabel lbNombre;
    private JLabel lbPie;
    private JLabel lbAltura;

    public CartaJugador(String codJugador, ImageIcon imagen, String nombre, String pie, int altura) {
        lbCodJugador = new JLabel(codJugador);
        Image image = new ImageIcon(CartaJugador.class.getResource("/logo2.png")).getImage();
        lbImagen = new JLabel(new ImageIcon(image));
        lbNombre = new JLabel(nombre);
        lbPie = new JLabel(pie);
        lbAltura = new JLabel(String.valueOf(altura));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.getHSBColor(30/360f, 1f, 1f));
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.gray);
        panel1.setLayout(new FlowLayout());
        panel1.add(lbPie);
        panel1.add(lbAltura);

        Insets insets = new Insets(0, 0, 0, 0);

        setLayout(new GridBagLayout());
        add(panel2, new GridBagConstraints(0, 0, 1, 3, 20, 100, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
        add(panel3, new GridBagConstraints(1, 0, 1, 1, 80, 33, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
        add(lbNombre, new GridBagConstraints(1, 1, 1, 1, 0, 33, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
        add(panel1, new GridBagConstraints(1, 2, 1, 1, 0, 33, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));


    }

    public static void main (String... args) {
        JFrame frame = new JFrame();
        CartaJugador cartaJugador = new CartaJugador("234", null, "Ander Mora", "Derecho", 190);
        frame.add(cartaJugador);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}