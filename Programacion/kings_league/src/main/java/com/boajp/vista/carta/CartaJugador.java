package com.boajp.vista.carta;

import com.boajp.modelo.JugadorEntidad;
import com.boajp.utilidades.*;

import javax.swing.*;
import java.awt.*;

public class CartaJugador extends CartaAbstracta{
    private int anchura = 300;
    private int altura = 400;
    private Dimension dimension = new Dimension(anchura, altura);
    private Insets insets = new Insets(10, 10, 10, 10);

    public CartaJugador(JugadorEntidad jugador) {
        super(EstilosDeVistas.COLOR_DE_CARTA_JUGADOR, Color.GRAY);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        setLayout(new GridBagLayout());
        GridBagConstraints constraintsTitulo = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
        GridBagConstraints constraintsImagen = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints constraintsCaracteristicas = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

        Image image = new ImageIcon(CartaJugador.class.getResource("/imagenes/perfil.png")).getImage();

        add(new JLabel(jugador.getNombre() + " " + jugador.getApellido(), JLabel.CENTER), constraintsTitulo);
        add(new JLabel(new ImageIcon(image), JLabel.CENTER), constraintsImagen);
        add(new JLabel("Características", JLabel.CENTER), constraintsCaracteristicas);
    }

    @Override
    public int getAnchura() {
        return anchura;
    }

    @Override
    public int getAltura() {
        return altura;
    }
    public static void main(String... args) {
        JugadorEntidad jugador = new JugadorEntidad();
        jugador.setNombre("Jorge");
        jugador.setApellido("Egea Nogueira");
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.add(new CartaJugador(jugador));

            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
