package com.boajp.vista.formulario;

import com.boajp.utilidades.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario extends JPanel {

    private CardLayout cardLayout;
    private JPanel formContainer;
    private FormularioIniciarSesion formularioIniciarSesion;
    private FormularioRegistro formularioRegistro;

    public Formulario() {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        formContainer = new JPanel(cardLayout);
        add(formContainer, BorderLayout.CENTER);

        formularioIniciarSesion = new FormularioIniciarSesion();
        formularioRegistro = new FormularioRegistro();

        formularioIniciarSesion.getBtRegistrarse().addActionListener(e -> showForm("registro"));
        formularioRegistro.getBtCancelar().addActionListener(e -> showForm("iniciarSesion"));

        formContainer.add(formularioIniciarSesion, "iniciarSesion");
        formContainer.add(formularioRegistro, "registro");
    }

    private void showForm(String formName) {
        cardLayout.show(formContainer, formName);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());
            frame.add(new Formulario());

            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
