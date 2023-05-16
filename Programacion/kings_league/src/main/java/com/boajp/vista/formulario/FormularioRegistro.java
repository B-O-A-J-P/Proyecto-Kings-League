package com.boajp.vista.formulario;

import com.boajp.utilidades.EstilosDeVistas;
import com.boajp.vista.componentes.BotonBoajp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormularioRegistro extends JPanel {
    private final JLabel lbNombre;
    private final JTextField tfNombre;
    private final JLabel lbEmail;
    private final JTextField tfEmail;
    private final JLabel lbContrasena;
    private final JPasswordField tfContrasena;
    private final BotonBoajp btRegistrar;
    private final BotonBoajp btCancelar;


    public FormularioRegistro() {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        lbNombre = new JLabel("Nombre");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        add(lbNombre, gbc);

        tfNombre = new JTextField();
        tfNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(tfNombre, gbc);

        lbEmail = new JLabel("Email");
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(lbEmail, gbc);

        tfEmail = new JTextField();
        tfEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(tfEmail, gbc);

        lbContrasena = new JLabel("Contraseña");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(lbContrasena, gbc);

        tfContrasena = new JPasswordField(15);
        tfContrasena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(tfContrasena, gbc);

        btRegistrar = new BotonBoajp("Registrase", new Color(0, 175, 0), new Color(0, 155, 0), new Color(0, 155, 0));;
        btRegistrar.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 0, 0);
        add(btRegistrar, gbc);

        btCancelar = new BotonBoajp("¿Ya tienes una cuenta?", null, null, null);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;

        add(btCancelar, gbc);


    }

    public JTextField getTfNombre() {
        return tfNombre;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public JPasswordField getTfContrasena() {
        return tfContrasena;
    }

    public JButton getBtRegistrar() {
        return btRegistrar;
    }

    public JButton getBtCancelar() {
        return btCancelar;
    }

    public static void main(String... args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(new FormularioRegistro());

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
