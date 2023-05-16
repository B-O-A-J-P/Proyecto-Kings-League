package com.boajp.vista.formulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import com.boajp.utilidades.EstilosDeVistas;
import com.boajp.vista.componentes.BotonBoajp;
import org.apache.batik.swing.JSVGCanvas;

public class FormularioIniciarSesion extends JPanel {
    private final JLabel lbUsuario;
    private final JTextField tfUsuario;
    private final JLabel lbContrasena;
    private final JPasswordField tfContrasena;
    private final BotonBoajp btContrasenaOlvidada;
    private final BotonBoajp btIniciar;
    private final BotonBoajp btRegistrarse;

    public FormularioIniciarSesion() {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        lbUsuario = new JLabel("Usuario");
        lbContrasena = new JLabel("Contraseña");

        tfUsuario = new JTextField();
        tfUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(5 ,5, 5, 5)
        ));
        tfContrasena = new JPasswordField();
        tfContrasena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(5 ,5, 5, 5)
        ));

        btIniciar = new BotonBoajp("Iniciar sesión", new Color(24, 119, 242), new Color(0, 100, 255),new Color(0, 100, 255));
        btIniciar.setForeground(Color.WHITE);

        btContrasenaOlvidada = new BotonBoajp("¿Has olvidado la contraseña?", null, null, null);
        btContrasenaOlvidada.setBorde(null);

        btRegistrarse = new BotonBoajp("Crear una nueva cuenta", null, null, null);
        btRegistrarse.setBorde(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black),
                new EmptyBorder(5, 0, 5, 0)
        ));

        // Imagen
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(new PanelImagen(), gbc);

        // Etiqueta usuario
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 5, 0, 5);
        add(lbUsuario, gbc);

        // Campo de texto usuario
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 5);
        add(tfUsuario, gbc);

        // Etiqueta contraseña
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 0, 5);
        add(lbContrasena, gbc);

        // Campo de texto contraseña
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 20, 5);
        add(tfContrasena, gbc);

        // Botón iniciar
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 5, 10, 5);
        add(btIniciar, gbc);

        // Botón contraseña olvidada
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        add(btContrasenaOlvidada, gbc);

        // Botón registrar
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        add(btRegistrarse, gbc);


    }

    public JTextField getTfUsuario() {
        return tfUsuario;
    }

    public JPasswordField getTfContrasena() {
        return tfContrasena;
    }

    public JButton getBtContrasenaOlvidada() {
        return btContrasenaOlvidada;
    }

    public JButton getBtIniciar() {
        return btIniciar;
    }

    public JButton getBtRegistrarse() {
        return btRegistrarse;
    }

    private class PanelImagen extends JPanel {
        private JSVGCanvas canvas;

        public PanelImagen() {
            Dimension dimension = new Dimension(200, 100);
            setSize(dimension);
            setMinimumSize(dimension);
            setMaximumSize(dimension);
            setPreferredSize(dimension);
            setBackground(null);
            setOpaque(false);
            canvas = new JSVGCanvas();
            canvas.setURI(getClass().getResource("/imagenes/KL_landscape.svg").toString());
            canvas.setBackground(null);
            canvas.setOpaque(false);

            setLayout(new BorderLayout());
            add(canvas, BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            canvas.setSize(getSize());
        }
    }

    public static void main(String... args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(new Formulario());
        frame.setBackground(Color.BLUE);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
