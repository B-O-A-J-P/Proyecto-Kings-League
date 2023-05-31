package com.boajp.vistas.carta;

import com.boajp.modelo.JugadorEntidad;
import com.boajp.modelo.MiembroEntidad;
import com.boajp.modelo.Persona;
import com.boajp.utilidades.EstilosDeVistas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MiembroCarta extends CartaAbstracta{
    private int anchura = 300;
    private int altura = 400;
    private Dimension dimension;
    private Insets padding = new Insets(10, 10, 10, 10);
    private Insets paddingImagen = new Insets(0, 0, 0, 0);


    public MiembroCarta(Persona persona) {
        super(EstilosDeVistas.COLOR_DE_CARTA_JUGADOR, Color.GRAY);
        dimension = new Dimension(anchura, altura);

        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);

        setLayout(new GridBagLayout());
        GridBagConstraints constraintsTitulo = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, padding, 0, 0);
        GridBagConstraints constraintsImagen = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, paddingImagen, 0, 0);
        GridBagConstraints constraintsCaracteristicas = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, padding, 0, 0);

        Image image = new ImageIcon(Objects.requireNonNull(com.boajp.vistas.carta.MiembroCarta.class.getResource("/imagenes/perfil.png"))).getImage();

        if (persona instanceof MiembroEntidad) {
            MiembroEntidad miembro = (MiembroEntidad) persona;

        }

        add(new JLabel(persona.getNombre() + " " + persona.getApellido(), JLabel.CENTER), constraintsTitulo);
        add(new JLabel(new ImageIcon(image), JLabel.CENTER), constraintsImagen);
        add(new JLabel("Características", JLabel.CENTER), constraintsCaracteristicas);

    }

    @Override
    public int getAnchura() {
        return anchura;
    }

    public void setAnchura(int anchura) {
        this.anchura = anchura;
    }

    @Override
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Insets getPadding() {
        return padding;
    }

    public void setPadding(Insets padding) {
        this.padding = padding;
    }

    public Insets getPaddingImagen() {
        return paddingImagen;
    }

    public void setPaddingImagen(Insets paddingImagen) {
        this.paddingImagen = paddingImagen;
    }

    public static void main(String... args) {
        JugadorEntidad jugador = new JugadorEntidad();
        jugador.setNombre("Jorge");
        jugador.setApellido("Egea Nogueira");
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.add(new MiembroCarta(jugador));

            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

