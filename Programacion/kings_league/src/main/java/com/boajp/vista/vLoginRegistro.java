package com.boajp.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vLoginRegistro {
    private JPanel pPrincipal;
    private JPanel pLogo;
    private JPanel pLoginRegistro;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registrateButton;
    private JLabel jlTitulo;
    private JButton bEntrar;
    private JLabel jlLogo;
    private JButton bLogin;
    private JLabel jlLogin;
    private JLabel jlRegistro;

    private static String op;


    public vLoginRegistro() {
        jlTitulo.setText("INICIAR SESION");
        op = "login";
        ImageIcon logo = new ImageIcon("src/main/java/com/boajp/Recursos/logo2.png");
        jlLogo.setIcon(logo);



        jlLogin.setVisible(false);
        bLogin.setVisible(false);

        registrateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op="registro";
                jlTitulo.setText("CREAR CUENTA");
                registrateButton.setVisible(false);
                jlRegistro.setVisible(false);
                jlLogin.setVisible(true);
                bLogin.setVisible(true);
                bEntrar.setText("Crear cuenta");
            }
        });
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op="login";
                jlTitulo.setText("INICIAR SESION");
                jlLogin.setVisible(false);
                bLogin.setVisible(false);
                registrateButton.setVisible(true);
                jlRegistro.setVisible(true);
                bEntrar.setText("Iniciar Sesion");
            }
        });
        bEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (op){
                    case "registro":

                        break;

                    case "login":

                        break;
                }
            }
        });
    }

    public boolean validarDatos(String tipo, JTextField campo, String exp){
        boolean correcto = true;
        String dato = campo.getText();
        try {
            if (dato.isEmpty())
                throw new Exception(tipo + " es un campo obligatorio");
            Pattern pat = Pattern.compile(exp);
            Matcher mat = pat.matcher(dato);
            if (!mat.matches())
                throw new Exception(tipo + " no tiene un formato adecuado");
        }
        catch (Exception e){
            campo.setBackground(Color.red);
            mostrarError(e.getMessage());
            correcto = false;
        }
        return correcto;
    }

    public void mostrarError(String mensaje){

    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("vLoginRegistro");
        frame.setContentPane(new vLoginRegistro().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
